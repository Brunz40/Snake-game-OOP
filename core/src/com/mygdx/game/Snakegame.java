package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Snakegame extends ApplicationAdapter {
    private ShapeRenderer renderer;
    private Snake player;
    private SnakePlayerTwo playerTwo;
    private Fruit fruit;
    private Random randomGenerator;
    private final int cellsize = 10;
    private float timeElapsed;
    private final float SPEED_INCREMENT_INTERVAL = 10f;
    private final float SPEED_INCREMENT = 1f;
    private boolean gameOver;
    private Music backgroundMusic;
    private Sound eatSound;
    public int scorePlayer1;
    public int scorePlayer2;
    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void create() {
        renderer = new ShapeRenderer();
        randomGenerator = new Random();
        player = new Snake(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                4 * cellsize, cellsize);
        playerTwo = new SnakePlayerTwo(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()),
                4 * cellsize, cellsize); // Velocidade inicial reduzida
        eatSound = Gdx.audio.newSound(Gdx.files.internal("eat_fruit.mp3"));
        fruit = new Fruit(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                randomGenerator.nextInt(Gdx.graphics.getHeight()), cellsize * 1.2f, eatSound); // Fruta um pouco maior
        scorePlayer1=0;
        scorePlayer2=0;
        timeElapsed = 0;
        gameOver = false;

        // Carregar e tocar a música de fundo
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound_game.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render() {
        if (gameOver) {
            return; // Se o jogo acabou, não faz mais nada
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        timeElapsed += deltaTime;

        if (timeElapsed >= SPEED_INCREMENT_INTERVAL) {
            player.increaseSpeed(SPEED_INCREMENT);
            playerTwo.increaseSpeed(SPEED_INCREMENT);
            timeElapsed = 0; // Reiniciar o contador de tempo
        }

        player.update();
        playerTwo.update();

        boolean playerHitPlayerTwo = player.checkHeadCollisionWithOtherSnake(playerTwo);
        boolean playerTwoHitPlayer = playerTwo.checkHeadCollisionWithOtherSnake(player);

        if (playerHitPlayerTwo && playerTwoHitPlayer) {
            player.alive = false;
            playerTwo.alive = false;
            gameOver = true;
            System.out.println("Oh no, you both lost");
        } else if (playerHitPlayerTwo) {
            player.alive = false;
            gameOver = true;
            System.out.println("Blue win !");
        } else if (playerTwoHitPlayer) {
            playerTwo.alive = false;
            gameOver = true;
            System.out.println("Green win !");
        }

        if (gameOver) {
            backgroundMusic.stop();
            Gdx.app.exit();
        }

        if (fruit.isEaten(player)) {
            scorePlayer1++;
            player.grow();
            fruit.reposition(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                    randomGenerator.nextInt(Gdx.graphics.getHeight()));
        }else if (fruit.isEaten(playerTwo)) {
            scorePlayer2++;
            playerTwo.grow();
            fruit.reposition(randomGenerator.nextInt(Gdx.graphics.getWidth()),
                    randomGenerator.nextInt(Gdx.graphics.getHeight()));
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "Player one Score: " + scorePlayer1, 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Player two Score: " + scorePlayer2, Gdx.graphics.getWidth()-135, Gdx.graphics.getHeight() - 10);
        batch.end();
        player.render(renderer);
        playerTwo.render(renderer);
        fruit.render(renderer);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        backgroundMusic.dispose();
        eatSound.dispose();
        font.dispose();
        batch.dispose();
    }
}
