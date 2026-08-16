package spaceinvaders.entity;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Player
{
    private static final double PLAYER_SPEED = 5.0;

    private static final double SPRITE_WIDTH = 64;
    private static final double SPRITE_HEIGHT = 64;
    private static final String SPRITE_PATH = "/sprites/player-ship.png";
    private final ImageView playerSprite;

    private final double windowWidth;


    public Player(double windowHeight, double windowWidth)
    {
        this.windowWidth = windowWidth;

        Image image = new Image(getClass().getResourceAsStream(SPRITE_PATH));
        playerSprite = new ImageView(image);

        playerSprite.setFitWidth(SPRITE_WIDTH);
        playerSprite.setFitHeight(SPRITE_HEIGHT);

        playerSprite.setX((windowWidth / 2) - 32);
        playerSprite.setY(windowHeight - 64);
    }

    public void update(boolean isMovingLeft, boolean isMovingRight)
    {
        updatePosition(isMovingLeft, isMovingRight);
        checkScreenWrap();
    }

    public void updatePosition(boolean isMovingLeft, boolean isMovingRight)
    {
        if (isMovingLeft)
        {
            moveLeft();
        }

        if (isMovingRight)
        {
            moveRight();
        }
    }

    public void checkScreenWrap()
    {
        double playerSpriteWidth = getPlayerWidth();

        if (getPlayerPositionX() > windowWidth)
        {
            setPlayerPositionX(-playerSpriteWidth);
        }

        if (getPlayerPositionX() + playerSpriteWidth < 0)
        {
            setPlayerPositionX(windowWidth);
        }
    }

    public void moveLeft()
    {
        playerSprite.setX(playerSprite.getX() - PLAYER_SPEED);
    }

    public void moveRight()
    {
        playerSprite.setX(playerSprite.getX() + PLAYER_SPEED);
    }

    public double getPlayerPositionX()
    {
        return playerSprite.getX();
    }

    public void setPlayerPositionX(double newPositionX)
    {
        playerSprite.setX(newPositionX);
    }

    public ImageView getPlayerSprite()
    {
        return playerSprite;
    }

    public double getPlayerWidth()
    {
        return SPRITE_WIDTH;
    }

}
