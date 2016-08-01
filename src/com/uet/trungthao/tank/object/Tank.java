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
    private boolean notMove;

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
        if (notMove) return;
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

    protected void changeDirection() {
        direction = new Random().nextInt(4)+1;
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
        return (checkCollisionX(tank) && checkCollisionY(tank));
    }

    private boolean checkCollisionX(Tank tank) {
        boolean bool;
        bool = (x <= (tank.getX() + CommonVLs.SIZE_TANK)) && ((x + CommonVLs.SIZE_TANK) >= (tank.getX() + CommonVLs.SIZE_TANK));
        if (bool) {
            return true;
        }
        bool = ((x + CommonVLs.SIZE_TANK) >= tank.getX()) && (x <= tank.getX());
        if (bool) {
            return true;
        }
        return false;
    }

    private boolean checkCollisionY(Tank tank) {
        boolean bool;
        bool = (y <= tank.getY() + CommonVLs.SIZE_TANK) && (y + CommonVLs.SIZE_TANK >= tank.getY() + CommonVLs.SIZE_TANK);
        if (bool) {
            return true;
        }
        bool = (y + CommonVLs.SIZE_TANK >= tank.getY()) && (y <= tank.getY() + CommonVLs.SIZE_TANK);
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

    public void setNotMove(boolean notMove) {
        this.notMove = notMove;
    }
}
