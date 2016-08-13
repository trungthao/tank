package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.swing.*;

/**
 * Created by JiH on 7/27/2016.
 */
public class GUI extends JFrame{
    private PlayGamePanel playPanel;
    private MenuPanel menuPanel;
    public GUI() {
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200,0, CommonVLs.WIDTH_FRAME, CommonVLs.HEIGHT_FRAME+47);

        menuPanel = new MenuPanel();
        playPanel = new PlayGamePanel();
        this.add(menuPanel);
        this.add(playPanel);
        menuPanel.setListener(playPanel);
    }
}
