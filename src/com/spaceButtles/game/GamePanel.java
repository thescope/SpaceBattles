package com.spaceButtles.game;

import com.spaceButtles.gameState.GameStateManager;
import com.spaceButtles.inputHandler.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

    // dimensions
    public static final int WIDTH = 240;
    public static final int HEIGHT = 280;
    public static final int SCALE = 2;

    // game thread
    private Thread thread;
    private boolean running;

    // image
    private BufferedImage image;
    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;
        gsm = new GameStateManager();
    }

    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        // game loop
        while (running) {
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            final int FPS = 60;
            final long targetTime = 1000 / FPS;
            wait = targetTime - elapsed / 1000000;
            if (wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gsm.update();
    }

    private void draw() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        InputHandler.setKey(k.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent k) {
        InputHandler.setKey(k.getKeyCode(), false);
    }

}
