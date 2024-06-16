package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
        for (int i = 0; i < bodysegments.size(); i++) {
            renderer.rect(bodysegments.get(i).x, bodysegments.get(i).y, bodysegments.get(i).width,
                    bodysegments.get(i).height);
        }
        renderer.end();
    }
}
