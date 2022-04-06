package com.denesgarda.JEngine;

import testing.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public abstract class Scene extends JPanel {
    private final Canvas canvas;

    public LinkedList<JObject> objects = new LinkedList<>();

    public Scene() {
        this.canvas = canvas();
        this.setLayout(new BorderLayout());
        this.add(canvas);
    }

    public abstract Canvas canvas();

    public Canvas getCanvas() {
        return canvas;
    }

    public void tick() {
        for (JObject object : objects) {
            object.tick();
        }
    }

    public void render() {
        BufferStrategy strategy = canvas.getBufferStrategy();
        if (strategy == null) {
            canvas.createBufferStrategy(3);
            strategy = canvas.getBufferStrategy();
        }
        for (JObject object : objects) {
            object.render(strategy.getDrawGraphics());
        }
        strategy.getDrawGraphics().dispose();
        strategy.show();
    }
}
