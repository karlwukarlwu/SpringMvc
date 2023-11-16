package web.requestparam;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.requestparam.entity.Master;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 韩顺平
 * @version 1.0
 */
@RequestMapping("/vote")
@Controller
public class VoteHandler {


    /**
     * 老韩解读 @RequestParam(value="name", required=false)
     * 1. 获取到超链接传递的数据 请求 http://localhost:8080/模板路径/vote/vote01?name=xx
     * 2. @RequestParam 表示会接收提交的参数
     * 3. value="name" 表示提交的参数名是name
     * 4. required=false 表示该参数可以没有, 默认是true,表示必须有这个参数
     * 5. 当我们使用了@RequestParam(value="name", required=false)后就请求的参数名和方法的形参名可以不一致
     */
    @RequestMapping(value = "/vote01")
//    这么写等于是把注解注到目标形参上 这样就可以参数和形参名不一致了 但是注解的value要和前端url的name一致
    public String test01(@RequestParam(value = "name", required = false) String username) {

        System.out.println("得到的username= " + username);
        //返回到一个结果
        return "success";
    }

    /**
     * 需求: 获取http请求头信息, 获取到Accept-Encoding 和 Host
     * 1. 这里涉及到前面讲过的http协议,小伙伴可以进行回顾
     *
     * @RequestHeader("Http请求头字段") 用这个注解 拿到请求头的信息
     */
    @RequestMapping(value = "/vote02")
    public String test02(@RequestHeader("Accept-Encoding") String ae,
                         @RequestHeader("Host") String host) {
        System.out.println("Accept-Encoding= " + ae);
        System.out.println("Host= " + host);
        //返回到一个结果
        return "success";
    }

    /**
     * 演示如果获取到提交数据->封装成java对象
     *
     * @return 老师说明
     * 1. 方法的形参用对应的类型来指定即可, SpringMVC会自动的进行封装
     * 2. 如果自动的完成封装, 要求提交的数据，参数名和对象的字段名保持一致
     * Monster 参数名
     *     private Integer id;
     *     private String name;
     *     private Pet pet;
     *前端传入的
     *     主人号:<input type="text" name="id"><br>
     *     主人名:<input type="text" name="name"><br>
     *     宠物号:<input type="text" name="pet.id"><br> 这里要保证和级联对象的属性名一致
     *     宠物名:<input type="text" name="pet.name"><br>
     * 3. 如果属性是对象，这里就是仍然是通过 字段名.字段名 比如Master [pet]
     * , 即提交的数据 参数名 是 pet.id pet.name， 这就是级联操作
     * 4. 如果提交的数据 的参数名和对象的字段名不匹配，则对象的属性值就是null
     * 5. 小伙伴疑惑，怎么就封装成功[底层仍然是反射+注解..]
     *  问题 如果我两个对象属性名一样怎么办（Master m1, Master m2）=>这个时候就会报错 解决办法
     *  再弄一个大类把这俩包起来当 然后属性名 m1.xx m2.xx 这样就不会报错了
     */
    @RequestMapping(value = "/vote03")
    public String test03(Master master) {

        System.out.println("master=" + master);
        //返回结果
        return "success";
    }

    /**
     * 使用servlet api, 来获取提交的数据
     *HttpServletRequest，HttpServletResponse这些是 servlet的api
     * @return
     */
    @RequestMapping(value = "/vote04")
    public String test04(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession hs) {

        //获取到session
        //servlet原生的方式
        HttpSession session = request.getSession();
        System.out.println("session=" + session);
        //注意:通过参数传入的 hs 和 通request.getSession() 得到的对象是同一个
//        HttpSession hs 这个是springmvc有的参数
        System.out.println("hs= " + hs);

        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username= " + username);
        System.out.println("pwd= " + pwd);
        //返回结果
        return "success";
    }


//    将数据封装到request域中的几种方法
    /**
     * 老韩解读
     * 1. 演示将提交的数据->springmvc封装到java对象->springmvc
     * 当我们传入的时候已经自动封装了
     *      会自动的将其放入到request域
     *      /vote03 看上面的这条
     * 2. 这样我们就可以在跳转到的页面取出数据.
     *
     * @return
     */
    @RequestMapping(value = "/vote05")
    public String test05(Master master, HttpServletRequest request) {

        //老韩解读
        //1. springmvc会自动把获取的model模型，放入到request域中，名字就是master
        //2. 也可以手动将master放入到request[一会在讲]
        request.setAttribute("address", "beijing");
        //3. 如果我们希望修改master的属性值
        master.setName("nono");
        //4. 分析一下springmvc默认存放对象到request域中,属性名是
        //
        //返回到一个结果
        return "vote_ok";
    }

    /**
     * 演示通过Map<String,Object> 设置数据到request域
     *
     * @return
     */
    @RequestMapping(value = "/vote06")
    public String test06(Master master, Map<String, Object> map) {
        System.out.println("------test06-----");
        //老韩解读
        //1. 需求是通过map对象，添加属性到request中
        //2. 原理分析：springmvc会遍历map，然后将map的k-v, 存放到request域
        map.put("address2", "beijing...");
//        当我们重新放了第二个master和之前的默认冲突了的时候 默认替换了
        //map.put("master", null);
        //返回到一个结果
        return "vote_ok";
    }


    /**
     * 演示通过返回ModelAndView对象，将数据放入到request域
     * @return
     */
    @RequestMapping(value = "/vote07")
    public ModelAndView test07(Master master) {

        System.out.println("----test07----");
        ModelAndView modelAndView = new ModelAndView();
        //放入属性到modelAndView对象
//        前端逻辑页面提交跳转逻辑
//            前端数据->被默认封装成对象或者不封装->到这里被放入modelAndView对象中->传入中央控制器->跳转到指定页面
        modelAndView.addObject("address", "shanghai");
        //modelAndView.addObject("master", null); //和之前替换一个逻辑 也是替换
//        后端后面添加的逻辑也是这样
            //可以把从数据库得到的数据->对象-》放入modelAndView[Service-dao-db]
        //这里指定跳转的视图名称
//        这里一定要注意设置视图名称
        modelAndView.setViewName("vote_ok");
        //返回结果
        return modelAndView;
    }

//    1) 从本质看，请求响应的方法return "xx", 是返回了一个字符串，其实本质是返回了一个
//ModelAndView 对象，只是默认被封装起来的.
//2) ModelAndView 即可以包含model 数据，也可以包含视图信息
//3) ModelAndView 对象的addObject 方法可以添加key-val 数据，默认在request 域中
//4) ModelAndView 对象setView 方法可以指定视图名称



    /**
     * 演示如何将数据设置到session域中
     * @return
     */
    @RequestMapping(value = "/vote08")
    public String test08(Master master, HttpSession httpSession) {
        System.out.println("----test08----");
        //master对象是默认放在request域
        //我们将master对象放入到session域
        httpSession.setAttribute("master", master);
        httpSession.setAttribute("address", "guangzhou");

        return "vote_ok";//请求转发
    }
    /**
     * 老师解读
     * 1. 当Handler的方法被标识 @ModelAttribute,就视为一个前置方法
     * 2. 当调用该Handler的其它的方法时，都会先执行该前置方法
     * 3. 类似我们前面讲解Spring时，AOP的前置通知[底层是AOP机制]
     * 4. prepareModel前置方法，会切入到其它方法前执行..
     */
    @ModelAttribute
    public void prepareModel(){
        System.out.println("prepareModel()-----完成准备工作-----");
    }
//    应用场景
//    1. 在修改前，在前置方法中从数据库查出这个用户
//    2. 在修改方法(目标方法)中，可以使用前置方法从数据库查询的用户
//    3. 如果表单中对用户的某个属性修改了，则以新的数据为准，如果没有修改，则以数据库的信息为准，比如，用户的某个属性不能修改，就保持原来的值

}
