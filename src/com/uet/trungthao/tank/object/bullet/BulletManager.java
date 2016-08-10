package com.uet.trungthao.tank.object.bullet;

import com.uet.trungthao.tank.object.tank.PlayerTank;
import com.uet.trungthao.tank.object.tank.TankOther;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by JiH on 7/29/2016.
 */
public class BulletManager {

    ArrayList<Bullet> arrayListBullet = new ArrayList<Bullet>();

    public void add(Bullet b) {
        arrayListBullet.add(b);
    }

    public void drawAll(Graphics2D g2d) {
        for (int i = 0; i < arrayListBullet.size(); ++i) {
            if (arrayListBullet.get(i).draw(g2d) == false) {
                arrayListBullet.remove(i);
            }
        }
    }

    public void checkAll(ArrayList<TankOther> arrayListTank) {
        for (int i = 0; i < arrayListBullet.size(); i++) {
            int j = arrayListBullet.get(i).check(arrayListTank);
            if (j != -1) {
                arrayListTank.remove(j);
                arrayListBullet.remove(i);

                continue;
            }
        }
    }

    public boolean checkAll(PlayerTank playerTank) {
        for (Bullet b : arrayListBullet) {
            if (b.check(playerTank)) {
                return true;
            }
        }
        return false;
    }
}
