package com.denesgarda.JEngine.asset;

import java.awt.*;

/**
 * An entity in a game
 */
public abstract class JEntity extends JAsset {
    public int X;
    public int Y;

    /**
     * The default constructor of a JEntity
     * @param x The x-position on the JFrame
     * @param y The y-position on the JFrame
     */
    public JEntity(int x, int y) {
        X = x;
        Y = y;
    }

    /**
     * A method to be executed for every tick
     */
    public abstract void tick();

    /**
     * A method to be executed every frame to refresh graphics
     * @param graphics Graphics used to render
     */
    public abstract void render(Graphics graphics);
}
