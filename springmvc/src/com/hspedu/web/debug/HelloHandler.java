package com.hspedu.web.debug;

import com.hspedu.web.json.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Controller
public class HelloHandler {

    //编写方法, 响应请求, 返回ModelAndView
    @RequestMapping(value = "/debug/springmvc")
    public ModelAndView hello(User user, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ok");//对应到/WEB-INF/pages/ok.jsp
        modelAndView.addObject("name", "老韩"); //在model中放入数据 k-v
        return modelAndView;
    }
}
