package web.rest;

/**
 * Karl Rules!
 * 2023/11/9
 * now File Encoding is UTF-8
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 韩顺平
 * @version 1.0
 * BookHandler处理rest风格的请求-增删改查
 */
@RequestMapping("/user")
@Controller
public class BookHandler {

    //查询[GET]
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String getBook(@PathVariable("id") String id) {
        System.out.println("查询书籍 id=" + id);
        return "success";
    }

    //添加[POST]
    @PostMapping(value = "/book")
    public String addBook(String bookName) {
        System.out.println("添加书籍 bookName== " + bookName);
        return "success";
    }

    //删除[DELETE]
//    <%--get方法转 delete 用隐藏表单和js--%> 前端的配置 见rest.jsp 把herf标签转成form表单 然后用post提交 含一个隐藏域
//    过滤器会自动去找_method的值 如果是delete就会转成delete
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public String delBook(@PathVariable("id") String id) {
        System.out.println("删除书籍 id= " + id);
        //return "success"; //[如果这样返回会报错 JSPs only permit GET POST or HEAD]
//        RequestMethod.DELETE 因为你这里改了请求方式 所以要用重定向 jsp自身的问题
        //老师解读
        //1. redirect:/user/success重定向
        //2. 会被解析成 /项目路径/user/success
//        springMvc会自动解析 redirect这个关键字
//        网络请求是302 重定向  不是200 请求转发
        return "redirect:/user/success";
    }

    //如果请求是 /user/success , 就转发到 success.jsp
    //successGenecal对应的url http://ip:port/springmvc/user/success
    @RequestMapping(value = "/success")
    public String successGenecal() {
        return "success";  //由该方法 转发到success.jsp页面
    }

    //修改[PUT]
    @PutMapping(value = "/book/{id}")
    public String updateBook(@PathVariable("id") String id) {
        System.out.println("修改书籍 id=" + id);
        return "redirect:/user/success";
    }
}
