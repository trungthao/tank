package com.uet.trungthao.tank.object;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by JiH on 7/29/2016.
 */
public class Bullet {

    private static final int deviation = 3;

    private int x,y;
    private Image image;
    public static int speed = 3;
    private int direction;

    public Bullet(int x, int y, int direction) {
        this.direction = direction;
        switch (direction) {
            case CommonVLs.DOWN:
                this.x = x + CommonVLs.SIZE_TANK/2 - deviation;
                this.y = y + CommonVLs.SIZE_TANK;
                break;
            case CommonVLs.UP:
                this.y = y;
                this.x = x + CommonVLs.SIZE_TANK/2 - deviation;
                break;
            case CommonVLs.LEFT:
                this.x = x;
                this.y = y + CommonVLs.SIZE_TANK/2 - deviation;
                break;
            case CommonVLs.RIGHT:
                this.x = x + CommonVLs.SIZE_TANK;
                this.y = y + CommonVLs.SIZE_TANK/2 - deviation;
                break;
        }
        image = new ImageIcon(getClass().getResource("/RESOURCE/Image/bullet.png")).getImage();
    }

    public boolean draw(Graphics2D g2d) {
        g2d.drawImage(image,x,y, CommonVLs.SIZE_BULLET,CommonVLs.SIZE_BULLET,null);
        switch (direction) {
            case CommonVLs.LEFT:
                if (x < 0) return false;
                x--; break;
            case CommonVLs.DOWN:
                if (y > CommonVLs.HEIGHT_FRAME) return false;
                y++; break;
            case CommonVLs.RIGHT:
                if (x > CommonVLs.WIDTH_SCREEN) return false;
                x++; break;
            case CommonVLs.UP:
                if (y < 0) return false;
                --y; break;
        }
        return true;
    }

    public int check(ArrayList<TankOther> arrayList) {
        for (int i = 0; i < arrayList.size(); ++i) {
            Tank tank = arrayList.get(i);
            if ((x >= tank.getX()) && (x <= tank.getX() + CommonVLs.SIZE_TANK))
                if ((y >= tank.getY()) && (y <= tank.getY() + CommonVLs.SIZE_TANK)) {
                return i;
            }
        }
        return -1;
    }

    public boolean check(PlayerTank playerTank) {
        if ((x >= playerTank.getX()) && (x <= playerTank.getX() + CommonVLs.SIZE_TANK))
            if ((y >= playerTank.getY()) && (y <= playerTank.getY() + CommonVLs.SIZE_TANK)) {
                return true;
            }
        return false;
    }

    public int getSpeed() {
        return speed;
    }
}
