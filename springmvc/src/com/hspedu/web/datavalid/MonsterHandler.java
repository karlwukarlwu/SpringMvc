package com.hspedu.web.datavalid;

import com.hspedu.web.datavalid.entity.Monster;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author 韩顺平
 * @version 1.0
 * MonsterHandler 处理器响应用户提交数据
 * @Scope(value = "prototype") 表示每次请求MonsterHandler会生成一个新的对象
 */
@Controller
@Scope(value = "prototype")
public class MonsterHandler {


    /**
     * 显示添加monster的界面
     * 1. 这里Map<String, Object> map
     * 2. 当我们向map添加的数据时，会默认存放到request域
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/addMonsterUI")
    public String addMonsterUI(Map<String, Object> map) {
        /**老韩解读:
         1. 这里的表单，我们使用springMVC的标签来完成
         2. SpringMVC 表单标签在显示之前必须在 request 中有一个 bean, 该 bean 的属性和表单标签的字段要对应!
         request 中的 key 为: form 标签的 modelAttrite 属性值， 比如这里的monsters
         3. SpringMVC 的 form:form 标签的 action 属性值中的 / 不代表 WEB 应用的根目录.
         4. <form:form action="?" method="POST" modelAttribute="monster">
         //这里需要给request增加一个 monster，因为jsp 页面 的modelAttribute="monster"需要
         //这时是springMVC的内部的检测机制  即使是一个空的也需要，否则报错.
         */
        //老韩再次说明，如果你跳转的页面使用springmvc标签
        //就需要准备一个对象，放入request域中，这个对象的属性名 monster, 对于
        //springmvc表单标签的 modelAttribute="monster"
        map.put("monster", new Monster());
        return "datavalid/monster_addUI";
    }


    /**
     * 编写方法,处理添加妖怪
     * 1. springmvc可以将提交的数据，按照参数名和对象的属性名匹配
     * 2. 直接封装到对象中->老师前面讲解模型数据时，讲过
     * String => Integer
     * 3. @Valid Monster monster :表示对monster接收的数据进行校验
     * 4. Errors errors 表示如果校验出现错误，将校验的错误信息保存 errors
     * 5. Map<String, Object> map  表示如果校验出现错误, 将校验的错误信息保存 map 同时保存monster对象
     * 6. 校验发生的时机： 在springmvc底层，反射调用目标方法时，会接收到http请求的数据，然后根据注解来进行验证
     * , 在验证过程中，如果出现了错误，就把错误信息填充errors 和 map
     *
     * @param monster
     * @return
     */
    @RequestMapping(value = "/save")
    public String save(@Valid Monster monster, Errors errors, Map<String, Object> map) {
        System.out.println("----monster---" + monster);
        //我们为了看到验证的情况,我们输出map 和 errors
        System.out.println("===== map ======");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " value=" + entry.getValue());
        }

        System.out.println("===== errors ======");
        if (errors.hasErrors()) {//判断是否有错误
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println("error=" + error);
            }
            return "datavalid/monster_addUI";
        }
        return "datavalid/success";
    }

    //取消绑定 monster的name表单提交的值给monster.name属性
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        /**
         * 老师解读
         * 1. 方法上需要标注 @InitBinder  springmvc底层会初始化 WebDataBinder
         * 2. 调用 webDataBinder.setDisallowedFields("name") 表示取消指定属性的绑定
         *    即：当表单提交字段为 name时， 就不在把接收到的name值，填充到model数据monster的name属性
         * 3. 机制：springmvc 在底层通过反射调用目标方法时, 接收到http请求的参数和值,使用反射+注解技术
         *    取消对指定属性的填充
         * 4. setDisallowedFields支持可变参数，可以填写多个字段
         * 5. 如果我们取消某个属性绑定,验证就没有意义了,应当把验证的注解去掉, name属性会使用默认值null
         *  //@NotEmpty
         *  private String name;
         *
         */
        //webDataBinder.setDisallowedFields("name");
    }
}
