package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import java.util.ArrayList;
import java.util.List;
public class Snake {
    protected int size;
    protected int width;
    protected float baseSpeed;
    protected List<Circle> bodysegments;
    protected List<Character> pointingDirection;

    public Snake(float x, float y, float speed, int cellsize) {
        size = 1;
        width = cellsize;
        baseSpeed = speed;
        bodysegments = new ArrayList<>();
        bodysegments.add(new Circle(x, y, width));
        pointingDirection = new ArrayList<>();
        pointingDirection.add('u');
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
        for (int i = 0; i < bodysegments.size(); i++) {
            switch (pointingDirection.get(i)) {
                case 'u':
                    bodysegments.get(i).setY(bodysegments.get(i).y + deltaSpeed);
                    if(bodysegments.get(i).y > Gdx.graphics.getHeight()){
                        bodysegments.get(i).setY(0);
                    }
                    break;
                case 'l':
                    bodysegments.get(i).setX(bodysegments.get(i).x - deltaSpeed);
                    if(bodysegments.get(i).x  < 0){
                        bodysegments.get(i).setX(Gdx.graphics.getWidth());
                    }
                    break;
                case 'r':
                    bodysegments.get(i).setX(bodysegments.get(i).x + deltaSpeed);
                    if(bodysegments.get(i).x  > Gdx.graphics.getWidth()){
                        bodysegments.get(i).setX(0);
                    }
                    break;
                case 'd':
                    bodysegments.get(i).setY(bodysegments.get(i).y - deltaSpeed);
                    if(bodysegments.get(i).y  < 0){
                        bodysegments.get(i).setY(Gdx.graphics.getHeight());
                    }
                    break;
                default:
            }
        }
        for (int i=1; i<bodysegments.size(); i++) {
            pointingDirection.set(i, pointingDirection.get(i-1));
        }
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.GREEN);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < bodysegments.size(); i++) {
            renderer.circle(bodysegments.get(i).x, bodysegments.get(i).y, bodysegments.get(i).radius);
        }
        renderer.end();
    }
}