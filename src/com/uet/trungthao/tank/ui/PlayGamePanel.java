package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;
import com.uet.trungthao.tank.object.AnimationManager;
import com.uet.trungthao.tank.object.audio.Audio;
import com.uet.trungthao.tank.object.bullet.Bullet;
import com.uet.trungthao.tank.object.bullet.BulletManager;
import com.uet.trungthao.tank.object.map.MapManager;
import com.uet.trungthao.tank.object.tank.PlayerTank;
import com.uet.trungthao.tank.object.tank.TankEnemy;
import com.uet.trungthao.tank.object.tank.TankEnemyManager;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class PlayGamePanel extends JPanel implements KeyListener, MenuPanel.ListenerMenu, Runnable {

    private TankEnemyManager enemyMgr;
    private PlayerTank playerTank;
    private BulletManager bullMgrPlay, bullMgrEnemy;
    private int countSpace;
    private boolean press;
    private MapManager mapMgr;
    private AnimationManager animMgr;
    private Audio audio;

    private int countAnim = 0;
    private boolean die, enemyDie;

    public PlayGamePanel() {
        initComponent();
        thread.start();
    }

    private void initComponent() {
        die = false;
        audio = new Audio();
        audio.play(CommonVLs.NEW_GAME);
        this.setVisible(true);
        press = false;
        countSpace = 0;
        countAnim = 0;
        isPlaying = true;
        mapMgr = new MapManager();
        playerTank = new PlayerTank(35, 35);
        bullMgrPlay = new BulletManager();
        bullMgrEnemy = new BulletManager();
        enemyMgr = new TankEnemyManager(mapMgr, playerTank);
        animMgr = new AnimationManager();
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(0, 0, CommonVLs.WIDTH_SCREEN, CommonVLs.HEIGHT_FRAME);
        setFocusable(true);
        addKeyListener(this);
        enemyDie = false;
    }

    private void loopGame() {
        draw();
        update();
    }

    private void update() {

        /**
         * Update cho tankPlayer
         */
        // Kiem tra dan trung tankEnemy
        bullMgrPlay.checkAll(enemyMgr.getArrayListTank(), animMgr, audio);
        // Kiem tra dan trung gach
        bullMgrPlay.checkAll(mapMgr, animMgr, audio);

        /**
         * Update cho các tankEnemy
         */
        enemyMgr.moveAll();
        enemyMgr.checkMap(mapMgr);
        enemyMgr.autoShootAll(bullMgrEnemy, audio);
        // kiem tra dan ban trung gach
        bullMgrEnemy.checkAll(mapMgr, animMgr, audio);
        // Kiem tra va xu ly va cham giua tankPlayer và enemyTank
        enemyMgr.processCollision(playerTank);

        if (enemyMgr.isEmpty() && !enemyDie) {
            enemyDie = true;
            audio.stop();
            audio.play(CommonVLs.ENDGAME);
            int option = JOptionPane.showConfirmDialog(null,"Bạn muốn chơi tiếp ko???","Chiến thắng", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                newGame();
            } else {
                exitGame();
            }
        }

        if (bullMgrEnemy.checkAll(playerTank, animMgr, audio)) {
            die = true;
        }

        /**
         * xử lý sự kiện nhấn dữ phím di chuyển để di chuyển
         * tankPlayer sẽ di chuyển theo phím mũi tên
         * Khi va chạm gạch sẽ ko di chuyển được nữa
         */
        if (press) {
            playerTank.move();
            audio.play(CommonVLs.MOVE);
            // Kiểm tra va chạm gạch hay chưa
            if (mapMgr.checkInsideTree(playerTank.getX(), playerTank.getY(), CommonVLs.SIZE_TANK)) {
                playerTank.setHidden(true);
            } else {
                playerTank.setHidden(false);
            }
            if (mapMgr.checkInsiseBrick(playerTank.getX(), playerTank.getY(), CommonVLs.SIZE_TANK)
                    || mapMgr.checkInsideWater(playerTank.getX(), playerTank.getY(), CommonVLs.SIZE_TANK)) {
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
        if (e.getKeyCode() == KeyEvent.VK_K) {
            ArrayList arr = enemyMgr.getArrayListTank();
            for (int i = 0; i < arr.size(); i++) {
                arr.remove(i);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ++countSpace;
            if (countSpace % 5 == 0) {
                bullMgrPlay.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getDirection()));
                audio.play(CommonVLs.SHOOT);
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
            audio.play(CommonVLs.SHOOT);
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
        } else {
            audio.stop();
            audio.play(CommonVLs.PAUSE);
        }
        return this.isPlaying;
    }

    @Override
    public void newGame() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                audio.stop();
                initComponent();
            }
        });
        thread1.start();
    }

    @Override
    public void exitGame() {
        System.exit(0);
    }

    @Override
    public void run() {
        while (isPlaying) {
            if (die) {
                for (int i = 0; i < 100; i++) {
                    loopGame();
                    if (i > 50) {
                        audio.stop();
                    }
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                audio.play(CommonVLs.ENDGAME);
                int option = JOptionPane.showConfirmDialog(null,"Thua rồi. Chơi tiếp nhá???","Thua cuộc", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    newGame();
                } else {
                    exitGame();
                }
            }
            loopGame();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
