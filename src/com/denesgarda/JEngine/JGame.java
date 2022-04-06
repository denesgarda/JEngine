package com.denesgarda.JEngine;

import javax.swing.*;
import java.security.InvalidParameterException;

public abstract class JGame {
    public final JFrame frame;
    private Scene scene;
    private Thread loop;

    protected int preferredFPS;
    protected int FPS;
    protected int preferredTPS;
    protected int TPS;

    public JGame() {
        frame = frame();
    }

    public abstract JFrame frame();

    public void start() {
        if (preferredFPS == 0 || preferredTPS == 0) {
            throw new InvalidParameterException("Preferred FPS or preferred TPS is not defined or is equal to 0.");
        } else {
            frame.setVisible(true);
            loop = new Thread(new GameLoop(this));
            loop.start();
        }
    }

    public void stop() {
        loop.interrupt();
        frame.setVisible(false);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        frame.setContentPane(scene);
    }

    public Scene getScene() {
        return scene;
    }

    public void setPreferredFPS(int preferredFPS) {
        this.preferredFPS = preferredFPS;
    }

    public int getPreferredFPS() {
        return preferredFPS;
    }

    public void setPreferredTPS(int preferredTPS) {
        this.preferredTPS = preferredTPS;
    }

    public int getPreferredTPS() {
        return preferredTPS;
    }

    public int getFPS() {
        return FPS;
    }

    public int getTPS() {
        return TPS;
    }
}
