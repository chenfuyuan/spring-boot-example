package com.learn.project.springboot.example.security.system.validator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 姓名校验
 *
 * @author chenfuyuan
 * @date 2022/2/26 22:45
 */
@Documented
@Constraint(validatedBy = FullNameValidator.class)
@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
public @interface FullName {
    String message() default "姓名格式错误";

    Class[] groups() default {};

    Class[] payload() default {};
}
