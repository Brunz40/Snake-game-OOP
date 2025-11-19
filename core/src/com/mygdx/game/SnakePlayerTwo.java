package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Class responsible for the player two, who inherits most it's methods from player one
 */
public class SnakePlayerTwo extends Snake {
    public SnakePlayerTwo(float x, float y, float speed, int cellSize) {
        super(x, y, speed, cellSize);
    }

    public void handleInput() {
        char currentDirection=pointingDirection.get(0);
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)&&currentDirection!='r') {
            pointingDirection.set(0, 'l');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)&&currentDirection!='l') {
            pointingDirection.set(0, 'r');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)&&currentDirection!='d') {
            pointingDirection.set(0, 'u');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)&&currentDirection!='u') {
            pointingDirection.set(0, 'd');
        }
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.BLUE);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (com.badlogic.gdx.math.Rectangle bodySegment : bodySegments) {
            renderer.rect(bodySegment.x, bodySegment.y, bodySegment.width,
                    bodySegment.height);
        }
        renderer.end();
    }
}
