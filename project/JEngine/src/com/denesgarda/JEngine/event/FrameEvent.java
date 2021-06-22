package com.denesgarda.JEngine.event;

public class FrameEvent extends UpdateEvent {
    private final int frame;

    public FrameEvent(int frame) {
        super();
        this.frame = frame;
    }

    public int getFrame() {
        return frame;
    }
}
