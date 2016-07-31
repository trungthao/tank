package com.uet.trungthao.tank;

import com.uet.trungthao.tank.ui.GUI;

import javax.swing.*;

/**
 * Created by JiH on 7/27/2016.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI();
                gui.setVisible(true);
            }
        });
    }
}
