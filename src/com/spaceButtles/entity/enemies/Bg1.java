package com.spaceButtles.entity.enemies;

import com.spaceButtles.animation.Animation;
import com.spaceButtles.entity.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bg1 extends Enemy {

    public Bg1(int w, int h) {
        super(w, h);

        cost = 10;
        BufferedImage[] sprites = loadImages("/Sprites/Enemies/bg1", 4);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(500);
    }

    public void set2ndAnimation() {
        BufferedImage[] sSprites = loadImages("/Sprites/Enemies/bg1", 4);
        BufferedImage buff = sSprites[0];
        System.arraycopy(sSprites, 1, sSprites, 0, 3);
        sSprites[3] = buff;
        animation.setFrames(sSprites);
    }

    public void update() {
        super.update();
        animation.update();
    }

    public void draw(Graphics2D g) {
        if (alive) {
            g.drawImage(animation.getImage(), (int) x, (int) y, null);
        }
    }

}
