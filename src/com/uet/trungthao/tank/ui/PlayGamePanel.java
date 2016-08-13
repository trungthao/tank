package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;
import com.uet.trungthao.tank.object.AnimationManager;
import com.uet.trungthao.tank.object.bullet.Bullet;
import com.uet.trungthao.tank.object.bullet.BulletManager;
import com.uet.trungthao.tank.object.map.MapManager;
import com.uet.trungthao.tank.object.tank.PlayerTank;
import com.uet.trungthao.tank.object.tank.TankEnemy;
import com.uet.trungthao.tank.object.tank.TankEnemyManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayGamePanel extends JPanel implements KeyListener, MenuPanel.ListenerMenu, Runnable {

    private TankEnemyManager enemyMgr;
    private PlayerTank playerTank;
    private BulletManager bullMgrPlay, bullMgrEnemy;
    private int countSpace;
    private boolean press;
    private MapManager mapMgr;
    private AnimationManager animMgr;
    private boolean first = true;

    private int countAnim = 0;

    public PlayGamePanel() {
        initComponent();
        thread.start();
    }

    private void initComponent() {

        this.setVisible(true);
        press = false;
        countSpace = 0;
        countAnim = 0;
        isPlaying = true;
        mapMgr = new MapManager();
        playerTank = new PlayerTank(CommonVLs.BRICK_SIZE + 10, CommonVLs.BRICK_SIZE + 10);
        bullMgrPlay = new BulletManager();
        bullMgrEnemy = new BulletManager();
        enemyMgr = new TankEnemyManager(mapMgr, playerTank);
        animMgr = new AnimationManager();
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(0, 0, CommonVLs.WIDTH_SCREEN, CommonVLs.HEIGHT_FRAME);
        setFocusable(true);
        addKeyListener(this);
    }

    private void loopGame() {
        draw();
        update();
    }

    private void update() {


        /**
         * Update cho các tankEnemy
         */
        enemyMgr.moveAll(mapMgr);
        enemyMgr.checkMap(mapMgr);
        enemyMgr.autoShootAll(bullMgrEnemy);
        // kiem tra dan ban trung gach
        bullMgrEnemy.checkAll(mapMgr, animMgr);
        enemyMgr.processCollision(playerTank);

        if (enemyMgr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You Win ... !!!");
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    initComponent();
                }
            });
            thread1.start();
        }

        if (bullMgrEnemy.checkAll(playerTank)) {
            JOptionPane.showMessageDialog(null, "Enemy Thắng");
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    initComponent();
                }
            });
            thread1.start();
        }

        /**
         * Update cho tankPlayer
         */
        // Kiem tra dan trung tankEnemy
        bullMgrPlay.checkAll(enemyMgr.getArrayListTank(), animMgr);
        // Kiem tra dan trung gach
        bullMgrPlay.checkAll(mapMgr, animMgr);

        /**
         * xử lý sự kiện nhấn dữ phím di chuyển để di chuyển
         * tankPlayer sẽ di chuyển theo phím mũi tên
         * Khi va chạm gạch sẽ ko di chuyển được nữa
         */
        if (press) {
            playerTank.move();
            // Kiểm tra va chạm gạch hay chưa
            if (mapMgr.checkInsise(playerTank.getX(), playerTank.getY(), CommonVLs.SIZE_TANK)) {
                // Xét hướng gây ra va chạm và dịch chuyển trở lại vị trí cữ => tank đứng yên
                playerTank.noMove();
            }
        }

    }

    private void draw() {
        repaint();
    }

    private boolean isPlaying = true;

    Thread thread = new Thread(this);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        mapMgr.drawAll(g2d);

        playerTank.draw(g2d);
        enemyMgr.drawAll(g2d);

        bullMgrPlay.drawAll(g2d);
        bullMgrEnemy.drawAll(g2d);

        ++countAnim;
        if (countAnim % 3 == 0) {
            if (countAnim > 99999) countAnim = 0;
            animMgr.drawAll(g2d);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ++countSpace;
            if (countSpace % 5 == 0) {
                bullMgrPlay.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getDirection()));
                countSpace = 0;
            }
        } else {
            playerTank.keyReleased(e);
            press = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        press = false;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullMgrPlay.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getDirection()));
        } else {
            playerTank.keyReleased(e);
        }
    }

    @Override
    public boolean pausePlay() {
        this.isPlaying = !isPlaying;
        if (isPlaying) {
            Thread thread2 = new Thread(this);
            thread2.start();
        }
        return this.isPlaying;
    }

    @Override
    public void run() {
        while (isPlaying) {
            loopGame();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
