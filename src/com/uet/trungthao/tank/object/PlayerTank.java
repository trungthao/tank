package com.uet.trungthao.tank.object;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.event.KeyEvent;

/**
 * Created by JiH on 7/27/2016.
 */
public class PlayerTank extends Tank {
    public PlayerTank(int x, int y) {
        super(x,y);
    }

    public void key(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                direction = CommonVLs.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                direction = CommonVLs.RIGHT;
                break;
            case KeyEvent.VK_UP:
                direction = CommonVLs.UP;
                break;
            case KeyEvent.VK_DOWN:
                direction = CommonVLs.DOWN;
                break;
        }
        changeImage();
    }

    public int getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
