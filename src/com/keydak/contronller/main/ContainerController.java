package com.keydak.contronller.main;

import com.keydak.contronller.UIController;
import com.keydak.contronller.dialog.AddProjectDialog;
import com.keydak.uitool.DialogUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import lombok.Data;

import java.io.IOException;

@Data
public class ContainerController extends BorderPane implements UIController {

    @FXML
    private Button addProject;
    @FXML
    private Pagination pagination;


    public ContainerController() {
        loadFxml();
        initUi();
    }
    @Override
    public void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/keydak/contronller/fxml/container.fxml"));
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

        registerListener();
    }

    // 设置UI的事件处理
    @Override
    public void registerListener() {
        addProject.setOnAction( event -> {
            DialogUtil.showDialog( new AddProjectDialog( this, myEvent -> {

            }), "添加项目" );
        });
    }


}
