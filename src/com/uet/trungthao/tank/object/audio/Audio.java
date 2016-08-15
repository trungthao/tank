package com.uet.trungthao.tank.object.audio;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.sound.sampled.Clip;

/**
 * Created by JiH on 8/14/2016.
 */
public class Audio {
    private Clip shoot, newGame, pause, endGame, explosionTank, explosionBrick, move;
    private boolean firstShoot = true, firstNew = true, firstPause = true, firstEnd = true,
            firstExploTank = true, firstExploBrick = true, firstMove = true;

    public Audio() {
        init();
    }

    private void init() {
        CommonVLs commonVLs = new CommonVLs();
        shoot = commonVLs.getClip("shoot.wav");
        newGame = commonVLs.getClip("enter_game.wav");
        pause = commonVLs.getClip("pause1.wav");
        endGame = commonVLs.getClip("endGame.wav");
        explosionBrick = commonVLs.getClip("explosion.wav");
        explosionTank = commonVLs.getClip("explosion_tank.wav");
        move = commonVLs.getClip("move.wav");
    }

    public void play(int type) {
        switch (type) {
            case CommonVLs.SHOOT:
                if (firstShoot) {
                    shoot.start();
                    firstShoot = false;
                } else {
                    shoot.loop(1);
                }
                break;
            case CommonVLs.NEW_GAME:
                if (firstNew) {
                    newGame.start();
                    firstNew = false;
                } else {
                    newGame.loop(1);
                }
                break;
            case CommonVLs.PAUSE:
                if (firstPause) {
                    pause.start();
                    firstPause = false;
                } else {
                    pause.loop(1);
                }
                break;
            case CommonVLs.ENDGAME:
                if (firstEnd) {
                    endGame.start();
                    firstEnd = false;
                } else {
                    endGame.loop(1);
                }
                break;
            case CommonVLs.EXPLOSION_TANK:
                if (firstExploTank) {
                    explosionTank.start();
                    firstExploTank = false;
                } else {
                    explosionTank.loop(1);
                }
                break;
            case CommonVLs.EXPLOSION_BRICK:
                if (firstExploBrick) {
                    explosionBrick.start();
                    firstExploBrick = false;
                } else {
                    explosionBrick.loop(1);
                }
                break;
            case CommonVLs.MOVE:
                if (firstMove) {
                    move.start();
                    firstMove = false;
                } else {
                    move.loop(1);
                }
                break;
        }
    }

    public void stop() {
        if (shoot.isRunning()) {
            shoot.stop();
        }
        if (newGame.isRunning()) {
            newGame.stop();
        }
        if (pause.isRunning()) {
            pause.stop();
        }
        if (explosionBrick.isRunning()) {
            explosionBrick.stop();
        }
        if (explosionTank.isRunning()) {
            explosionTank.stop();
        }
        if (endGame.isRunning()) {
            endGame.stop();
        }
        if (move.isRunning()) {
            move.stop();
        }
    }

}
