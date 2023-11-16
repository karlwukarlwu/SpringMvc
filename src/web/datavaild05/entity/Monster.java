package web.datavaild05.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Karl Rules!
 * 2023/11/15
 * now File Encoding is UTF-8
 */
public class Monster {
    private Integer id;

    //email是string,使用@NotEmpty
    @NotEmpty
    private String email;

    //@Range(min = 1,max = 100)
    //表示接收的age值，在 1-100之间
//  注解组合使用
    @NotNull(message = "age cant be null")
    @Range(min = 1,max = 100)
    private Integer age;
    //@NotEmpty 表示name不能为空
    //Asserts that the annotated string, collection, map or array is not {@code null} or empty.
    @NotEmpty
    private String name;

    @NotNull(message = "birth cant be null")
//    手动设置格式 如果传入的数据不是这个格式就会报错
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "salary cant be null")
    @NumberFormat(pattern = "###,###.##")
    private Float salary;

    public Monster(Integer id, String email, Integer age, String name, Date birthday, Float salary) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Monster() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }
}
