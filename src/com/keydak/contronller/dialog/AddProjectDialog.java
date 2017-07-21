package com.keydak.contronller.dialog;

import com.keydak.contronller.UIController;
import com.keydak.uitool.MyCallBack;
import com.keydak.uitool.MyEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.Data;

import java.io.IOException;

/**
 * User: vk
 * Date: 2017/4/17
 * Time: 11:19
 * Description:
 */
@Data
public class AddProjectDialog extends BorderPane implements UIController {
    @FXML private Label alertLabel;
    @FXML private TextField projectName;
    @FXML private TextField leader;
    @FXML private DatePicker payDate;
    @FXML private DatePicker carryOutDate;
    @FXML private Button submitAdd;


    private UIController uIController;

    // 确认添加回调
    private MyCallBack myCallBack;

    public AddProjectDialog(UIController uIController, MyCallBack myCallBack ) {
        this.uIController = uIController;
        this.myCallBack = myCallBack;
        loadFxml();
        initUi();
    }

    @Override
    public void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/keydak/contronller/fxml/add_project_dialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public void initUi() {
        alertLabel.setVisible(false);
        registerListener();
    }

    @Override
    public void registerListener() {
        submitAdd.setOnAction( (event)->{
            myCallBack.handle( new MyEvent(this) );
        });
    }

    public boolean validInput() {
        if( projectName == null || projectName.getText().equals("") || leader == null || leader.getText().equals("") ) {
            alertLabel.setVisible(true);
            return false;
        }
        return true;
    }
}
