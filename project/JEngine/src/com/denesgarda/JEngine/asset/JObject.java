package com.denesgarda.JEngine.asset;

import java.awt.*;

public abstract class JObject extends JAsset {
    public int X;
    public int Y;

    public JObject(int x, int y) {
        X = x;
        Y = y;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);
}
