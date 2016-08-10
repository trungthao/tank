package com.uet.trungthao.tank.object.map;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by JiH on 8/9/2016.
 */
public class MapManager {
    private ArrayList<Observe> obArr;

    public MapManager() {
        initData();
    }

    private void initData() {
        obArr = new ArrayList<>();

//        INIT bound
//        for (int i = 0; i < 25; ++i)
//            for (int j = 0; j < 25; j++) {
//                if (i == 5 || j == 5 || i == 10 || j == 10) {
//                    obArr.add(new Observe(i, j, CommonVLs.BRICK_TYPE));
//                }
//            }
        obArr.add(new Observe(0,0,CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(1,0,CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(5,5,CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(5,15,CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(10, 10, CommonVLs.BRICK_TYPE));
    }

    public void drawAll(Graphics2D g2d) {
        for (int i = 0; i < obArr.size(); ++i) {
            obArr.get(i).draw(g2d);
        }
    }

    public boolean checkInsise(int xObj, int yObj, int sizeObj) {
        for (int i = 0; i < obArr.size(); i++) {
            if (obArr.get(i).isObjInside(xObj, yObj, sizeObj)) {
                System.out.println("Va cham !!! va cham !!!");
                return true;
            }
        }
        return false;
    }
}
