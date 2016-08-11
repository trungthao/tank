package com.uet.trungthao.tank.object;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by JiH on 8/10/2016.
 */
public class AnimationManager {
    ArrayList<Animation> aniArr;
    public AnimationManager() {
        this.aniArr = new ArrayList<>();
    }

    public void addAnim(int type, int x, int y) {
        Animation anim = new Animation(type, CommonVLs.ANIMATION_SIZE, x, y);
        aniArr.add(anim);
    }

    public void drawAll(Graphics2D g2d) {
        for (int i = 0; i < aniArr.size(); i++) {
            Animation anim = aniArr.get(i);
            boolean boolAnim = anim.draw(g2d);
            if (!boolAnim) {
                aniArr.remove(i);
                i--;
            }
        }
    }
}
