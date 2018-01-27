package org.demo.demoarch.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
