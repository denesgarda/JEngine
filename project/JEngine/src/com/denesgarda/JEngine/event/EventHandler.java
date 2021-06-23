package com.denesgarda.JEngine.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * All methods that want to listen for events need to be tagged with EventHandler in order to be realized as a listening method.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
}
