package com.denesgarda.JEngine;

import com.denesgarda.JEngine.event.Event;
import com.denesgarda.JEngine.event.FrameEvent;
import com.denesgarda.JEngine.event.SecondPassedEvent;
import com.denesgarda.JEngine.event.TickEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * JGame is the core class of JEngine. It has everything needed to run a game.
 */
public abstract class JGame implements Runnable {
    private final JFrame frame;
    private final Canvas canvas;
    private boolean running = false;
    private final Thread thread = new Thread(this);
    private Object eventListener = new Object();
    private int preferredFPS = 60;
    private int FPS = 0;
    private int preferredTPS = 20;
    private int TPS = 0;

    public JGame() {
        this.frame = frame();
        this.canvas = canvas();
    }

    /**
     * Sets the desired JFrame for the game
     * @return The JFrame that you want the game to use
     */
    public abstract @NotNull JFrame frame();

    /**
     * Sets the desired Canvas for the JFrame
     * @return The Canvas that you want the JFrame to use
     */
    public abstract @NotNull Canvas canvas();

    /**
     * Gets the JFrame of the game
     * @return The JFrame of the game
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Gets the Canvas of the JFrame
     * @return The Canvas of the JFrame
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * Tells if the game is running or not
     * @return The state of the game
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Gets the preferred fps of the game. By default, it is 60
     * @return The preferred fps of the game.
     */
    public int getPreferredFPS() {
        return preferredFPS;
    }

    /**
     * Sets the preferred fps of the game to run at. The game will try to match this value using a game loop algorithm that may be less accurate the higher the number is
     * @param preferredFPS The fps you want to game to run at
     */
    public void setPreferredFPS(int preferredFPS) {
        this.preferredFPS = preferredFPS;
    }

    /**
     * Gets the preferred tps of the game. By default, it is 20
     * @return THe preferred tps of the game
     */
    public int getPreferredTPS() {
        return preferredTPS;
    }

    /**
     * Sets the preferred tps of the game to run at. The game will try to match this value using a game loop algorithm that may be less accurate the higher the number is
     * @param preferredTPS The tps you want to game to run at
     */
    public void setPreferredTPS(int preferredTPS) {
        this.preferredTPS = preferredTPS;
    }

    /**
     * Gets the fps the game is currently running at. This value updates every second
     * @return The fps the game is currently running at.
     */
    public int getFPS() {
        return FPS;
    }

    /**
     * Gets the tps the game is currently running at. This value updates every second
     * @return The tps the game is currently running at.
     */
    public int getTPS() {
        return TPS;
    }

    /**
     * Sets the event listener of the JGame. The event lister class is used to listen for certain events called by the Game to render, tick, etc.
     * @param object The class you want to set as the event listener class
     */
    public void setEventListener(Object object) {
        eventListener = object;
    }

    /**
     * Starts the game
     */
    public synchronized void start() {
        frame.add(canvas);
        frame.setVisible(true);

        if(!running) {
            thread.start();
            running = true;
        }
    }

    /**
     * Stops the game, but doesn't exit.
     */
    public synchronized void stop() {
        if(running) {
            try {
                thread.join();
                running = false;
            }
            catch(Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * Is the high-efficiency game loop algorithm that tries to match all preset settings.
     */
    @Override
    public final void run() {
        long sleepTimeMillis = 1;
        canvas.requestFocus();
        long nanoTime = System.nanoTime();
        long fpsNanoTime = nanoTime;
        long tpsNanoTime = nanoTime;
        long secondInNano;
        int fps = 0;
        int tps = 0;
        long npt;
        long npf;
        long difference = 0;
        boolean sec = false;
        int fpsAdd = 0;
        int tpsAdd = 0;
        while(running) {
            if(sec) {
                sec = false;
                if (FPS < preferredFPS) {
                    fpsAdd += 1;
                }
                else if(FPS > preferredFPS) {
                    fpsAdd -= 1;
                }
                if (TPS < preferredTPS) {
                    tpsAdd += 1;
                }
                else if(TPS > preferredTPS) {
                    tpsAdd -= 1;
                }
            }
            long currentNanoTime = System.nanoTime();
            secondInNano = 1000000000 - sleepTimeMillis * 1000000 + difference;
            npt = secondInNano / (preferredTPS + difference / 1000000);
            npf = secondInNano / (preferredFPS + difference / 1000000);
            if(currentNanoTime >= nanoTime + secondInNano) {
                sec = true;
                nanoTime = currentNanoTime;
                FPS = fps;
                FPS += fpsAdd;
                TPS = tps;
                TPS += tpsAdd;
                fps = 0;
                tps = 0;
                Event.callEvent(eventListener, new SecondPassedEvent());
            }
            else {
                if(currentNanoTime >= fpsNanoTime + npf) {
                    if(fps < preferredFPS) {
                        fpsNanoTime = currentNanoTime;
                        fps++;
                        Event.callEvent(eventListener, new FrameEvent(fps));
                    }
                }
            }
            if(currentNanoTime >= tpsNanoTime + npt) {
                if (tps < preferredTPS) {
                    tpsNanoTime = currentNanoTime;
                    tps++;
                    Event.callEvent(eventListener, new TickEvent(tps));
                }
            }
            try {
                Thread.sleep(sleepTimeMillis);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            difference = System.nanoTime() - currentNanoTime;
        }
    }
}
