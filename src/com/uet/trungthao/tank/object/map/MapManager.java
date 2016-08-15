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
        obArr = new ArrayList<Observe>();

//        INIT bound
        int number = CommonVLs.WIDTH_SCREEN / CommonVLs.BRICK_SIZE;
        for (int i = 0; i < number; ++i)
            for (int j = 0; j < number; j++) {
                if (i == 0 || j == 0 || i == number - 1 || j == number - 1) {
                    obArr.add(new Observe(i, j, CommonVLs.BRICK_TYPE));
                }
            }
//
        obArr.add(new Observe(10, 5, CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(15, 10, CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(3, 7, CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(9, 6, CommonVLs.BRICK_TYPE));
        obArr.add(new Observe(6, 9, CommonVLs.BRICK_TYPE));

        obArr.add(new Observe(7, 7, CommonVLs.WATER_TYPE));
        obArr.add(new Observe(8, 7, CommonVLs.WATER_TYPE));
        obArr.add(new Observe(9, 7, CommonVLs.WATER_TYPE));

        obArr.add(new Observe(5, 5, CommonVLs.TREE_TYPE));
        obArr.add(new Observe(5, 6, CommonVLs.TREE_TYPE));
        obArr.add(new Observe(5, 7, CommonVLs.TREE_TYPE));
    }

    public void drawAll(Graphics2D g2d) {
        for (int i = 0; i < obArr.size(); ++i) {
            obArr.get(i).draw(g2d);
        }
    }

    public boolean checkInsiseBrick(int xObj, int yObj, int sizeObj) {
        for (int i = 0; i < obArr.size(); i++) {
            Observe observe = obArr.get(i);
            if (observe.getType() == CommonVLs.BRICK_TYPE) {
                if (observe.isObjInside(xObj, yObj, sizeObj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkInsideWater(int xObj, int yObj, int sizeObj) {
        for (int i = 0; i < obArr.size(); i++) {
            Observe observe = obArr.get(i);
            if (observe.getType() == CommonVLs.WATER_TYPE) {
                if (observe.isObjInside(xObj, yObj, sizeObj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkInsideTree(int xObj, int yObj, int sizeObj) {
        for (int i = 0; i < obArr.size(); i++) {
            Observe observe = obArr.get(i);
            if (observe.getType() == CommonVLs.TREE_TYPE) {
                if (observe.isObjInside(xObj, yObj, sizeObj)) {
                    return true;
                }
            }
        }
        return false;
    }
}
