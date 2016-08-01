package com.uet.trungthao.tank.object;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JiH on 7/29/2016.
 */
public class TankOtherManager {
    private ArrayList<TankOther> arrayListTank;
    private Random random;

    public TankOtherManager() {
        arrayListTank = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            int x, y;
            random = new Random();
            x = random.nextInt(CommonVLs.WIDTH_SCREEN - 30);
            y = random.nextInt(CommonVLs.HEIGHT_FRAME - 70);
            arrayListTank.add(new TankOther(x, y));
        }
    }

    public void drawAll(Graphics2D g2d) {
        for (TankOther tank : arrayListTank) {
            tank.draw(g2d);
        }
    }

    public void moveAll() {
        for (int i = 0; i < arrayListTank.size(); ++i) {
            TankOther tank = arrayListTank.get(i);
            for (int j = i + 1; j < arrayListTank.size(); ++j) {
                TankOther tankOther = arrayListTank.get(j);
                if (tank.collisionCheckTank(tankOther)) {
                    if (tank.direction == tankOther.direction) {
                        tank.direction = reverseDirection(tankOther.direction);
                    } else {
                        tank.direction = reverseDirection(tank.direction);
                        tankOther.direction = reverseDirection(tankOther.direction);
                    }
                    tank.changeImage();
                    tankOther.changeImage();
                    while (tank.collisionCheckTank(tankOther)) {
                        tank.move();
                        tankOther.move();
                    }
                    break;
                }
            }
            tank.move();
        }
    }

    public void autoShootAll(BulletManager bulletManager) {
        for (int i = 0; i < arrayListTank.size(); i++) {
            TankOther tankOther = arrayListTank.get(i);
            boolean bool = tankOther.autoShoot();
            if (bool) {
                bulletManager.add(new Bullet(tankOther.getX(), tankOther.getY(), tankOther.getDirection()));
            }
        }
    }

    private int reverseDirection(int direction) {
        switch (direction) {
            case CommonVLs.UP:
                return CommonVLs.DOWN;
            case CommonVLs.DOWN:
                return CommonVLs.UP;
            case CommonVLs.LEFT:
                return CommonVLs.RIGHT;
            case CommonVLs.RIGHT:
                return CommonVLs.LEFT;
        }
        return -1;
    }

    public ArrayList<TankOther> getArrayListTank() {
        return arrayListTank;
    }
}
