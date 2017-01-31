package pt.adrz.gymlogger.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

@NameBinding
@Retention(value=RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AuthenticationNotRequired { }
