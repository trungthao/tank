package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.swing.*;

/**
 * Created by JiH on 7/27/2016.
 */
public class GUI extends JFrame implements FirstMenuPanel.Listener {
    private PlayGamePanel playPanel;
    private MenuPanel menuPanel;
    private FirstMenuPanel firstMenu;
    public GUI() {
//        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200,0, CommonVLs.WIDTH_FRAME, CommonVLs.HEIGHT_FRAME+47);

        firstMenu = new FirstMenuPanel();
        firstMenu.setListener(this);
        this.add(firstMenu);
    }

    @Override
    public void newGame() {
        menuPanel = new MenuPanel();
        playPanel = new PlayGamePanel();
        this.add(menuPanel);
        this.add(playPanel);
        menuPanel.setListener(playPanel);
    }
}
