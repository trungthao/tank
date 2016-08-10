package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.swing.*;

/**
 * Created by JiH on 7/27/2016.
 */
public class GUI extends JFrame{
    public GUI() {
//        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200,0, CommonVLs.WIDTH_FRAME, CommonVLs.HEIGHT_FRAME);

        MenuPanel menuPanel = new MenuPanel();
        ScreenPlayPanel screenPlayPanel = new ScreenPlayPanel();

        this.add(menuPanel);
        this.add(screenPlayPanel);
    }
}
