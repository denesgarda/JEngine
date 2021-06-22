package testing;

import com.denesgarda.JEngine.JGame;
import com.denesgarda.JEngine.JHandler;
import com.denesgarda.JEngine.event.EventHandler;
import com.denesgarda.JEngine.event.FrameEvent;
import com.denesgarda.JEngine.event.SecondPassedEvent;
import com.denesgarda.JEngine.event.TickEvent;
import org.jetbrains.annotations.NotNull;
import testing.assets.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends JGame {
    public static int WIDTH = 512;
    public static int HEIGHT = 512;
    public Handler handler = new Handler();

    public Game() {
        this.start();
        this.setEventListener(this);

        Player player = new Player(256, 256);
        handler.addAsset(player);
    }

    @Override
    public @NotNull JFrame frame() {
        JFrame frame = new JFrame("Test Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        return frame;
    }

    @Override
    public @NotNull Canvas canvas() {
        return new Canvas();
    }

    public static void main(String[] args) {
        new Game();
    }

    @EventHandler
    public void onSecondPassed(SecondPassedEvent event) {
        System.out.println("FPS: " + this.getFPS() + " / " + this.getPreferredFPS());
        System.out.println("TPS: " + this.getTPS() + " / " + this.getPreferredTPS());
    }

    @EventHandler
    public void onTick(TickEvent event) {
        handler.tick();
    }

    @EventHandler
    public void onFrame(FrameEvent event) {
        BufferStrategy bs = this.getCanvas().getBufferStrategy();
        if(bs == null) {
            this.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(graphics);

        graphics.dispose();
        bs.show();
    }
}
