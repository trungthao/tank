package com.uet.trungthao.tank.commons;

import javax.swing.*;

/**
 * Created by JiH on 7/27/2016.
 */
public class CommonVLs {
    public static final int WIDTH_FRAME = 900;
    public static final int HEIGHT_FRAME = 600;
    public static final int WIDTH_MENU = 300;
    public static final int WIDTH_SCREEN = 600;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public static final int BRICK_TYPE = 0;
    public static final int BRICK_SIZE= 30;

    public static final int TANK_EXFIORE = 1;
    public static final int BULLET_EXFIORE = 2;
    public static final int ANIMATION_SIZE = 30;

    public static final int SIZE_TANK= 30;
    public static final int SIZE_BULLET = 5;

    private final String PACKAGE_PATH = "/RESOURCE/Image/";

    public java.awt.Image getImage(String name) {
        return new ImageIcon(getClass().getResource(PACKAGE_PATH + name)).getImage();
    }
}
