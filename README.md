# JEngine
A High-Efficiency Game Engine for Java
## Getting Started
Download the latest version from [here](./builds/JEngine_1.4.jar), and add it to your project's dependencies.
## Usage
### Initializing
For example, I will be using the class called ``Game.java`` as my main class. In your main class, you have to extend JGame and implement methods.
```java
public class Game extends JGame {
    @Override
    public @NotNull JFrame frame() {
        return null;
    }

    @Override
    public @NotNull Canvas canvas() {
        return null;
    }
}
```
Also add a constructor and a main method.
```java
public class Game extends JGame {
    public Game() {
        this.start();
    }

    @Override
    public @NotNull JFrame frame() {
        return null;
    }

    @Override
    public @NotNull Canvas canvas() {
        return null;
    }

    public static void main(String[] args) {
        new Game();
    }
}
```
Return a JFrame and a Canvas in the two overridden methods.
```java
public class Game extends JGame {
    public static int WIDTH = 512;
    public static int HEIGHT = 512;
    public static String TITLE = "Game";

    public Game() {
        this.start();
    }

    @Override
    public @NotNull JFrame frame() {
        JFrame frame = new JFrame(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    @Override
    public @NotNull Canvas canvas() {
        return new Canvas();
    }

    public static void main(String[] args) {
        new Game();
    }
}
```
### Handler
To use a handler in your game, you have to create one. Create a class that extends JHandler. For now, I will just create an empty constructor because my handler doesn't need to do anything else than it already does.
```java
public class Handler extends JHandler {
    public Handler() {
        
    }
}
```
I will also add an instance of my handler in ``Game.java``.
```java
private Handler handler = new Handler();
```
### Configuring
You can configure the fps and tps to whatever you like. By default, the fps is set to 60, and the tps is set to 20. It is not recommended to set the tps very high.
```java
this.setPreferredFPS(144);
this.setPreferredTPS(10);
```
I will not be including this in my game, as the defaults are fine for this demo.
### Using that handler and events
To use the handler, you have to listen for certain events. This is how you can set the event listener in ``Game.java``. I'm doing it in the constructor.
```java
this.setEventListener(this);
```
To actually listen for events, do the following.
```java
@EventHandler
public void onSecondPassed(SecondPassedEvent event) { // This fires every second. We can just ouput the fps and tps every second.
    System.out.println("FPS: " + this.getFPS() + " / " + this.getPreferredFPS());
    System.out.println("TPS: " + this.getTPS() + " / " + this.getPreferredTPS());
}
```
We can also listen for each tick and each frame, then use the according method in our handler.
```java
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
    Graphics g = bs.getDrawGraphics();

    handler.render(g);

    g.dispose();
    bs.show();
}
```
### Assets
Let's create a player object. I will be creating a class called ``Player.java``.
```java
public class Player extends JEntity {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(X, Y, 32, 32);
    }
}
```
In the constructor of ``Game.java``, we can add a player to the game.
```java
Player player = new Player(64, 64);
handler.addAsset(player);
```
