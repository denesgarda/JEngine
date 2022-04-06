package testing.objects;

import com.denesgarda.JEngine.JObject;

import java.awt.*;

public class Player extends JObject {
    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(50, 50, 50, 50);
    }
}
