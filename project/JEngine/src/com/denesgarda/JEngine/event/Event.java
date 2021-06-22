package com.denesgarda.JEngine.event;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Event {
    public Event() {

    }

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
