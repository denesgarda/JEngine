package com.denesgarda.JEngine.asset;

import java.awt.*;

public abstract class JAsset {
    protected JAsset() {

    }

    public abstract void tick();
    public abstract void render(Graphics graphics);
}
