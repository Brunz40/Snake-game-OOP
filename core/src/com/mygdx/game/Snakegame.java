package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

/**
 * Main class for the Snake game application.
 * This class handles the game setup, rendering, and main game loop.
 */
public class Snakegame extends ApplicationAdapter {
    private ShapeRenderer renderer;
    private Snake player;
    private SnakePlayerTwo playerTwo;
    private Fruit fruit;
    private Random randomGenerator;
    private int cellsize = 20;
    private float timeElapsed;
    private final float SPEED_INCREMENT_INTERVAL = 10f; // Interval for speed increase
    private final float SPEED_INCREMENT = 1f; // Speed increment
    private boolean gameOver;
    private Music backgroundMusic;
    private Sound eatSound;

    /**
     * Called when the application is first created.
     * Initializes the game objects and starts the background music.
     */
    @Override
    public void create() {
        renderer = new ShapeRenderer();
        randomGenerator = new Random();
        player = new Snake(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                4 * cellsize, cellsize);
        playerTwo = new SnakePlayerTwo(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                4 * cellsize, cellsize); // Initial speed reduced
        eatSound = Gdx.audio.newSound(Gdx.files.internal("eat_fruit.mp3"));
        fruit = new Fruit(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()), cellsize * 1.2f, eatSound); // Fruit slightly larger
                                                                                               // than segments
        timeElapsed = 0;
        gameOver = false;

        // Load and play background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound_game.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    /**
     * Called to update and render the game at each frame.
     * Handles game logic, input, and rendering.
     */
    @Override
    public void render() {
        if (gameOver) {
            return; // If the game is over, do nothing
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        timeElapsed += deltaTime;

        if (timeElapsed >= SPEED_INCREMENT_INTERVAL) {
            player.increaseSpeed(SPEED_INCREMENT);
            playerTwo.increaseSpeed(SPEED_INCREMENT);
            timeElapsed = 0; // Reset the time counter
        }

        player.update();
        playerTwo.update();

        boolean playerHitPlayerTwo = player.checkHeadCollisionWithOtherSnake(playerTwo);
        boolean playerTwoHitPlayer = playerTwo.checkHeadCollisionWithOtherSnake(player);

        if (playerHitPlayerTwo && playerTwoHitPlayer) {
            player.alive = false;
            playerTwo.alive = false;
            gameOver = true;
            System.out.println("Oh no, you both lost");
        } else if (playerHitPlayerTwo) {
            player.alive = false;
            gameOver = true;
            System.out.println("Blue win!");
        } else if (playerTwoHitPlayer) {
            playerTwo.alive = false;
            gameOver = true;
            System.out.println("Green win!");
        }

        if (gameOver) {
            backgroundMusic.stop();
            Gdx.app.exit();
        }

        if (fruit.isEaten(player)) {
            player.grow();
            fruit.reposition(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                    randomGenerator.nextInt(Gdx.graphics.getHeight()));
        }

        if (fruit.isEaten(playerTwo)) {
            playerTwo.grow();
            fruit.reposition(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                    randomGenerator.nextInt(Gdx.graphics.getHeight()));
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.render(renderer);
        playerTwo.render(renderer);
        fruit.render(renderer);
    }

    /**
     * Called when the application is resized.
     * Adjusts the size of game objects.
     *
     * @param width  the new width
     * @param height the new height
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float newCellSize = Math.min(width, height) / 50f; // Adjust as needed
        player.resize(newCellSize / 2);
        playerTwo.resize(newCellSize / 2);
        fruit.resize(newCellSize * 0.8f); // Fruit slightly larger than segments
    }

    /**
     * Called when the application is destroyed.
     * Releases all resources.
     */
    @Override
    public void dispose() {
        renderer.dispose();
        backgroundMusic.dispose(); // Release music resources
        eatSound.dispose(); // Release sound resources
    }
}
