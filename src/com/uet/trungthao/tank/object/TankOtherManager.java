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
            int x,y;
            random = new Random();
            x = random.nextInt(CommonVLs.WIDTH_SCREEN - 30);
            y = random.nextInt(CommonVLs.HEIGHT_FRAME - 70);
            arrayListTank.add(new TankOther(x,y));
        }
    }
    public void drawAll(Graphics2D g2d) {
        for (TankOther tank : arrayListTank) {
            tank.draw(g2d);
        }
    }

    public void moveAll() {
        for (int i = 0; i < arrayListTank.size()-1; ++i) {
            boolean check = false;
            TankOther tank = arrayListTank.get(i);
            for(int j = i+1; j < arrayListTank.size(); ++j) {
                TankOther tankOther = arrayListTank.get(j);
                if (tank.collisionCheckTank(tankOther)) {
                    tankOther.setDirection((tankOther.getDirection() + 2)%4);
                    tankOther.changeImage();

                    while (tank.collisionCheckTank(tankOther)) {
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

    public ArrayList<TankOther> getArrayListTank() {
        return arrayListTank;
    }
}
