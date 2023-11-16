package web.interceptor09;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import web.json07.entity.User;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Controller
public class FurnHandler {

    @RequestMapping(value = "/hi")
    public String hi(User user) {

        System.out.println("---FurnHandler--hi()---");
        return "success";
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        System.out.println("---FurnHandler--hello()---");
        return "success";
    }

    @RequestMapping(value = "/ok")
    public String ok() {
        System.out.println("---FurnHandler--ok()---");
        return "success";
    }
}
