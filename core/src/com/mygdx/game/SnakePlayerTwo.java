package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Class responsible for the player two, mostly equal to the player one snake
 */
public class SnakePlayerTwo extends Snake {
    public SnakePlayerTwo(float x, float y, float speed, int cellSize) {
        super(x, y, speed, cellSize);
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (!pointingDirection.get(0).equals('r'))
                pointingDirection.set(0, 'l');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (!pointingDirection.get(0).equals('l'))
                pointingDirection.set(0, 'r');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (!pointingDirection.get(0).equals('d'))
                pointingDirection.set(0, 'u');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (!pointingDirection.get(0).equals('u'))
                pointingDirection.set(0, 'd');
        }
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.BLUE);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (com.badlogic.gdx.math.Rectangle bodysegment : bodysegments) {
            renderer.rect(bodysegment.x, bodysegment.y, bodysegment.width,
                    bodysegment.height);
        }
        renderer.end();
    }
}
