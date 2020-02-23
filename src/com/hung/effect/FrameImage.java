package com.hung.effect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameImage {

    private String name;
    private BufferedImage image;

    // Constructor
    public FrameImage(String name, BufferedImage image){
        this.name = name;
        this.image = image;

    }
// Copy Constructor tao ra mot doi tuong khac giong data khac ham
    public FrameImage(FrameImage frameImage){

        // tao mot image moi de khong xung dot voi image cu
        image = new BufferedImage(frameImage.getImageWidth(), frameImage.getImageHeight()
                        , frameImage.getImage().getType());

        // g duoc hieu nhu cay co de ve bat cu thu gi len image
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.getImage(), 0, 0, null);

    }

    FrameImage(){
        image = null;
        name =null;
    }

    public void draw (Graphics2D g2, int x, int y){

        g2.drawImage(image, x - getImageWidth()/2, y - getImageHeight()/2, null);
    }

    public int getImageWidth(){
        return image.getWidth();
    }

    public int getImageHeight(){
        return image.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
