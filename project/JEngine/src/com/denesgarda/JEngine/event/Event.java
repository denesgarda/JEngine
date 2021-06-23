package com.denesgarda.JEngine.event;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * The superclass of all events in the game
 */
public class Event {
    public Event() {

    }

    /**
     * Calls an event to be executed in the event listener class
     * @param object The event listener class for the event to be executed in
     * @param event The event to execute
     */
    public static void callEvent(@NotNull Object object, Event event) {
        for(Method method : object.getClass().getMethods()) {
            if(method.isAnnotationPresent(EventHandler.class)) {
                if(Arrays.asList(method.getParameterTypes()).contains(event.getClass())) {
                    try {
                        method.invoke(object, event);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
