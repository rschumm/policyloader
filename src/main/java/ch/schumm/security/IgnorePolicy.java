package ch.schumm.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Policies mit dieser Annotation werden vom Loader nicht geladen und damit auch nicht ausgewertet.
 * @author C709360
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnorePolicy {

}
