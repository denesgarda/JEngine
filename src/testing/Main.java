package testing;

import testing.objects.Player;

public class Main {
    public static Game game;

    public static void main(String[] args) {
        game = new Game();
        game.setPreferredFPS(60);
        game.setPreferredTPS(60);
        MainScene mainScene = new MainScene();
        game.setScene(mainScene);
        game.start();

        Player player = new Player();
        mainScene.objects.add(player);
    }
}
