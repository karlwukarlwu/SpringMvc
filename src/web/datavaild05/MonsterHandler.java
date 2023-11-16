package web.datavaild05;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import web.datavaild05.entity.Monster;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Karl Rules!
 * 2023/11/15
 * now File Encoding is UTF-8
 */
@Controller
@Scope(value = "prototype")//单例模式
public class MonsterHandler {
//
    @RequestMapping(value = "/addMonsterUI")
    public String addMonsterUI(Map<String, Object> map) {
//2. SpringMVC 表单标签在显示之前必须在 request 中有一个 bean, 该 bean 的属性和表单标签的字段要对应
//         request 中的 key 为: form 标签的 modelAttrite 属性值， 比如这里的monsters
//        什么意思 因为下一个页面需要用到monster (modelAttribute="monster)所以这里要先放一个空的
//        不然500错误
        map.put("monster", new Monster());
        return "datavalid/monster_addUI";
    }


//  如果前端把String（比如 ）给了后端 会尝试转成目标数据类型 如果转不了就报错
//  当我们在数据模型上加上了限制以后 我们需要在后端加上@Valid注解 表示对monster接收的数据进行校验
//    Errors errors 用来接收错误信息 当Errors errors 表示如果校验出现错误，将校验的错误信息保存 errors
//    Map<String, Object> map  表示如果校验出现错误, 将校验的错误信息保存 map 同时保存monster对象
//    校验发生的时机： 在springmvc底层，反射调用目标方法时，会接收到http请求的数据，然后根据注解来进行验证
//    在验证过程中，如果出现了错误，就把错误信息填充errors 和 map
    @RequestMapping(value = "/save")
    public String save(@Valid Monster monster, Errors errors, Map<String, Object> map) {
        System.out.println("----monster---" + monster);
        //我们为了看到验证的情况,我们输出map 和 errors
//        errors里面会记录我们注解定义的message 或者默认的message
//        比如  @NotNull(message = "age cant be null")

//        当我们有了错误信息 我们可以进行回显 回显的要用我们自定义的错误信息
//        如何回显 需要配置文件 创建i18n和在xml中配置 在配置文件里面写上我们要回显的数据
//        i18n配置文件的key是错误信息的开头 value是回显的内容
        System.out.println("===== map ======");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " value=" + entry.getValue());
            System.out.println("");
        }

        System.out.println("===== errors ======");
        if (errors.hasErrors()) {//判断是否有错误
            List<ObjectError> allErrors = errors.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println("error=" + error);
            }
            return "datavalid/monster_addUI";
//            <form:errors path="name"/> 前端会自动显示错误信息 看看得了 没人用jsp了
//            以后用json了
        }
        return "datavalid/success";
    }

    //取消绑定 monster的name表单提交的值给monster.name属性
//    我们不想要前端传过来的某个值 我们可以在这里取消绑定
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

//        写这个表明不要的属性即可，mvc会去扫描这个注释找到这个方法
        //webDataBinder.setDisallowedFields("name");
    }
}
