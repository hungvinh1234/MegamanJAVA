/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hung.userinterface;

import com.hung.gameobjects.GameWorld;

import java.awt.event.KeyEvent;




public class InputManager {

    private GameWorld gameWorld;

    public InputManager(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }


    public void setPressedButton(int code){

        switch(code){

            case KeyEvent.VK_DOWN:
                gameWorld.megaman.dick();
                break;

            case KeyEvent.VK_RIGHT:
                gameWorld.megaman.setDirection(gameWorld.megaman.RIGHT_DIR);
                gameWorld.megaman.run();
                break;

            case KeyEvent.VK_LEFT:
                gameWorld.megaman.setDirection(gameWorld.megaman.LEFT_DIR);
                gameWorld.megaman.run();
                break;

            case KeyEvent.VK_ENTER:
                if(gameWorld.state == GameWorld.INIT_GAME){
                    if(gameWorld.previousState == GameWorld.GAMEPLAY)
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    else gameWorld.switchState(GameWorld.TUTORIAL);

                    gameWorld.bgMusic.loop();
                    gameWorld.bgMusic.play();

                }
                if(gameWorld.state == GameWorld.TUTORIAL && gameWorld.storyTutorial >= 1){
                    if(gameWorld.storyTutorial<=3){
                        gameWorld.storyTutorial ++;
                        gameWorld.currentSize = 1;
                        gameWorld.textTutorial = gameWorld.texts1[gameWorld.storyTutorial-1];
                    }else{
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    }

                    // for meeting boss tutorial
                    if(gameWorld.tutorialState == GameWorld.MEETFINALBOSS){
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    }
                }
                break;

            case KeyEvent.VK_SPACE:
                gameWorld.megaman.jump();
                break;

            case KeyEvent.VK_A:
                gameWorld.megaman.attack();
                break;

        }

    }

    public void setReleasedButton(int code) {
        switch(code){

            case KeyEvent.VK_UP:

                break;

            case KeyEvent.VK_DOWN:
                gameWorld.megaman.standUp();
                break;

            case KeyEvent.VK_RIGHT:
                if(gameWorld.megaman.getSpeedX() > 0)
                    gameWorld.megaman.stopRun();
                break;

            case KeyEvent.VK_LEFT:
                if(gameWorld.megaman.getSpeedX() < 0)
                    gameWorld.megaman.stopRun();
                break;

            case KeyEvent.VK_ENTER:
//                if(state == GAMEOVER || state == GAMEWIN) {
//                    gamePanel.setState(new MenuState(gamePanel));
//                } else if(state == PAUSEGAME) {
//                    state = lastState;
//                }
                break;

            case KeyEvent.VK_SPACE:

                break;

            case KeyEvent.VK_A:

                break;
            case KeyEvent.VK_ESCAPE:
//                lastState = state;
//                state = PAUSEGAME;
                break;

        }}


}
