package com.uet.trungthao.tank.object;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.swing.*;
import java.util.Random;

/**
 * Created by JiH on 7/29/2016.
 */
public class TankOther extends Tank {
    Random random = new Random();
    int count = random.nextInt(CommonVLs.WIDTH_SCREEN);
    int delay = random.nextInt(10);
    int shoot = random.nextInt(5000);

    public TankOther(int x, int y) {
        super(x, y);
        imageUp = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "player_green_1.png")).getImage();
        imageDown = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "player_green_2.png")).getImage();
        imageLeft = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "player_green_3.png")).getImage();
        imageRight = new ImageIcon(getClass().getResource(CommonVLs.PACKAGE_PATH + "player_green_4.png")).getImage();
        image = imageRight;
    }

    @Override
    public void move() {
        if (delay > 0) {
            --delay;
        } else if (count > 0) {
            delay = random.nextInt(10);
            --count;
            super.move();
            if (collisionCheck()) {
                count = 0;
            }
        } else {
            count = random.nextInt(CommonVLs.WIDTH_SCREEN);
            changeDirection();
            changeImage();
        }
    }

    public boolean autoShoot() {
        if (shoot > 0) {
            --shoot;
            return false;
        } else {
            shoot = random.nextInt(1000);
            return true;
        }
    }
}