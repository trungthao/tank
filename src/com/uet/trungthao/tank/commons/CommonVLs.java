package com.uet.trungthao.tank.commons;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by JiH on 7/27/2016.
 */
public class CommonVLs {

    /**
     * width, height
     */
    public static final int WIDTH_FRAME = 800;
    public static final int HEIGHT_FRAME = 600;
    public static final int WIDTH_SCREEN = 600;
    public static final int WIDTH_MENU = 200;
    public static final int WIDTH_BUTTON = CommonVLs.WIDTH_MENU - 30;
    public static final int HEIGHT_BUTTON = HEIGHT_FRAME/10;

    /**
     * các hướng di chuyển
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    /**
     * size của brick, tank, bullet
     */
    public static final int BRICK_SIZE= 30;
    public static final int SIZE_TANK= 30;
    public static final int SIZE_BULLET = 5;
    public static final int ANIMATION_SIZE = 30;

    /**
    * type
     */
    // Map
    public static final int BRICK_TYPE = 0;
    public static final int WATER_TYPE = 1;
    public static final int TREE_TYPE = 2;

    // Animation
    public static final int TANK_EXFIORE = 1;
    public static final int BULLET_EXFIORE = 2;

    // Audio: shoot, newGame, pause, endGame, explosionTank, explosionBrick, move;
    public static final int SHOOT = 0;
    public static final int NEW_GAME = 1;
    public static final int PAUSE = 2;
    public static final int ENDGAME = 3;
    public static final int EXPLOSION_TANK = 4;
    public static final int EXPLOSION_BRICK = 5;
    public static final int MOVE = 6;

    /**
     * get File, Image, audio
     */
    private final String PACKAGE_PATH = "/RESOURCE/Image/";
    private final String PACKAGE_SOUND = "/RESOURCE/sound/";
    public java.awt.Image getImage(String name) {
        return new ImageIcon(getClass().getResource(PACKAGE_PATH + name)).getImage();
    }

    public BufferedImage getBufferImage(String name) {
        File file = new File(getClass().getResource(PACKAGE_PATH + name).getPath());
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Clip getClip(String nameSound) {

        try {
            File file = new File(getClass().getResource(PACKAGE_SOUND + nameSound).getPath());
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
}
