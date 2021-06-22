package com.denesgarda.JEngine.event;

public class TickEvent extends UpdateEvent {
    private final int tick;

    public TickEvent(int tick) {
        super();
        this.tick= tick;
    }

    public int getTick() {
        return tick;
    }
}
