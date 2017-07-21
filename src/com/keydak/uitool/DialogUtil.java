package com.keydak.uitool;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * User: vk
 * Date: 2017/4/17
 * Time: 14:12
 * Description:
 *
 * 显示对话框
 */
public class DialogUtil {
    public static void showDialog( Parent root, String title ) {
        Stage dialog = new Stage();
        dialog.setScene(new Scene( root));
        dialog.setTitle(title);
        dialog.showAndWait();
    }
}
