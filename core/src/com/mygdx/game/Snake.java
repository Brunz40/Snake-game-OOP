package com.mygdx.game;
//imports from gdx
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
//imports from otgher utilities
import java.util.ArrayList;
import java.util.List;
/**
 * Class responsible for the snake
 */
public class Snake {
    //snake characteristics
    protected int size;
    protected float cellSize;
    //movement related
    protected float baseSpeed;
    protected float currentSpeed;
    protected List<Rectangle> bodySegments;
    protected List<Character> pointingDirection;
    public boolean alive;
    /**
     * method responsible for creating the snake
     * @param x initial x coordinate
     * @param y initial y coordinate
     * @param speed initial speed
     * @param cellsize body segment size, which should be the same as the cell size
     */
    public Snake(float x, float y, float speed, float cellsize) {
        this.size = 1;
        this.cellSize = cellsize;
        this.baseSpeed = speed;
        this.currentSpeed = baseSpeed;
        this.bodySegments = new ArrayList<>();
        this.pointingDirection = new ArrayList<>();
        this.alive = true;
        this.bodySegments.add(new Rectangle(x*cellsize, y*cellsize, cellSize, cellSize));
        this.pointingDirection.add('u');
    }
    /**
     * method responsible for handling input, checking if the new direction is feasible
     * and changing the head pointing direction accordingly
     */
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
    /**
     * method responsible for updating the snake state,
     * will move every body segment and update its pointing direction accordingly
     */
    public void update() {
        if (!alive)
            return;
        float deltaSpeed = cellSize*Gdx.graphics.getDeltaTime() * currentSpeed;
        handleInput();
        // Atualizar a posição da cabeça
        Rectangle head = bodySegments.get(0);
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
        for (int i = bodySegments.size() - 1; i > 0; i--) {
            bodySegments.get(i).setPosition(bodySegments.get(i - 1).x, bodySegments.get(i - 1).y);
            pointingDirection.set(i, pointingDirection.get(i - 1));
        }
    }
    /**
     * method responsible for rendering the snake at the screen,
     * will address each body segment separatedly
     * @param renderer rendering unit responsible for output the snake drawing
     */
    public void render(ShapeRenderer renderer) {
        if (!alive)
            return;

        renderer.setColor(Color.GREEN);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Rectangle bodysegment : bodySegments) {
            renderer.rect(bodysegment.x, bodysegment.y, bodysegment.width,
                    bodysegment.height);
        }
        renderer.end();
    }
    /**
     * @return the list containing the snakes body segments
     */
    public List<Rectangle> getBodySegments() {
        return bodySegments;
    }
    /**
     * method responsible for adding new body segments to the snake,
     * asserting they are at the end of the body and point to the right direction
     */
    public void grow() {
        Rectangle lastSegment = bodySegments.get(bodySegments.size() - 1);
        char direction = pointingDirection.get(pointingDirection.size() - 1);
        float newX = lastSegment.x;
        float newY = lastSegment.y;
        int GROWTH_DISTANCE = 60;
        switch (direction) {
            case 'u':
                newY -= (cellSize + GROWTH_DISTANCE);
                break;
            case 'd':
                newY += (cellSize + GROWTH_DISTANCE);
                break;
            case 'l':
                newX += (cellSize + GROWTH_DISTANCE);
                break;
            case 'r':
                newX -= (cellSize + GROWTH_DISTANCE);
                break;
        }
        bodySegments.add(new Rectangle(newX, newY, cellSize, cellSize));
        pointingDirection.add(direction);
    }
    /**
     * method responsible for changing the snake speed
     * @param increment amount per wich the speed will change
     */
    public void increaseSpeed(float increment) {
        this.currentSpeed += increment;
    }

    public boolean checkHeadCollisionWithOtherSnake(Snake other) {
        Rectangle head = bodySegments.get(0);
        for (int i = 1; i < other.getBodySegments().size(); i++) {
            if (head.overlaps(other.getBodySegments().get(i))) {
                return true;
            }
        }
        return false;
    }
}
