package com.olim.cvhelper.bot.config;

import com.olim.cvhelper.bot.model.StateOrder;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Component
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface BotState {
    /**
     * Returns an array of the commands supported by handler
     *
     * @return an array of the commands supported by handler
     */
    StateOrder orderId();

}
