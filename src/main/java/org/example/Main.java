import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application
{
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

        root.getChildren().add(circle);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("My JavaFX Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}