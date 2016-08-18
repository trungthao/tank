package com.uet.trungthao.tank.object.map;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by JiH on 8/9/2016.
 */
public class MapManager {
    private ArrayList<Observe> obArr;
    private int autoMap;

    public MapManager() {
        initData();
    }

    private void initData() {
        obArr = new ArrayList<>();

        autoMap = new Random().nextInt(5) + 1;
        CommonVLs commonVLs = new CommonVLs();
        Map<Point, Integer> map = commonVLs.getMap("map" + autoMap);

        for (Map.Entry<Point, Integer> entry : map.entrySet()) {
            Point p = entry.getKey();
            int type = entry.getValue();
            int x = (int) p.getX();
            int y = (int) p.getY();
            obArr.add(new Observe(x, y, type));
        }

//        INIT bound
        int number = CommonVLs.WIDTH_SCREEN / CommonVLs.BRICK_SIZE;
        for (int i = 0; i < number; ++i)
            for (int j = 0; j < number; j++) {
                if (i == 0 || j == 0 || i == number - 1 || j == number - 1) {
                    obArr.add(new Observe(i, j, CommonVLs.BRICK_TYPE));
                }
            }
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

    public int getAutoMap() {
        return autoMap;
    }
}
