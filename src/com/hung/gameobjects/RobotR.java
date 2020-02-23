
package com.hung.gameobjects;

import com.hung.effect.Animation;
import com.hung.effect.CacheDataLoader;
import static com.hung.gameobjects.ParticularObject.LEFT_DIR;
import static com.hung.gameobjects.ParticularObject.NOBEHURT;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RobotR extends ParticularObject {

    private Animation forwardAnim, backAnim;

    private long startTimeToShoot;
    private float x1, x2, y1, y2;

    private AudioClip shooting;

    public RobotR(float x, float y, GameWorld gameWorld) {
        super(x, y, 127, 89, 0, 100, gameWorld);
        backAnim = CacheDataLoader.getInstance().getAnimation("robotR");
        forwardAnim = CacheDataLoader.getInstance().getAnimation("robotR");
        forwardAnim.flipAllImage();
        startTimeToShoot = 0;
        setTimeForNoBehurt(300000000);
        setDamage(10);

        x1 = x - 100;
        x2 = x + 100;
        y1 = y - 50;
        y2 = y + 50;

        setSpeedX(1);
        setSpeedY(1);

        shooting = CacheDataLoader.getInstance().getSound("robotRshooting");
    }

    @Override
    public void attack() {

        shooting.play();
        Bullet bullet = new RobotRBullet(getPosX(), getPosY(), getGameWorld());

        if(getDirection()==LEFT_DIR)
            bullet.setSpeedX(5);
        else bullet.setSpeedX(-5);
        bullet.setTeamType(getTeamType());
        getGameWorld().bulletManager.addObject(bullet);

    }


    public void Update(){
        super.Update();

        if(getPosX() - getGameWorld().megaman.getPosX() > 0) setDirection(ParticularObject.RIGHT_DIR);
        else setDirection(ParticularObject.LEFT_DIR);

        if(getPosX() < x1)
            setSpeedX(1);
        else if(getPosX() > x2)
            setSpeedX(-1);
        setPosX(getPosX() + getSpeedX());

        if(getPosY() < y1)
            setSpeedY(1);
        else if(getPosY() > y2)
            setSpeedY(-1);
        setPosY(getPosY() + getSpeedY());

        if(System.nanoTime() - startTimeToShoot > 1000*10000000*1.5){
            attack();
            startTimeToShoot = System.nanoTime();
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x += 20;
        rect.width -= 40;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(!isObjectOutOfCameraView()){
            if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1){
                // plash...
            }else{
                if(getDirection() == LEFT_DIR){
                    backAnim.Update(System.nanoTime());
                    backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }else{
                    forwardAnim.Update(System.nanoTime());
                    forwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
            }
        }
        //drawBoundForCollisionWithEnemy(g2);
    }

}
