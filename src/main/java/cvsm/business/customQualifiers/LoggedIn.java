package cvsm.business.customQualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;

@Qualifier
@Retention(RUNTIME)
@Target({ TYPE, METHOD, PARAMETER, FIELD })
public @interface LoggedIn {

}
