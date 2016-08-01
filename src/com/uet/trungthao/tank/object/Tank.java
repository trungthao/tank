package com.uet.trungthao.tank.object;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by JiH on 7/27/2016.
 */
public abstract class Tank {

    protected int x, y;
    protected Image image, imageUp, imageDown, imageLeft, imageRight;
    protected int direction;
    protected int speed = 10;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        imageRight = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "bossyellow_4.png")).getImage();
        imageLeft = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "bossyellow_3.png")).getImage();
        imageDown = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "bossyellow_2.png")).getImage();
        imageUp = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "bossyellow_1.png")).getImage();
        image = imageRight;
        direction = CommonVLs.RIGHT;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.image, x, y, CommonVLs.SIZE_TANK, CommonVLs.SIZE_TANK, null);
    }


    public void move() {
        switch (direction) {
            case CommonVLs.UP:
                if (y <= 0) break;
                --y;
                break;
            case CommonVLs.DOWN:
                if (y >= CommonVLs.HEIGHT_FRAME - 70) break;
                ++y;
                break;
            case CommonVLs.LEFT:
                if (x <= 0) break;
                --x;
                break;
            case CommonVLs.RIGHT:
                if (x >= CommonVLs.WIDTH_SCREEN - 30) break;
                ++x;
                break;
        }
    }

    protected void changeImage() {
        switch (direction) {
            case CommonVLs.DOWN:
                image = imageDown;
                break;
            case CommonVLs.LEFT:
                image = imageLeft;
                break;
            case CommonVLs.UP:
                image = imageUp;
                break;
            case CommonVLs.RIGHT:
                image = imageRight;
        }
    }

    protected boolean collisionCheck() {
        return (y >= CommonVLs.HEIGHT_FRAME - 70) || (x >= CommonVLs.WIDTH_SCREEN - 30) || (x < 0) || (y < 0);
    }

    /**
     *
     */
    protected void drawBoom() {

    }

    protected void changeDirection() {
        direction = new Random().nextInt(4) + 1;
    }

    public boolean collisionCheckTank(Tank tank) {
        return (checkCollisionX(tank) && checkCollisionY(tank));
    }

    private boolean checkCollisionX(Tank tank) {
        boolean bool;
        boolean check;
        do {
            bool = (x <= (tank.getX() + CommonVLs.SIZE_TANK)) && ((x + CommonVLs.SIZE_TANK) >= (tank.getX() + CommonVLs.SIZE_TANK));
            ++x;
            check = true;
        } while (bool);

        do {
            bool = ((x + CommonVLs.SIZE_TANK) >= tank.getX()) && (x <= tank.getX());
            --x;
            check = true;
        } while (bool);
        return check;
    }

    private boolean checkCollisionY(Tank tank) {
        boolean bool;
        boolean check;
        do {
            check = true;
            bool = (y <= tank.getY() + CommonVLs.SIZE_TANK) && (y + CommonVLs.SIZE_TANK >= tank.getY() + CommonVLs.SIZE_TANK);
            ++y;
        } while (bool);
        do {
            bool = (y + CommonVLs.SIZE_TANK >= tank.getY()) && (y <= tank.getY() + CommonVLs.SIZE_TANK);
            --y;
            check = true;
        } while (bool);
        return check;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }
}
