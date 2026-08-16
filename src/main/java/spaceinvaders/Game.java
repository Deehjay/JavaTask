package spaceinvaders;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import spaceinvaders.core.GameLoop;
import spaceinvaders.entity.Player;
import spaceinvaders.ui.PerformanceTracker;

public class Game
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private final Stage stage;

    private PerformanceTracker performanceTracker;


    private Player player;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    public Game(Stage stage)
    {
        this.stage = stage;
    }

    public void start()
    {
        Pane root = new Pane();
        player = new Player(WINDOW_HEIGHT, WINDOW_WIDTH);
        ImageView background = createBackground();


        performanceTracker = new PerformanceTracker();

        // Assets must be added to the root in layered order, so background must be added first
        root.getChildren().add(background);
        root.getChildren().add(player.getPlayerSprite());
        root.getChildren().add(performanceTracker.getPerformanceLabel());

        Scene scene = new Scene(
                root,
                WINDOW_WIDTH,
                WINDOW_HEIGHT
        );

        scene.setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case A:
                    isMovingLeft = true;
                    break;

                case D:
                    isMovingRight = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event ->
        {
            switch (event.getCode())
            {
                case A:
                    isMovingLeft = false;
                    break;

                case D:
                    isMovingRight = false;
                    break;
            }
        });

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

    private void startGameLoop()
    {
        GameLoop gameLoop = new GameLoop()
        {
            @Override
            public void update()
            {
                player.update(isMovingLeft, isMovingRight);
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