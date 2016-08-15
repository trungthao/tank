package com.uet.trungthao.tank.object.map;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.*;

/**
 * Created by JiH on 8/9/2016.
 */
public class Observe {
    private int x;
    private int y;
    private int size;
    private int type;
    private java.awt.Image img;

    public Observe(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.size = CommonVLs.BRICK_SIZE;
        this.type = type;
        CommonVLs commonVLs = new CommonVLs();
        if (this.type == CommonVLs.BRICK_TYPE) {
            img = commonVLs.getImage("brick1.png");
        } else if (this.type == CommonVLs.WATER_TYPE) {
            img = commonVLs.getImage("water.png");
        } else if (this.type == CommonVLs.TREE_TYPE) {
            img = commonVLs.getImage("tree.png");
        }
    }


    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x * this.size, y * this.size, this.size, this.size, null);
    }

    public boolean isObjInside(int xObj, int yObj, int sizeObj) {
        int x = this.x * this.size;
        int y = this.y * this.size;

        if (isPointInside(xObj, yObj, x,y,this.size)
                || isPointInside(xObj + sizeObj, yObj, x, y, this.size)
                || isPointInside(xObj, yObj + sizeObj, x, y, this.size)
                || isPointInside(xObj + sizeObj, yObj + sizeObj, x, y, this.size)) {
            return true;
        }
        return false;
    }

    private boolean isPointInside(int xObj, int yObj, int x, int y, int size) {
        int bottomPoint = y + this.size;
        int rightPoint = x + this.size;
        if (xObj >= x
            && yObj >= y
            && xObj <= rightPoint
            && yObj <= bottomPoint)
            return true;
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
