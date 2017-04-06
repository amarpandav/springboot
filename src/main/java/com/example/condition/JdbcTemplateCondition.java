package com.example.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * What makes all of this configuration special, however, is that it leverages Spring’s
 * support for conditional configuration.
 * Conditional configuration allows for configuration to be available in an application, but to
 * be ignored unless certain conditions are met.
 * <p>
 * It’s easy enough to write your own conditions in Spring. All you have to do is
 * implement the Condition interface and override its matches() method.
 */
public class JdbcTemplateCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
        try {
            context.getClassLoader().loadClass(JdbcTemplate.class.getName());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
