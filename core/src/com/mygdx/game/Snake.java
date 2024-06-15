package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
public class Snake {
    int size;
    int width;
    float baseSpeed;
    List<Float> xBodyPosition;
    List<Float> yBodyPosition;
    List<Character> pointingDirection;

    public Snake(float x, float y, float speed) {
        this.xBodyPosition = new ArrayList<>();
        xBodyPosition.add(x);
        this.yBodyPosition = new ArrayList<>();
        yBodyPosition.add(y);
        this.pointingDirection = new ArrayList<>();
        pointingDirection.add('u');
        this.size = 2;
        this.width = 10;
        this.baseSpeed = speed;
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if(!pointingDirection.get(0).equals('r'))
                pointingDirection.set(0, 'l');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(!pointingDirection.get(0).equals('l'))
                pointingDirection.set(0, 'r');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(!pointingDirection.get(0).equals('d'))
                pointingDirection.set(0, 'u');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(!pointingDirection.get(0).equals('u'))
                pointingDirection.set(0, 'd');
        }
    }

    public void update() {
        float deltaSpeed = Gdx.graphics.getDeltaTime()*baseSpeed;
        handleInput();
        for (int i = 0; i < xBodyPosition.size(); i++) {
            switch (pointingDirection.get(i)) {
                case 'u':
                    yBodyPosition.set(i, yBodyPosition.get(i) + deltaSpeed);
                    break;
                case 'l':
                    xBodyPosition.set(i, xBodyPosition.get(i) - deltaSpeed);
                    break;
                case 'r':
                    xBodyPosition.set(i, xBodyPosition.get(i) + deltaSpeed);
                    break;
                case 'd':
                    yBodyPosition.set(i, yBodyPosition.get(i) - deltaSpeed);
                    break;
                default:
            }
        }
        for (int i=1; i<yBodyPosition.size(); i++) {
            pointingDirection.set(i, pointingDirection.get(i-1));
        }
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.GREEN);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < xBodyPosition.size(); i++) {
            renderer.circle(xBodyPosition.get(i), yBodyPosition.get(i), width);
        }

        renderer.end();
    }
}