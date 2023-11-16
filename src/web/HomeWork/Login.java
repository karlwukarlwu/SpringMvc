package web.HomeWork;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Karl Rules!
 * 2023/11/10
 * now File Encoding is UTF-8
 */
@Controller
@RequestMapping("/ll")
public class Login {
    @RequestMapping(value = "/t1")
    public String t1() {
        System.out.println("Login");
        return "success";
    }
//    method = RequestMethod.POST 这个不写也行 默认支持get post 写了是限定支持post
    @RequestMapping(value = "/t2")
    public String t2(User user) {
        System.out.println(user);
        return "success";
    }

}
