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
    protected int speed = 4;
    protected boolean hidden;

    // (35,35) là vị trí đầu tiên của playerTank
    // cần đặt như vậy vì nếu không khi enemy va chạm vào player nó sẽ nhảy về (0,0)
    int oldX = 35, oldY = 35;

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
        hidden = false;
    }

    public void draw(Graphics2D g2d) {
        if (!hidden) {
            g2d.drawImage(this.image, x, y, CommonVLs.SIZE_TANK, CommonVLs.SIZE_TANK, null);
        }
    }


    public void move() {
        oldX = this.x;
        oldY = this.y;
        switch (direction) {
            case CommonVLs.UP:
                y -= speed;
                break;
            case CommonVLs.DOWN:
                y += speed;
                break;
            case CommonVLs.LEFT:
                x -= speed;
                break;
            case CommonVLs.RIGHT:
                x += speed;
        }
    }

    public void noMove() {
        this.x = oldX;
        this.y = oldY;
    }

    public void changeImage() {
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

    protected boolean isObjInside(int xObj, int yObj) {
        if (isPointInside(xObj, yObj)
                || isPointInside(xObj + CommonVLs.SIZE_TANK, yObj)
                || isPointInside(xObj, yObj + CommonVLs.SIZE_TANK)
                || isPointInside(xObj + CommonVLs.SIZE_TANK, yObj + CommonVLs.SIZE_TANK)) {
            return true;
        }
        return false;
    }

    private boolean isPointInside(int xObj, int yObj) {
        int bottomLeft = this.y + CommonVLs.SIZE_TANK;
        int topRight = this.x + CommonVLs.SIZE_TANK;

        if (xObj >= this.x
                && yObj >= this.y
                && xObj <= topRight
                && yObj <= bottomLeft) {
            return true;
        }
        return false;
    }

    public void changeDirection() {
        direction = new Random().nextInt(4) + 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean getHidden() {
        return hidden;
    }
}
