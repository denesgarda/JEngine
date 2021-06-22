package testing.assets;

import com.denesgarda.JEngine.asset.JEntity;

import java.awt.*;

public class Player extends JEntity {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(X, Y, 32, 32);
    }
}
