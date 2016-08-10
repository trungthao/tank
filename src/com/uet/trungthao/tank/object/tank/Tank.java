package com.uet.trungthao.tank.object.tank;

import com.uet.trungthao.tank.commons.CommonVLs;

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
    protected int collisionTank;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        CommonVLs commonVLs = new CommonVLs();
        imageRight = commonVLs.getImage("bossyellow_4.png");
        imageLeft = commonVLs.getImage("bossyellow_3.png");
        imageDown = commonVLs.getImage("bossyellow_2.png");
        imageUp = commonVLs.getImage("bossyellow_1.png");
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

    /**
     * Kiểm tra xem tank có vượt biên không
     * @return
     * true nếu đến biên
     * false nếu chưa đến biên
     */
    protected boolean collisionCheck() {
        return (y >= CommonVLs.HEIGHT_FRAME - 70) || (x >= CommonVLs.WIDTH_SCREEN - 30) || (x <= 0) || (y <= 0);
    }

    /**
     *
     */
    protected void drawBoom() {

    }

    /**
     * Kiểm tra 2 tank có va chạm nhau không
     * @param tank
     * @return
     * true nếu có va chạm
     * false nếu không va chạm
     */
    public boolean collisionCheckTank(Tank tank) {
        return (checkCollisionX(tank.getX()) && checkCollisionY(tank.getY()));
    }

    private boolean checkCollisionX(int x) {
        boolean bool;
        bool = (this.x <= (x + CommonVLs.SIZE_TANK)) && ((this.x + CommonVLs.SIZE_TANK) >= (x + CommonVLs.SIZE_TANK));
        if (bool) {
            return true;
        }
        bool = ((this.x + CommonVLs.SIZE_TANK) >= x) && (this.x <= x);
        if (bool) {
            return true;
        }
        return false;
    }

    private boolean checkCollisionY(int y) {
        boolean bool;
        bool = (this.y <= y + CommonVLs.SIZE_TANK) && (this.y + CommonVLs.SIZE_TANK >= y + CommonVLs.SIZE_TANK);
        if (bool) {
            return true;
        }
        bool = (this.y + CommonVLs.SIZE_TANK >= y) && (this.y <= y + CommonVLs.SIZE_TANK);
        if (bool) {
            return true;
        }
        return false;
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

    public int getSpeed() {
        return speed;
    }

    protected void changeDirection() {
        direction = new Random().nextInt(4)+1;
    }
}
