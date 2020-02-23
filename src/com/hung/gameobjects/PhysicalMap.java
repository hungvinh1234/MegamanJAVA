package com.hung.gameobjects;

import com.hung.effect.CacheDataLoader;

import java.awt.*;

public class PhysicalMap extends GameObject{

    public int[][] phys_map;
    private int titleSize;

    @Override
    public void Update() { }

    public PhysicalMap(float x,float y, GameWorld gameWorld){

        super(x,y,gameWorld);
        this.titleSize = 30;
        phys_map = CacheDataLoader.getInstance().getPhysicalMap();

    }

    public int getTitleSize(){
        return titleSize;
    }

    public void draw(Graphics2D g2){
        Camera camera = getGameWorld().camera;

        g2.setColor(Color.GRAY);
        for(int i = 0;i< phys_map.length;i++)
            for(int j = 0;j<phys_map[0].length;j++)
                if(phys_map[i][j]!=0) g2.fillRect((int) getPosX() + j*titleSize - (int) camera.getPosX(),
                        (int) getPosY() + i*titleSize - (int) camera.getPosY(), titleSize, titleSize);
    }


    //Va cham voi mat dat
    public Rectangle haveCollisionWithLand(Rectangle rect) {

        int posX1 = rect.x / titleSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width) / titleSize;
        posX2 += 2;

        int posY1 = (rect.y + rect.height) / titleSize;

        if (posX1 < 0) posX1 = 0;

        if (posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;
        for (int y = posY1; y < phys_map.length; y++) {
            for (int x = posX1; x <= posX2; x++) {

                if (phys_map[y][x] == 1) {
                    Rectangle r = new Rectangle((int) getPosX() + x * titleSize,
                            (int) getPosY() + y * titleSize, titleSize, titleSize);
                    if (rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    //Va cham voi tran
    public Rectangle haveCollisionWithTop(Rectangle rect) {

        int posX1 = rect.x / titleSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width) / titleSize;
        posX2 += 2;

//        int posY = (rect.y + rect.height) / titleSize;
        int posY = rect.y / titleSize;

        if(posX1 < 0) posX1 = 0;

        if (posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;

        for (int y = posY; y >= 0; y--){
            for (int x = posX1; x <= posX2; x++){

                if (phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * titleSize, (int) getPosY() + y * titleSize, titleSize, titleSize);
                    if (rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithRightWall(Rectangle rect){

        int posY1 = rect.y/titleSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / titleSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / titleSize;
        int posX2 = posX1 +3 ;
        if(posX2 >= phys_map[0].length) posX2 = phys_map[0].length - 1;

        if (posY1 < 0) posY1 = 0;
        if (posY2 >= phys_map.length) posY2 = phys_map.length - 1;

        for (int x = posX1; x <= posX2; x++){
            for (int y = posY1; y <= posY2; y++){
                if (phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * titleSize, (int) getPosY() + y * titleSize, titleSize,titleSize);
                    if (r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithLeftWall(Rectangle rect){

        int posY1 = rect.y/titleSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / titleSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / titleSize;
        int posX2 = posX1 - 3 ;
        if(posX2 < 0) posX2 = 0;

        if (posY1 < 0) posY1 = 0;
        if (posY2 >= phys_map.length) posY2 = phys_map.length - 1;

        for (int x = posX1; x >= posX2; x--){
            for (int y = posY1; y <= posY2; y++){
                if (phys_map[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * titleSize, (int) getPosY() + y * titleSize, titleSize,titleSize);
                    if (r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }





}
