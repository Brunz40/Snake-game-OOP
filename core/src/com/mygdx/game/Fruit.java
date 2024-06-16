package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Fruit {
    private Circle fruit;
    private float size;

    public Fruit(float x, float y, float size) {
        this.size = size;
        this.fruit = new Circle(x, y, size);
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(fruit.x, fruit.y, fruit.radius);
        renderer.end();
    }

    public boolean isEaten(Snake snake) {
        for (Rectangle segment : snake.getBodySegments()) {
            if (circleIntersectsRectangle(fruit, segment)) {
                return true;
            }
        }
        return false;
    }

    private boolean circleIntersectsRectangle(Circle circle, Rectangle rectangle) {
        float closestX = clamp(circle.x, rectangle.x, rectangle.x + rectangle.width);
        float closestY = clamp(circle.y, rectangle.y, rectangle.y + rectangle.height);

        float distanceX = circle.x - closestX;
        float distanceY = circle.y - closestY;

        return (distanceX * distanceX + distanceY * distanceY) < (circle.radius * circle.radius);
    }

    private float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }

    public void reposition(float x, float y) {
        this.fruit.setPosition(x, y);
    }

    public Circle getFruit() {
        return fruit;
    }

    public void resize(float newSize) {
        this.size = newSize;
        this.fruit.setRadius(newSize);
    }
}
