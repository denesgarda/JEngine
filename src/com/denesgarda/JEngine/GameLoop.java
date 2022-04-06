package com.denesgarda.JEngine;

public class GameLoop implements Runnable {
    private JGame game;

    public GameLoop(JGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        long sleepTimeMillis = 1;
        game.getScene().getCanvas().requestFocus();
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
        while(true) {
            if(sec) {
                sec = false;
                if (game.FPS < game.preferredFPS) {
                    fpsAdd += 1;
                }
                else if(game.FPS > game.preferredFPS) {
                    fpsAdd -= 1;
                }
                if (game.TPS < game.preferredTPS) {
                    tpsAdd += 1;
                }
                else if(game.TPS > game.preferredTPS) {
                    tpsAdd -= 1;
                }
            }
            long currentNanoTime = System.nanoTime();
            secondInNano = 1000000000 - sleepTimeMillis * 1000000 + difference;
            npt = secondInNano / (game.preferredTPS + difference / 1000000);
            npf = secondInNano / (game.preferredFPS + difference / 1000000);
            if(currentNanoTime >= nanoTime + secondInNano) {
                sec = true;
                nanoTime = currentNanoTime;
                game.FPS = fps;
                game.FPS += fpsAdd;
                game.TPS = tps;
                game.TPS += tpsAdd;
                fps = 0;
                tps = 0;
            }
            else {
                if(currentNanoTime >= fpsNanoTime + npf) {
                    if(fps < game.preferredFPS) {
                        fpsNanoTime = currentNanoTime;
                        fps++;
                        game.getScene().render();
                    }
                }
            }
            if(currentNanoTime >= tpsNanoTime + npt) {
                if (tps < game.preferredTPS) {
                    tpsNanoTime = currentNanoTime;
                    tps++;
                    game.getScene().tick();
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
