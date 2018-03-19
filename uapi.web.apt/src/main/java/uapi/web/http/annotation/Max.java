package uapi.web.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xquan on 6/1/2017.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Max {

    int intValue() default Integer.MIN_VALUE;

    short shortValue() default Short.MIN_VALUE;

    long longValue() default Long.MIN_VALUE;

    float floatValue() default Float.MIN_VALUE;

    double doubleValue() default Double.MIN_VALUE;
}
