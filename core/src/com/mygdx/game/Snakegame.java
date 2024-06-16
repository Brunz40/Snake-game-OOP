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
    private final int cellsize = 40;
    private float timeElapsed;
    private final float SPEED_INCREMENT_INTERVAL = 10f; // Intervalo para aumentar a velocidade
    private final float SPEED_INCREMENT = 1f; // Incremento de velocidade

    @Override
    public void create() {
        randomGenerator = new Random();
        renderer = new ShapeRenderer();
        player = new Snake(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                1 * cellsize, cellsize);
        playerTwo = new SnakePlayerTwo(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                1 * cellsize, cellsize); // Velocidade inicial reduzida
        fruit = new Fruit(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()), cellsize * 1.2f); // Fruta um pouco maior que os
                                                                                     // segmentos
        timeElapsed = 0;
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        timeElapsed += deltaTime;

        if (timeElapsed >= SPEED_INCREMENT_INTERVAL) {
            player.increaseSpeed(SPEED_INCREMENT);
            playerTwo.increaseSpeed(SPEED_INCREMENT);
            timeElapsed = 0; // Reiniciar o contador de tempo
        }

        player.update();
        playerTwo.update();

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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float newCellSize = Math.min(width, height) / 50f; // Ajuste conforme necessário
        player.resize(newCellSize / 2);
        playerTwo.resize(newCellSize / 2);
        fruit.resize(newCellSize * 0.8f); // Fruta um pouco maior que os segmentos
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
