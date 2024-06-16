package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class responsible for handling the fruit
 */
public class Fruit {
    private Circle fruit;

    /**
     * constructor method responsible for creating the fruit
     * @param x fruit coordinate on the x-axis, must be between 0 and screen width
     * @param y fruit coordinate on the x-axis, must be between 0 and screen height
     * @param size fruit radius
     */
    public Fruit(float x, float y, float size) {
        this.fruit = new Circle(x, y, size);
    }

    /**
     * method responsible for rendering the fruit at the screen
     * @param renderer rendering unit responsible for output the fruit drawing
     */
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(fruit.x, fruit.y, fruit.radius);
        renderer.end();
    }

    /**
     * method responsible for checking if the fruit got eaten
     * @param snake snake player going to be tested for collision with the fruit
     * @return will return True whenever the snake body is touching the fruit
     */
    public boolean isEaten(Snake snake) {
        for (Rectangle segment : snake.getBodySegments()) {
            if (circleIntersectsRectangle(fruit, segment)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method responsible for checking if a rectangle(snake body segment) and a circle(fruit) collided
     * @param circle circle to be tested for collision
     * @param rectangle rectangle to be tested for collision
     * @return will return true if the polygons body intersect
     */
    private boolean circleIntersectsRectangle(Circle circle, Rectangle rectangle) {
        float closestX = clamp(circle.x, rectangle.x, rectangle.x + rectangle.width);
        float closestY = clamp(circle.y, rectangle.y, rectangle.y + rectangle.height);

        float distanceX = circle.x - closestX;
        float distanceY = circle.y - closestY;

        return (distanceX * distanceX + distanceY * distanceY) < (circle.radius * circle.radius);
    }

    /**
     * submethod of circleIntersectsRectangle responsible for selecting the minimum value for distance

     */
    private float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        } else return Math.min(value, max);
    }

    /**
     * method responsible for setting a new position for the fruit
     * @param x new value for x coordinate
     * @param y new value for y coordinate
     */
    public void reposition(float x, float y) {
        this.fruit.setPosition(x, y);
    }

    /**
     * method responsible for altering the size of the fruit
     * @param newSize new value for the radius of the fruit
     */
    public void resize(float newSize) {
        this.fruit.setRadius(newSize);
    }
}
