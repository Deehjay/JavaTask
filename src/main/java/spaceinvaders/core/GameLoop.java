package spaceinvaders.core;

import javafx.animation.AnimationTimer;

public abstract class GameLoop extends AnimationTimer
{
    private static final double TARGET_UPS = 60.0;

    private static final double UPDATE_TIME = 1_000_000_000.0 / TARGET_UPS;

    private long previousTime = 0;

    private double accumulator = 0;

    private long counterTimer = 0;

    private int frameCount = 0;
    private int updateCount = 0;

    private int fps = 0;
    private int ups = 0;

    // 'handle' is called once per JavaFX frame while AnimationTimer is running
    // 'now' is the current timestamp in nanoseconds, which is parameter provided by JavaFX
    @Override
    public void handle(long now)
    {
        // Update previousTime to be the current frame when handle is called for the first time
        if (previousTime == 0)
        {
            previousTime = now;
            counterTimer = now;
            return;
        }

        // How much real time has passed since the last JavaFX frame?
        long elapsed = now - previousTime;
        previousTime = now;

        // Keep that time rather than throwing any away
        accumulator += elapsed;

        // Run as many 60 Hz updates as are required
        while (accumulator >= UPDATE_TIME)
        {
            update();

            updateCount++;

            accumulator -= UPDATE_TIME;
        }

        // Render once per JavaFX frame
        render();

        frameCount++;

        // Calculate FPS and UPS roughly once per second
        long counterElapsed = now - counterTimer;

        if (counterElapsed >= 1_000_000_000L)
        {
            double seconds =
                    counterElapsed / 1_000_000_000.0;

            fps = (int) Math.round(
                    frameCount / seconds
            );

            ups = (int) Math.round(
                    updateCount / seconds
            );

            frameCount = 0;
            updateCount = 0;

            counterTimer = now;
        }
    }

    public abstract void update();

    public abstract void render();

    public int getFps()
    {
        return fps;
    }

    public int getUps()
    {
        return ups;
    }
}