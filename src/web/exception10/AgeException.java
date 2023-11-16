package web.exception10;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 韩顺平
 * @version 1.0
 */
//通过@ResponseStatus 注解, 可以自定义异常的说明
// Java本身的自定义异常是继承一个已经存在的异常类, 例如 RuntimeException
//  throw new AgeException("年龄必须在1-120之间~~~");
//    目标类要手动抛出这个异常 resason是异常信息 前面传入的message 是页面回显用的
@ResponseStatus(reason = "年龄需要在1-120之间", value = HttpStatus.BAD_REQUEST)

public class AgeException extends RuntimeException {

    public AgeException() {
    }

    public AgeException(String message) {
        super(message);
    }
}
