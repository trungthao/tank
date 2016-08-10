package com.uet.trungthao.tank.object.map;

import com.uet.trungthao.tank.commons.CommonVLs;

import java.awt.*;

/**
 * Created by JiH on 8/9/2016.
 */
public class Observe {
    private int x;
    private int y;
    private int size = CommonVLs.SIZE_TANK;
    private int type;
    private java.awt.Image img;

    public Observe(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        if (this.type == CommonVLs.BRICK_TYPE) {
            CommonVLs commonVLs = new CommonVLs();
            this.img = commonVLs.getImage("brick1.png");
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x * this.size, y * this.size, this.size, this.size, null);
    }

    public boolean isObjInside(int xObj, int yObj, int sizeObj) {
        int x = this.x * this.size;
        this.y = this.y * this.size;

        if (isPointInside(xObj, yObj, x,y,this.size)
                || isPointInside(xObj + sizeObj, yObj, x, y, this.size)
                || isPointInside(xObj, yObj + sizeObj, x, y, this.size)
                || isPointInside(xObj + sizeObj, yObj + sizeObj, x, y, this.size))
            return true;
        if (isPointInside(x,y,xObj,yObj, sizeObj)
            || isPointInside(x + this.size, y, xObj, yObj, sizeObj)
            || isPointInside(x, y + this.size, xObj, yObj, sizeObj)
            || isPointInside(x + this.size, y + this.size, xObj, yObj, sizeObj))
            return true;
        return false;
    }

    private boolean isPointInside(int xObj, int yObj, int x, int y, int size) {
        int bottomPoint = y + this.size;
        int rightPoint = x + this.size;
        if (xObj > x
            && yObj > y
            && xObj < rightPoint
            && yObj < bottomPoint) {
            return true;
        }
        return false;
    }
}
