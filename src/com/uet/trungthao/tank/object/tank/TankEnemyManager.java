package com.uet.trungthao.tank.object.tank;

import com.uet.trungthao.tank.commons.CommonVLs;
import com.uet.trungthao.tank.object.bullet.Bullet;
import com.uet.trungthao.tank.object.bullet.BulletManager;
import com.uet.trungthao.tank.object.map.MapManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JiH on 7/29/2016.
 */
public class TankEnemyManager {
    private ArrayList<TankEnemy> arrayListTank;

    private Random random;

    public TankEnemyManager(MapManager mapMgr, PlayerTank playerTank) {
        init(mapMgr, playerTank);
    }

    private void init(MapManager mapMgr,PlayerTank playerTank) {
        int playX = playerTank.getX();
        int playY = playerTank.getY();
        arrayListTank = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            int x, y;
            random = new Random();
            boolean boolLocal;
            do {
                boolLocal = false;
                x = random.nextInt(400) + CommonVLs.BRICK_SIZE;
                y = random.nextInt(400) + CommonVLs.BRICK_SIZE;
                for (TankEnemy j : arrayListTank) {
                    if (j.isObjInside(x, y)
                            || mapMgr.checkInsise(x,y,CommonVLs.SIZE_TANK)
                            || (x == playX && y == playY)) {
                        boolLocal = true;
                        break;
                    }
                }

            } while (boolLocal);
            arrayListTank.add(new TankEnemy(x, y));
        }
    }

    public void drawAll(Graphics2D g2d) {
        for (int i = 0; i < arrayListTank.size(); ++i) {
            arrayListTank.get(i).draw(g2d);
        }
    }

    public void moveAll(MapManager mapMgr) {
        for (int i = 0; i < arrayListTank.size(); ++i) {
            TankEnemy tank1 = arrayListTank.get(i);
            tank1.move();
            for (int j = 0; j < arrayListTank.size(); ++j) {
                if (i != j) {
                    TankEnemy tank2 = arrayListTank.get(j);
                    if (tank1.isObjInside(tank2.getX(), tank2.getY())) {
                        tank1.changeDirection();
                        tank1.changeImage();
                        tank1.noMove();
                    }
                }
            }
        }
    }

    /**
     * Kiểm tra va chạm giưa tankPlayer với các tankEnemy khác
     *
     * @param
     */
    public boolean processCollision(PlayerTank tank) {
        for (int i = 0; i < arrayListTank.size(); ++i) {
            TankEnemy taEnemy = arrayListTank.get(i);
            if (tank.isObjInside(taEnemy.getX(), taEnemy.getY())) {
                taEnemy.noMove();
                tank.noMove();
                return true;
            }
        }
        return false;
    }

    public void checkMap(MapManager mapMgr) {
        for (int i = 0; i < arrayListTank.size(); i++) {
            TankEnemy tank = arrayListTank.get(i);
            if (mapMgr.checkInsise(tank.getX(), tank.getY(), CommonVLs.SIZE_TANK)) {
                tank.changeDirection();
                tank.changeImage();
                tank.noMove();
            }
        }
    }

    public void autoShootAll(BulletManager bulletManager) {
        for (int i = 0; i < arrayListTank.size(); i++) {
            TankEnemy tankOther = arrayListTank.get(i);
            boolean bool = tankOther.autoShoot();
            if (bool) {
                bulletManager.add(new Bullet(tankOther.getX(), tankOther.getY(), tankOther.getDirection()));
            }
        }
    }

    public ArrayList<TankEnemy> getArrayListTank() {
        return arrayListTank;
    }

    public boolean isEmpty() {
        return arrayListTank.isEmpty();
    }
}
