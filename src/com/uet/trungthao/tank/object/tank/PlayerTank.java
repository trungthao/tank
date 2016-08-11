package com.uet.trungthao.tank.object.tank;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.event.KeyEvent;

/**
 * Created by JiH on 7/27/2016.
 */
public class PlayerTank extends Tank {
    public PlayerTank(int x, int y) {
        super(x,y);
    }

    public void keyReleased(KeyEvent e) {
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
        }
        changeImage();
    }
}
