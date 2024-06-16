package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    protected int size;
    protected float width;
    protected float baseSpeed;
    protected float currentSpeed;
    protected List<Rectangle> bodysegments;
    protected List<Character> pointingDirection;
    private final int GROWTH_DISTANCE = 60; // Aumentando a distância para a nova célula

    public Snake(float x, float y, float speed, float cellsize) {
        this.size = 1; // Tamanho inicial da cobra
        this.width = cellsize; // Diminuindo o tamanho das bolinhas
        this.baseSpeed = speed;
        this.currentSpeed = baseSpeed;
        this.bodysegments = new ArrayList<>();
        this.pointingDirection = new ArrayList<>();

        // Adicionando a cabeça da cobra
        this.bodysegments.add(new Rectangle(x, y, width, width));
        this.pointingDirection.add('u');
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (!pointingDirection.get(0).equals('r'))
                pointingDirection.set(0, 'l');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (!pointingDirection.get(0).equals('l'))
                pointingDirection.set(0, 'r');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (!pointingDirection.get(0).equals('d'))
                pointingDirection.set(0, 'u');
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (!pointingDirection.get(0).equals('u'))
                pointingDirection.set(0, 'd');
        }
    }

    public void update() {
        float deltaSpeed = Gdx.graphics.getDeltaTime() * currentSpeed;
        handleInput();

        // Guardar as posições atuais
        float[] prevX = new float[bodysegments.size()];
        float[] prevY = new float[bodysegments.size()];
        for (int i = 0; i < bodysegments.size(); i++) {
            prevX[i] = bodysegments.get(i).x;
            prevY[i] = bodysegments.get(i).y;
        }

        // Atualizar a posição da cabeça
        Rectangle head = bodysegments.get(0);
        switch (pointingDirection.get(0)) {
            case 'u':
                head.setY(head.y + deltaSpeed);
                if (head.y > Gdx.graphics.getHeight()) {
                    head.setY(0);
                }
                break;
            case 'd':
                head.setY(head.y - deltaSpeed);
                if (head.y < 0) {
                    head.setY(Gdx.graphics.getHeight());
                }
                break;
            case 'l':
                head.setX(head.x - deltaSpeed);
                if (head.x < 0) {
                    head.setX(Gdx.graphics.getWidth());
                }
                break;
            case 'r':
                head.setX(head.x + deltaSpeed);
                if (head.x > Gdx.graphics.getWidth()) {
                    head.setX(0);
                }
                break;
        }

        // Atualizar as posições das outras partes do corpo
        for (int i = 1; i < bodysegments.size(); i++) {
            bodysegments.get(i).setPosition(prevX[i - 1], prevY[i - 1]);
            pointingDirection.set(i, pointingDirection.get(i - 1));
        }
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.GREEN);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < bodysegments.size(); i++) {
            renderer.rect(bodysegments.get(i).x, bodysegments.get(i).y, bodysegments.get(i).width,
                    bodysegments.get(i).height);
        }
        renderer.end();
    }

    public List<Rectangle> getBodySegments() {
        return bodysegments;
    }

    public void grow() {
        Rectangle lastSegment = bodysegments.get(bodysegments.size() - 1);
        char direction = pointingDirection.get(pointingDirection.size() - 1);
        float newX = lastSegment.x;
        float newY = lastSegment.y;

        switch (direction) {
            case 'u':
                newY -= (width + GROWTH_DISTANCE);
                break;
            case 'd':
                newY += (width + GROWTH_DISTANCE);
                break;
            case 'l':
                newX += (width + GROWTH_DISTANCE);
                break;
            case 'r':
                newX -= (width + GROWTH_DISTANCE);
                break;
        }

        bodysegments.add(new Rectangle(newX, newY, width, width));
        pointingDirection.add(direction);
    }

    public void resize(float newWidth) {
        this.width = newWidth;
        for (Rectangle segment : bodysegments) {
            segment.setSize(newWidth, newWidth);
        }
    }

    public void increaseSpeed(float increment) {
        this.currentSpeed += increment;
    }
}
