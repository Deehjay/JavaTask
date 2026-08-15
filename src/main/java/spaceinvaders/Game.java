package spaceinvaders;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import spaceinvaders.core.GameLoop;
import spaceinvaders.ui.PerformanceTracker;

public class Game
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private final Stage stage;

    private PerformanceTracker performanceTracker;

    public Game(Stage stage)
    {
        this.stage = stage;
    }

    public void start()
    {
        Pane root = new Pane();

        ImageView background = createBackground();
        ImageView player = createPlayer();

        performanceTracker = new PerformanceTracker();

        // Assets must be added to the root in layered order, so background must be added first
        root.getChildren().add(background);
        root.getChildren().add(player);
        root.getChildren().add(performanceTracker.getPerformanceLabel());

        Scene scene = new Scene(
                root,
                WINDOW_WIDTH,
                WINDOW_HEIGHT
        );

        stage.setTitle("Space Invaders Clone Thingy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        startGameLoop();
    }

    // Creates the background image by loading it from the resources folder
    private ImageView createBackground()
    {
        Image backgroundImage = new Image(getClass().getResourceAsStream("/game-background.jpg"));

        // ImageView is the object JavaFX uses to load images
        ImageView background = new ImageView(backgroundImage);

        background.setFitWidth(WINDOW_WIDTH);
        background.setFitHeight(WINDOW_HEIGHT);

        return background;
    }

    // Creates the player sprite by loading it from the resources folder
    private ImageView createPlayer()
    {
        Image playerImage = new Image(getClass().getResourceAsStream("/sprites/player-ship.png"));

        ImageView player = new ImageView(playerImage);

        // Display the player as a 64x64 sprite
        player.setFitWidth(64);
        player.setFitHeight(64);

        // Position the player near the bottom-centre of the screen
        player.setX((WINDOW_WIDTH / 2.0) - 32); // Offset required (half of sprite size)
        player.setY(WINDOW_HEIGHT - 64);

        return player;
    }

    private void startGameLoop()
    {
        GameLoop gameLoop = new GameLoop()
        {
            @Override
            public void update()
            {

            }

            @Override
            public void render()
            {
                performanceTracker.update(getFps(), getUps());
            }
        };

        gameLoop.start();
    }
}