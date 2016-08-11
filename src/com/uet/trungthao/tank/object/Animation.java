package com.uet.trungthao.tank.object;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by JiH on 8/10/2016.
 */
public class Animation {
    private int type;
    private ArrayList<Image> imgArr;
    private int size;
    private int x;
    private int y;

    public Animation(int type, int size, int x, int y) {
        this.type = type;
        this.size = size;

        this.x = x;
        this.y = y;

        initImageFromType(type);
    }

    private void initImageFromType(int type) {
        imgArr = new ArrayList<>();
        CommonVLs commonVLs = new CommonVLs();
        if (type == CommonVLs.TANK_EXFIORE) {
            // lay anh tank no cho vao Arrlist
            String tankName = "tank_exp";
            for (int i = 1; i < 6; ++i) {
                imgArr.add(commonVLs.getImage(tankName + i + ".png"));
            }
        } else if (type == CommonVLs.BULLET_EXFIORE) {
            imgArr.add(commonVLs.getImage("explosion.png"));
        }
    }

    public boolean draw(Graphics2D g2d) {
        Image img = imgArr.get(0);
        imgArr.remove(0);
        g2d.drawImage(img, this.x, this.y, this.size, this.size, null);
        if (imgArr.isEmpty()) return false;
        else return true;

    }
}
