package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Karl Rules!
 * 2023/11/8
 * now File Encoding is UTF-8
 * 标识了@Controller的类就是一个控制器
 */
@Controller
public class UserServlet {
//     @RequestMapping(value = "/Login") 类似我们以前在原生的Servlet
//     配置 url-pattern, 就是给方法配置一个url映射
//     即当用户在浏览器输入 http://localhost:8080/web工程路径/Login 就能够访问到login()
//     return "login_ok"; 表示返回结果给视图解析器(InternalResourceViewResolver)
//     视图解析器会根据配置，来决定跳转到哪个页面
//     *
//     *     <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//     *
//     *         <property name="prefix" value="/WEB-INF/pages/"/> 这里是定位的位置
//     *         <property name="suffix" value=".jsp"/> 这里是定位文件的后缀
//     *     </bean>
//     *
//    根据上面的配置 return "login_ok"; 就是转发到 /WEB-INF/pages/login_ok.jsp
    @RequestMapping("/login")
    public String login(){
        System.out.println("Login");
//       默认是请求转发
        return "login_ok";
    }

}
//02
