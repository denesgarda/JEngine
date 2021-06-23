package com.denesgarda.JEngine.event;

/**
 * Fires every time the game ticks
 */
public class TickEvent extends UpdateEvent {
    private final int tick;

    /**
     * The default constructor of TickEvent. Should only be used within JEngine
     * @param tick The tick number in the given second
     */
    public TickEvent(int tick) {
        super();
        this.tick= tick;
    }

    /**
     * Gets the frame
     * @return The frame
     */
    public int getTick() {
        return tick;
    }
}
