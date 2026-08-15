package spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import spaceinvaders.core.GameLoop;
import spaceinvaders.ui.PerformanceTracker;

public class Main extends Application
{
    private PerformanceTracker performanceTracker;

    @Override
    public void start(Stage stage)
    {
        Pane root = new Pane();

        root.setStyle("-fx-background-color: grey;");

        Circle circle = new Circle();

        circle.setCenterX(400);
        circle.setCenterY(300);
        circle.setRadius(50);
        circle.setFill(Color.WHITE);

        performanceTracker = new PerformanceTracker();

        root.getChildren().add(circle);
        root.getChildren().add(performanceTracker.getPerformanceLabel());

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Space Invaders Clone Thingy");
        stage.setScene(scene);
        stage.show();

        GameLoop gameLoop = new GameLoop() // Anonymous subclass
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

    public static void main(String[] args)
    {
        launch(args);
    }
}
