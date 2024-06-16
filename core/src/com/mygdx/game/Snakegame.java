package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Snakegame extends ApplicationAdapter {
    private ShapeRenderer renderer;
    private Snake player;
    private SnakePlayerTwo playerTwo;
    private Fruit fruit;
    private Random randomGenerator;
    private final int cellsize = 10;
    private float timeElapsed;
    private final float SPEED_INCREMENT_INTERVAL = 10f; // Intervalo para aumentar a velocidade
    private final float SPEED_INCREMENT = 1f; // Incremento de velocidade

    @Override
    public void create() {
        randomGenerator = new Random();
        renderer = new ShapeRenderer();
        player = new Snake(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                 5*cellsize, cellsize);
        playerTwo = new SnakePlayerTwo(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                5*cellsize, cellsize);
        fruit = new Fruit(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()), cellsize );

        timeElapsed = 0;
    }

    public void updateGame() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        timeElapsed += deltaTime;
        if (timeElapsed >= SPEED_INCREMENT_INTERVAL) {
            player.increaseSpeed(SPEED_INCREMENT);
            playerTwo.increaseSpeed(SPEED_INCREMENT);
            timeElapsed = 0;
        }
        if (fruit.isEaten(player)) {
            player.grow();
            fruit.reposition(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                    randomGenerator.nextInt(Gdx.graphics.getHeight()));
        }else if (fruit.isEaten(playerTwo)) {
                playerTwo.grow();
                fruit.reposition(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                    randomGenerator.nextInt(Gdx.graphics.getHeight()));
        }
    }

    @Override
    public void render() {
        player.update();
        playerTwo.update();
        updateGame();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.render(renderer);
        playerTwo.render(renderer);
        fruit.render(renderer);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
