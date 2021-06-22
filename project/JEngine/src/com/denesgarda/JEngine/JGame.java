package com.denesgarda.JEngine;

import com.denesgarda.JEngine.event.Event;
import com.denesgarda.JEngine.event.FrameEvent;
import com.denesgarda.JEngine.event.SecondPassedEvent;
import com.denesgarda.JEngine.event.TickEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

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

    public abstract @NotNull JFrame frame();
    public abstract @NotNull Canvas canvas();

    public JFrame getFrame() {
        return this.frame;
    }
    public Canvas getCanvas() {
        return this.canvas;
    }
    public boolean isRunning() {
        return running;
    }
    public int getPreferredFPS() {
        return preferredFPS;
    }
    public void setPreferredFPS(int preferredFPS) {
        this.preferredFPS = preferredFPS;
    }
    public int getPreferredTPS() {
        return preferredTPS;
    }
    public void setPreferredTPS(int preferredTPS) {
        this.preferredTPS = preferredTPS;
    }
    public int getFPS() {
        return FPS;
    }
    public int getTPS() {
        return TPS;
    }

    public void setEventListener(Object object) {
        eventListener = object;
    }

    public synchronized void start() {
        frame.add(canvas);
        frame.setVisible(true);

        if(!running) {
            thread.start();
            running = true;
        }
    }
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
