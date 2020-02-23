package com.hung.effect;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Animation {

    private String name;

    private boolean isRepeated;

//    ArrayList ban chat la mang nhung co cac ham get, set
    private ArrayList<FrameImage> frameImages;
    private int currentFrame;

    // Animation doi luc can bo qua dong tac du thua
    private ArrayList<Boolean> ignoreFrames;

    private ArrayList<Double> delayFrames;

    private long beginTime;

    // Ve khung de trong qua trinh phat trien de thay do rong cua hinh
    private boolean drawRectFrame;

    // Constructor
    public Animation(){
        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;

        ignoreFrames = new ArrayList<Boolean>();

        frameImages = new ArrayList<FrameImage>();

        drawRectFrame = false;

        isRepeated = true;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Cho nay hoi ki
    public boolean getIsRepeated() {
        return isRepeated;
    }

    public void setIsRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
       if(currentFrame >= 0 && currentFrame < frameImages.size())
           this.currentFrame = currentFrame;
       else this.currentFrame = 0;
    }

    public ArrayList<Boolean> getIgnoreFrames() {
        return ignoreFrames;
    }


    public ArrayList<Double> getDelayFrames() {
        return delayFrames;
    }

    public void setDelayFrames(ArrayList<Double> delayFrames) {
        this.delayFrames = delayFrames;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean getDrawRectFrame() {
        return drawRectFrame;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {
        this.drawRectFrame = drawRectFrame;
    }

    // Xet xem cai frame nay co phai la frame bi bo qua khong, neu bo qua thi di toi frame tiep theo
    public boolean isIgnoreFrame(int id){
        return ignoreFrames.get(id);
    }

    // Set frame nao ta muon ignore

    public void setIgnoreFrames(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, true);
    }

//    public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {
//        this.ignoreFrames = ignoreFrames;
//    }

    public void unIgnoreFrames(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, false);
    }

    // Reset animation ve tu the dau tien
    public void reset() {
        currentFrame = 0;
        beginTime = 0;

        for (int i = 0; i < ignoreFrames.size(); i++ ){
            ignoreFrames.set(i, false);
        }
    }

    // Khi tao animation chua co gi can add them frame de no quan ly
    public void add(FrameImage frameImage, double timeToNextFrame){

        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double(timeToNextFrame));
    }

    public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();

    }

    public void Update(long currentTime){

        if(beginTime == 0) beginTime = currentTime;
        else {
            if(currentTime - beginTime > delayFrames.get(currentFrame)){
                nextFrames();
                beginTime =  currentTime;
            }
        }
    }

    private void nextFrames(){

        if(currentFrame >= frameImages.size() -1 ){

            if(isRepeated) currentFrame = 0;
        }
        else currentFrame++;

        if(ignoreFrames.get(currentFrame)) nextFrames();
    }

    public boolean isLastFrame(){
        if(currentFrame == frameImages.size() - 1 )
            return true;
        else return false;
    }

    // Dao chieu Animation khi nhan vat chay tu phai sang trai
    public void flipAllImage(){

        for(int i = 0;i < frameImages.size(); i++){

            BufferedImage image = frameImages.get(i).getImage();

            AffineTransform tx = AffineTransform.getScaleInstance(-1 ,1);
            tx.translate(-image.getWidth(), 0);

            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);

            frameImages.get(i).setImage(image);

        }
    }

    public void draw(int x, int y , Graphics2D g2){

        BufferedImage image = getCurrentImage();

        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
    if(drawRectFrame)
        g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
    
    }
    // Copy Constructor
    public Animation(Animation animation){

        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;

        delayFrames =  new ArrayList<Double>();
        for(Double d : animation.delayFrames){
            delayFrames.add(d);

        }
// Trong video Bien ham tren la Double ben duoi lai la boolean ???
        ignoreFrames = new ArrayList<Boolean>();
        for(Boolean b : animation.ignoreFrames){
            ignoreFrames.add(b);

        }

        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f: animation.frameImages){
            frameImages.add(new FrameImage(f));
        }



    }



}
