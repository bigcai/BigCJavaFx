package com.keydak.uitool;

import com.keydak.contronller.main.ContainerController;
import com.keydak.contronller.main.ProjTableController;
import com.keydak.contronller.main.ProjTableRowController;
import com.keydak.data.ProjectData;
import com.keydak.model.Project;
import com.keydak.uitool.page.Page;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.List;


/**
 * User: vk
 * Date: 2017/4/14
 * Time: 14:26
 * Description:
 */
public class ProjTablePageRender {

    public static Node createPage(ContainerController controller, Integer pageIndex) {
        Page page = ProjectData.getProjectPage( new Page<Project>( pageIndex + 1 ) );
        List<Project> projects = page.getDatas();

        ProjTableController tableController = new ProjTableController( controller );
        VBox vBox = tableController.getVBox();
        Integer dataSize = projects.size();
        vBox.setSpacing( dataSize );
        // 填充页内容
        Project project = null;
        for (int i = 0; i < page.getPageSize() ; i++) {
            if( i < dataSize ) {
                // 填充行内容
                project = projects.get(i);
                vBox.getChildren().add( new ProjTableRowController( tableController, project ) );
            }
        }
        return tableController;
    }
}
