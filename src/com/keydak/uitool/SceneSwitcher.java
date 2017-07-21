package com.keydak.uitool;

import com.keydak.Main;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * User: vk
 * Date: 2017/4/18
 * Time: 11:13
 * Description:
 * 界面切换
 */
public class SceneSwitcher {

    public static void switchScene( Scene beforeScene, Scene afterScene ) {
        if( beforeScene.getWindow().isShowing() ) {
            beforeScene.getWindow().hide();
        }
        Stage newStage = new Stage();
        newStage.setScene(afterScene);
        newStage.show();

        Main.setCurrentStage( newStage );
    }
}
