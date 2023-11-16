package com.hspedu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 韩顺平
 * @version 1.0
 */
@RequestMapping("/computer")
@Controller
public class ComputerHandler {

    //这里一定要注意，info方法的形参名需要和请求的参数名一致
    @PostMapping("/info")
    public String info(String brand, String price, String nums) {
        System.out.println("电脑信息 brand=" + brand
                + " price=" + price + " nums=" + nums);

        return "success";
    }
}
