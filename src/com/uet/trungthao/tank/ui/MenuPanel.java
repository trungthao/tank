package com.uet.trungthao.tank.ui;

import com.uet.trungthao.tank.commons.CommonVLs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by JiH on 7/27/2016.
 */
public class MenuPanel extends JPanel implements ActionListener {
    JButton pauseOrPlay;
    ListenerMenu listener;
    ImageIcon iconPlay, iconPause;

    public MenuPanel() {
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBounds(CommonVLs.WIDTH_SCREEN, 0, CommonVLs.WIDTH_MENU, CommonVLs.HEIGHT_FRAME);
        initComponent();
    }

    private void initComponent() {
        CommonVLs commonVLs = new CommonVLs();

        BufferedImage bufImg = commonVLs.getBufferImage("pauseGame.png");
        iconPause = new ImageIcon(bufImg.getScaledInstance(50, 50, bufImg.SCALE_SMOOTH));
        pauseOrPlay = new JButton("Tạm dừng", iconPause);
        pauseOrPlay.setBounds(10, CommonVLs.HEIGHT_BUTTON, CommonVLs.WIDTH_BUTTON, CommonVLs.HEIGHT_BUTTON);
        pauseOrPlay.addActionListener(this);

        bufImg = commonVLs.getBufferImage("playGame.png");
        iconPlay = new ImageIcon(bufImg.getScaledInstance(50,50,bufImg.SCALE_SMOOTH));
        this.add(pauseOrPlay);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == pauseOrPlay) {
            button.setFocusable(false);
            boolean boolPlayPause = listener.pausePlay();
            if (boolPlayPause) {
                pauseOrPlay.setIcon(iconPause);
                pauseOrPlay.setText("Tạm dừng");
            } else {
                pauseOrPlay.setIcon(iconPlay);
                pauseOrPlay.setText("Chơi tiếp");
            }
        }
    }

    interface ListenerMenu {
        boolean pausePlay();
    }

    public void setListener(ListenerMenu listener) {
        this.listener = listener;
    }
}
