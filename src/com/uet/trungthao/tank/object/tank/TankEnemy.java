package com.uet.trungthao.tank.object.tank;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.swing.*;
import java.util.Random;

/**
 * Created by JiH on 7/29/2016.
 */
public class TankEnemy extends Tank {
    private Random random = new Random();
    private int timeMove = random.nextInt(CommonVLs.WIDTH_FRAME/CommonVLs.SIZE_TANK) + 10;
    private int timeShoot = random.nextInt(200);

    public TankEnemy(int x, int y) {
        super(x, y);
        this.setSpeed(2);
        init();
    }

    private void init() {
        CommonVLs commonVLs = new CommonVLs();
        imageUp = commonVLs.getImage("player_green_1.png");
        imageDown = commonVLs.getImage("player_green_2.png");
        imageLeft = commonVLs.getImage("player_green_3.png");
        imageRight = commonVLs.getImage("player_green_4.png");
        image = imageRight;
    }

    @Override
    public void move() {
        if (timeMove > 0) {
            --timeMove;
            super.move();
        } else {
            timeMove = random.nextInt(CommonVLs.WIDTH_FRAME/CommonVLs.SIZE_TANK) + 10;
            changeDirection();
            changeImage();
        }
    }

    public boolean autoShoot() {
        if (timeShoot > 0) {
            --timeShoot;
            return false;
        } else {
            timeShoot = random.nextInt(200);
            return true;
        }
    }

    public void reverDirec(int direction) {
        switch (direction) {
            case CommonVLs.UP:
                this.direction = CommonVLs.DOWN;
            case CommonVLs.DOWN:
                this.direction = CommonVLs.UP;
            case CommonVLs.LEFT:
                this.direction = CommonVLs.RIGHT;
            case CommonVLs.RIGHT:
                this.direction = CommonVLs.LEFT;
        }
    }
}