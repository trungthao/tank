package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by JiH on 17/08/2016.
 */
public class FirstMenuPanel extends JPanel implements ActionListener{

    JButton btnNewGame, btnGuide, btnExitGame;
    JLabel label;

    Listener listener;

    public FirstMenuPanel() {
        init();
        this.setVisible(true);
    }

    private void init() {
        this.setLayout(null);
        CommonVLs commonVLs = new CommonVLs();
        this.setBounds(0,0,CommonVLs.WIDTH_FRAME, CommonVLs.HEIGHT_FRAME);

        /**
         * set Image Panel
         */
        BufferedImage buffImg = commonVLs.getBufferImage("Panel.png");
        ImageIcon icon = new ImageIcon(buffImg.getScaledInstance(this.getWidth(), this.getHeight(), buffImg.SCALE_SMOOTH));
        label = new JLabel(icon);
        label.setBounds(0,0,CommonVLs.WIDTH_FRAME, CommonVLs.HEIGHT_FRAME);


        /**
         * Button newGame
         */
        buffImg = commonVLs.getBufferImage("gameMoi.png");
        ImageIcon newGameIcon = new ImageIcon(buffImg.getScaledInstance(CommonVLs.WIDTH_BUTTON, CommonVLs.HEIGHT_BUTTON, buffImg.SCALE_SMOOTH));
        btnNewGame = new JButton(newGameIcon);
        btnNewGame.setBounds(200,200, CommonVLs.WIDTH_BUTTON, CommonVLs.HEIGHT_BUTTON);


        /**
         * Button Guide
         */
        buffImg = commonVLs.getBufferImage("huongDan.png");
        ImageIcon guideIcon= new ImageIcon(buffImg.getScaledInstance(CommonVLs.WIDTH_BUTTON, CommonVLs.HEIGHT_BUTTON, buffImg.SCALE_SMOOTH));
        btnGuide = new JButton(guideIcon);
        btnGuide.setBounds(250, 300, CommonVLs.WIDTH_BUTTON, CommonVLs.HEIGHT_BUTTON);

        /**
         * Button exitGame
         */
        buffImg = commonVLs.getBufferImage("thoatGame.png");
        ImageIcon exitIcon = new ImageIcon(buffImg.getScaledInstance(CommonVLs.WIDTH_BUTTON, CommonVLs.HEIGHT_BUTTON, buffImg.SCALE_SMOOTH));
        btnExitGame = new JButton(exitIcon);
        btnExitGame.setBounds(300, 400, CommonVLs.WIDTH_BUTTON, CommonVLs.HEIGHT_BUTTON);

        this.add(btnNewGame);
        this.add(btnGuide);
        this.add(btnExitGame);
        this.add(label);

        btnNewGame.addActionListener(this);
        btnExitGame.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button == btnNewGame) {
            button.setFocusable(false);
            this.setVisible(false);
            listener.newGame();
        }

        if (button == btnExitGame) {
            System.exit(0);
        }
    }

    interface Listener {
        void newGame();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
