package testing;

import com.denesgarda.JEngine.JGame;

import javax.swing.*;

public class Game extends JGame {
    @Override
    public JFrame frame() {
        JFrame frame = new JFrame("JEngine Testing");
        frame.setSize(512, 512);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}
