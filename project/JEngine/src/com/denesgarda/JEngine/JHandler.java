package com.denesgarda.JEngine;

import com.denesgarda.JEngine.asset.JAsset;

import java.awt.*;
import java.util.LinkedList;

public abstract class JHandler {
    LinkedList<JAsset> assets = new LinkedList<>();

    public JHandler() {

    }

    public void tick() {
        for(JAsset asset : assets) {
            asset.tick();
        }
    }
    public void render(Graphics graphics) {
        for(JAsset asset : assets) {
            asset.render(graphics);
        }
    }

    public void addAsset(JAsset asset) {
        this.assets.add(asset);
    }
    public void removeAsset(JAsset asset) {
        this.assets.remove(asset);
    }
}
