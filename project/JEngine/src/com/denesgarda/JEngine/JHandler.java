package com.denesgarda.JEngine;

import com.denesgarda.JEngine.asset.JAsset;

import java.awt.*;
import java.util.LinkedList;

/**
 * JHandler is what refreshes graphics, ticks the game, and manages assets like objects and entities of a JGame
 */
public abstract class JHandler {
    public LinkedList<JAsset> ASSETS = new LinkedList<>();

    public JHandler() {

    }

    /**
     * Ticks all assets in the JGame
     */
    public void tick() {
        for(JAsset asset : ASSETS) {
            asset.tick();
        }
    }

    /**
     * Renders all assets in the JGame
     * @param graphics The graphics the assets use to render
     */
    public void render(Graphics graphics) {
        for(JAsset asset : ASSETS) {
            asset.render(graphics);
        }
    }

    /**
     * Adds an asset to the linked list of assets.
     * @param asset The asset you want to add
     */
    public void addAsset(JAsset asset) {
        this.ASSETS.add(asset);
    }

    /**
     * Removes an asset from the linked list of assets
     * @param asset The asset you want to remove
     */
    public void removeAsset(JAsset asset) {
        this.ASSETS.remove(asset);
    }
}
