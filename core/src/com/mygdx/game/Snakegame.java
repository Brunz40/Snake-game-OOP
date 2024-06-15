package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class Snakegame extends ApplicationAdapter {
    final int cellsize=10;
    ShapeRenderer renderer;
    Snake player;
    SnakePlayerTwo playerTwo;
    Random randomGenerator;
    public void create () {
        randomGenerator = new Random();
        renderer = new ShapeRenderer();
        player = new Snake(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                5*cellsize,cellsize);
        playerTwo = new SnakePlayerTwo(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                5*cellsize,cellsize);
    }

    public void render () {
        player.update();
        playerTwo.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.render(renderer);
        playerTwo.render(renderer);
    }

    public void dispose () {
        renderer.dispose();
    }
}