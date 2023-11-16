package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//他这里讲的是RequestMapping的各种用法
//04

/**
 * Karl Rules!
 * 2023/11/8
 * now File Encoding is UTF-8
 */
@RequestMapping(value = "/user")
//想换url换个类名即可 只要在扫描的文件夹下就行
//说明: @RequestMapping 注解可以修饰方法，还可以修饰类当同时修饰类和方法时，
//请求的url 就是组合/类请求值/方法请求值.
//想多层嵌套就要手动设置
//    // 如果你想要进一步嵌套，可以这样继续给方法手动添加多层路径
//    @RequestMapping("/settings/privacy") 比如多层嵌套就是一溜方法全是setting开头
@Controller //UserHandler就是一个处理器/控制器,注入到容器
public class UserHandler {

    /** url 参数的获取
     * 如果我们确定表单或者超链接会提交某个字段数据比如(email), 要求提交的参数名和目
     * 标方法的参数名保持一致.
     *
     * /user/hello?email=xxx 通过方法定从义 email形参 拿到email的值
     * hello3(String email)： 如果我们的请求参数有 email=xx, 就会将传递的值,赋给String email
     * ,要求名称保持一致, 如果不一致，那么接收不到数据, 而是null
     * @param email
     * @return
     */
    @GetMapping(value = "/hello3")// /user/hello3?email=abc
    public String hello3(String email) {
        System.out.println("hello3 " + email);//hello3 abc
        return "success";
    }
//    如果是post 需要前端的name属性和后端的形参名字一致
//    前端
//        <form action="user/info" method="post">
//    品牌:<input type="text" name="brand"><br>
//    价格:<input type="text" name="price"><br>
//    数量:<input type="text" name="nums"><br>
//    <input type="submit" value="提交">
//</form>

//    //这里一定要注意，info方法的形参名需要和请求的参数名一致
//    @PostMapping("/info")
//    public String info(String brand, String price, String nums) {
//        System.out.println("电脑信息 brand=" + brand
//                + " price=" + price + " nums=" + nums);
//
//        return "success";
//    }


    /**
     * 老韩解读
     * 1. method=RequestMethod.POST: 表示请求buy目标方法必须是 post
     * 2. RequestMethod 四个常用选项 POST, GET, PUT, DELETE[后面我们会详解]
     * 3. SpringMVC 控制器默认支持GET和POST两种方式， 你不写method默认get post
     * <p>
     * 4. buy()方法请求的url: http://ip:port/工程路径/user/buy
     * 5. @PostMapping(value = "/buy")等价 @RequestMapping(value = "/buy",method = RequestMethod.POST)
     * @return
     */
    //@RequestMapping(value = "/buy",method = RequestMethod.POST)
//    @PostMapping(value = "/buy")
    @RequestMapping(value = "/buy")
    public String buy() {
        System.out.println("购买商品~");
//        这里打到视图解析器 解析器开始解析到要用的页面
        return "success";
    }

    /**
     * 老韩解读
     * 1. params="bookId" 表示请求该目标方法时，必须给一个bookId参数, 值没有限定
     * 2. search(String bookId): 表示请求目标方法时, 携带的bookId=100, 就会将请求携带的 bookId对应的
     * 值 100, 赋给 String bookId
     * 3. params = "bookId=100" 表示必须给一个bookId参数, 而且值必须是100
     * http://localhost:8080/mvc1/user/find?bookId=100 只有这样才可以
     * @return
     */
    @RequestMapping(value = "/find", params = "bookId=100", method = RequestMethod.GET)
    public String search(String bookId) {
        System.out.println("查询书籍 bookId= " + bookId);
        return "success";
    }

    //    4. Ant 风格的url 地址举例
//     /user/*/createUser: 匹配/user/aaa/createUser、/user/bbb/createUser 等URL
//     /user/**/createUser: 匹配/user/createUser、/user/aaa/bbb/createUser 等URL
//     /user/createUser??: 匹配/user/createUseraa、/user/createUserbb 等URL

    /**
     * 要求: 可以配置  /user/message/aa, /user/message/aa/bb/cc
     * 1. @RequestMapping(value = "/message/**") /** 可以匹配多层路径
     *
     */
    @RequestMapping(value = "/message/**")
    public String im() {
        System.out.println("发送消息");
        return "success";
    }

//    @RequestMapping 还可以配合@PathVariable 映射URL 绑定的占位符。
    //要求： 我们希望目标方法获取到 username 和 userid, value="/xx/{username}" - @PathVariable("username")..
//    看看得了 我不知道这个有什么意义
    //前端页面: <a href="user/reg/kristina/300">占位符的演示</a>
    //(value = "/reg/{username}/{userid}"): 表示kristina->{username} 300=>{userid}
    @RequestMapping(value = "/reg/{username}/{userid}")
    public String register(@PathVariable("username") String name,
                           @PathVariable("userid") String id) {
        System.out.println("接收到参数--" + "username= " + name + "--" + "usreid= " + id);
        return "success";
    }

    @RequestMapping(value = "/hi")
    public String hi() {
        System.out.println("hi");
        return "success";
    }

    //@RequestMapping(value = "/hi")
    //public String hi2() {
    //    System.out.println("hi");
    //    return "success";
    //}


}
