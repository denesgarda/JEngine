package com.denesgarda.JEngine.event;

/**
 * Fires every time an in-game second passes. It may be inaccurate if the game loop gets overloaded and cannot run more that one time per second. At that point, the fps and tps will be 0 and it is highly recommended to terminate the program.
 */
public class SecondPassedEvent extends TimeEvent {
    public SecondPassedEvent() {
        super();
    }
}
