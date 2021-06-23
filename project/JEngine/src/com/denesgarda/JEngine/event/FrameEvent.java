package com.denesgarda.JEngine.event;

/**
 * The event that fires every time a new frame needs to be loaded
 */
public class FrameEvent extends UpdateEvent {
    private final int frame;

    /**
     * The default constructor of FrameEvent. Should only be used within JEngine
     * @param frame The frame number in the given second
     */
    public FrameEvent(int frame) {
        super();
        this.frame = frame;
    }

    /**
     * Gets the frame
     * @return The frame
     */
    public int getFrame() {
        return frame;
    }
}
