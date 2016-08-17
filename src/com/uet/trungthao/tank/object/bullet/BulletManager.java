package com.uet.trungthao.tank.object.bullet;

import com.uet.trungthao.tank.commons.CommonVLs;
import com.uet.trungthao.tank.object.AnimationManager;
import com.uet.trungthao.tank.object.audio.Audio;
import com.uet.trungthao.tank.object.map.MapManager;
import com.uet.trungthao.tank.object.tank.PlayerTank;
import com.uet.trungthao.tank.object.tank.TankEnemy;

import javax.swing.*;
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

    /**
     * Kiểm tra và tạo ra hiệu ứng khi đạn bắn đạn trúng tank
     * @param arrayListTank
     * @param animMgr
     */
    public void checkAll(ArrayList<TankEnemy> arrayListTank, AnimationManager animMgr, Audio audio) {
        for (int i = 0; i < arrayListBullet.size(); i++) {
            int indexTaDie = arrayListBullet.get(i).check(arrayListTank);
            if (indexTaDie != -1) {
                TankEnemy tankDie = arrayListTank.get(indexTaDie);
                animMgr.addAnim(CommonVLs.TANK_EXFIORE, tankDie.getX(), tankDie.getY());
                arrayListTank.remove(indexTaDie);
                audio.play(CommonVLs.EXPLOSION_TANK);
                arrayListBullet.remove(i);
                continue;
            }
        }
    }

    /**
     * Kiểm tra và tạo hiệu ứng khi đạn bắn trúng tường
     * @param mapMgr
     * @param animMgr
     */
    public void checkAll(MapManager mapMgr, AnimationManager animMgr, Audio audio) {
        for (int i = 0; i < arrayListBullet.size(); i++) {
            Bullet b = arrayListBullet.get(i);
            if ( !mapMgr.checkInsideWater(b.getX(), b.getY(), CommonVLs.SIZE_BULLET)
                    && mapMgr.checkInsiseBrick(b.getX(), b.getY(), CommonVLs.SIZE_BULLET)) {
                animMgr.addAnim(CommonVLs.BULLET_EXFIORE,b.getX() - CommonVLs.ANIMATION_SIZE/2, b.getY() - CommonVLs.ANIMATION_SIZE/2);
                audio.play(CommonVLs.EXPLOSION_BRICK);
                arrayListBullet.remove(i);
            }
        }
    }

    public boolean checkAll(PlayerTank playerTank, AnimationManager animMgr, Audio audio) {
        for (int i = 0; i < arrayListBullet.size(); ++i) {
            Bullet b = arrayListBullet.get(i);
            if (b.check(playerTank)) {
                animMgr.addAnim(CommonVLs.TANK_EXFIORE, playerTank.getX(), playerTank.getY());
                audio.play(CommonVLs.EXPLOSION_TANK);
                playerTank.setX(0);
                playerTank.setY(0);
                arrayListBullet.remove(i);
                return true;
            }
        }
        return false;
    }
}
