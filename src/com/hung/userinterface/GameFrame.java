package com.hung.userinterface;

import com.hung.effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {

    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;
    // static la de truy xuat bien sau tu nguon khac
    // final giong hang, trong qua trinh chay chuong trinh thi bien nay khong sai duoc

    // Khai bao GamePanel vao GameFrame - JPanel la khung chua nho trong man hinh JFrame
    GamePanel gamePanel;

    public GameFrame(){

        Toolkit toolkit = this.getToolkit(); // de lay size cua man hinh may tinh
        //Dimension la doi tuong giup luu 1 cap gia tri Width va Height sau khi getScreen
        Dimension dimension = toolkit.getScreenSize();
        // Dua man hinh game vao chinh giua Screen
        this.setBounds((dimension.width - SCREEN_WIDTH)/2, (dimension.height - SCREEN_HEIGHT)/2, SCREEN_WIDTH, SCREEN_HEIGHT );

        //Khi tat cua so game thi chuong trinh tu dong stop
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add GamePanel vao GameFrame - JPanel la khung chua nho trong man hinh JFrame
        gamePanel = new GamePanel();
        add(gamePanel);

        this.addKeyListener(gamePanel);

        // goi nhu vay khong tot :(
//        gamePanel.startGame();
    }

    //viet nguyen phuong thuc cho GameFrame luon
    public void startGame(){
        gamePanel.startGame();

    }

    public static void main(String[] args) {

        // de chay GameFrame thi phai new no ra va setVisible
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        // goi phuong thuc startGame
        gameFrame.startGame();
    }

}
