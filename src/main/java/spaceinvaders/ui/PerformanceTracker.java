package spaceinvaders.ui;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PerformanceTracker
{
    private final Label performanceLabel;

    public PerformanceTracker()
    {
        performanceLabel = new Label(
                "FPS: 0\nUPS: 0"
        );

        performanceLabel.setTextFill(Color.WHITE);
        performanceLabel.setLayoutX(10);
        performanceLabel.setLayoutY(10);
        performanceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        performanceLabel.setTextFill(Color.YELLOW);
    }

    public void update(int fps, int ups)
    {
        performanceLabel.setText(
                "FPS: " + fps +
                        "\nUPS: " + ups
        );
    }

    public Label getPerformanceLabel()
    {
        return performanceLabel;
    }
}