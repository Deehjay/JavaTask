package spaceinvaders.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet
{
    private static final double BULLET_SPEED = 5.0;
    private static final double BULLET_HEIGHT = 10.0;
    private static final double BULLET_WIDTH = 5.0;

    private final Rectangle bulletShape;

    public Bullet(double playerCentreX, double playerPositionY)
    {

        bulletShape = new Rectangle(BULLET_WIDTH, BULLET_HEIGHT);
        bulletShape.setFill(Color.WHITE);

        bulletShape.setX((playerCentreX - BULLET_WIDTH / 2) - 2); // Slight offset due to location of gun on sprite
        bulletShape.setY(playerPositionY - BULLET_HEIGHT);
    }

    public void update()
    {
        updatePosition();
    }

    public void updatePosition()
    {
        bulletShape.setY(
                bulletShape.getY() - BULLET_SPEED
        );

    }

    public Rectangle getBulletShape()
    {
        return bulletShape;
    }

    public double getBulletPosition() { return bulletShape.getY(); }

    public void setBulletPosition(double newPosition) { bulletShape.setY(newPosition); }
}
