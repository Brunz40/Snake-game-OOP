package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class Snakegame extends ApplicationAdapter {
    ShapeRenderer renderer;
    Snake player;
    Random randomGenerator;
    public void create () {
        randomGenerator = new Random();
        renderer = new ShapeRenderer();
        player = new Snake(randomGenerator.nextInt(Gdx.graphics.getWidth()), randomGenerator.nextInt(Gdx.graphics.getHeight()), 10);
    }

    public void render () {
        player.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.render(renderer);
    }

    public void dispose () {
        renderer.dispose();
    }
}