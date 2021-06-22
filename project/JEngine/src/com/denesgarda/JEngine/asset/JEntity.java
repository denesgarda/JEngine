package com.denesgarda.JEngine.asset;

import java.awt.*;

public abstract class JEntity extends JAsset {
    public int X;
    public int Y;

    public JEntity(int x, int y) {
        X = x;
        Y = y;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);
}
