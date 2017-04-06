package com.example.service;


import com.example.condition.JdbcTemplateCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * the MyConditionalService bean will only be created if the JdbcTemplateCondition
 * passes
 */
@Conditional(JdbcTemplateCondition.class)
@Service
public class MyConditionalService {

    /*
    example from book
    @Conditional(JdbcTemplateCondition.class)
    public MyService myService() {
    ...
    }
     */

    /*
    @ConditionalOnBean …the specified bean has been configured
    @ConditionalOnMissingBean …the specified bean has not already been configured
    @ConditionalOnClass …the specified class is available on the classpath
    @ConditionalOnMissingClass …the specified class is not available on the classpath
    @ConditionalOnExpression …the given Spring Expression Language (SpEL) expression
    evaluates to true
    @ConditionalOnJava …the version of Java matches a specific value or range
    of versions
    @ConditionalOnJndi …there is a JNDI InitialContext available and
    optionally given JNDI locations exist
    @ConditionalOnProperty …the specified configuration property has a specific value
    @ConditionalOnResource …the specified resource is available on the classpath
    @ConditionalOnWebApplication …the application is a web application
    @ConditionalOnNotWebApplication …the application is not a web application
     */
}
