package com.hung.gameobjects;

import java.awt.*;

public abstract class Human extends ParticularObject{

    private boolean isJumping;
    private boolean isDicking;

    private boolean isLanding;


    public Human(float x, float y, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(x, y, width, height, mass, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run();

    public abstract void jump();

    public abstract void dick();

    public abstract void standUp();

    public abstract void stopRun();

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean getIsDicking() {
        return isDicking;
    }

    public void setIsDicking(boolean dicking) {
        isDicking = dicking;
    }

    public boolean getIsLanding() {
        return isLanding;
    }

    public void setIsLanding(boolean landing) {
        isLanding = landing;
    }

    @Override
    public void Update(){

        super.Update();

        if(getState() == ALIVE || getState() == NOBEHURT){

            if(!isLanding){

                setPosX(getPosX() + getSpeedX());

                if(getDirection() == LEFT_DIR &&
                    getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null){

                    Rectangle recLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(recLeftWall.x + recLeftWall.width + getWidth() / 2);
                }

                if(getDirection() == RIGHT_DIR &&
                     getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null){

                    Rectangle recRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(recRightWall.x - getWidth() / 2);
                }

                // Code below check the postY of Megaman
                // We must check below the character when he speedY = 0

                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                // Lenh if neu getSpeedY() != 0 thi gan cho no bang getSpeedY() con neu no = 0 thi gan cho no bang 2
                boundForCollisionWithMapFuture.y += (getSpeedY() != 0 ? getSpeedY() : 2);
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithLand(boundForCollisionWithMapFuture);

                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(boundForCollisionWithMapFuture);

                if (rectTop != null){

                    setSpeedY(0);
                    setPosY(rectTop.y + getGameWorld().physicalMap.getTitleSize() + getHeight() / 2);
                }
                else if(rectLand != null){
                    setIsJumping(false);
                    if (getSpeedY() > 0) setIsLanding(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight() / 2 - 1);

                }
                else {
                    setIsJumping(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
            }
        }
    }
}


