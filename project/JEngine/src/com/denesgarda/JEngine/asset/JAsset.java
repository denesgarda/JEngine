package com.denesgarda.JEngine.asset;

import java.awt.*;

/**
 * The superclass of all JObjects and JEntities
 */
public abstract class JAsset {
    protected JAsset() {

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
