package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;
import com.uet.trungthao.tank.object.*;
import com.uet.trungthao.tank.object.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by JiH on 7/27/2016.
 */
public class ScreenPlayPanel extends JPanel implements KeyListener {

    private TankOtherManager tankOtherManager;
    private PlayerTank playerTank;
    private BulletManager bulletManagerPlayer, bulletManagerTankOther;
    private int count, countSpace;
    private boolean press;

    public ScreenPlayPanel() {
        playerTank = new PlayerTank(50, 50);
        bulletManagerPlayer = new BulletManager();
        bulletManagerTankOther = new BulletManager();
        tankOtherManager = new TankOtherManager();
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(0, 0, CommonVLs.WIDTH_SCREEN, CommonVLs.HEIGHT_FRAME);
        setFocusable(true);
        addKeyListener(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        playerTank.draw(g2d);
        bulletManagerPlayer.drawAll(g2d);
        tankOtherManager.drawAll(g2d);
        tankOtherManager.moveAll();
        bulletManagerPlayer.checkAll(tankOtherManager.getArrayListTank());
        bulletManagerTankOther.drawAll(g2d);
        if (bulletManagerTankOther.checkAll(playerTank)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                tankOtherManager.autoShootAll(bulletManagerTankOther);
                try {
                    Thread.sleep(Bullet.speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++count;
                if (count % (playerTank.getSpeed()/Bullet.speed) == 0 && press) {
                    count = 0;
                    playerTank.move();
                }
                repaint();
            }
        }
    });

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ++countSpace;
            if (countSpace % 5 == 0) {
                bulletManagerPlayer.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getDirection()));
                countSpace = 0;
            }
        } else {
            playerTank.key(e);
            press = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        press = false;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                bulletManagerPlayer.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getDirection()));
        } else {
            playerTank.key(e);
        }
    }
}
