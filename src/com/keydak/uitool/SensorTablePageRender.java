package com.keydak.uitool;

import com.keydak.contronller.sensor.SensorController;
import com.keydak.contronller.sensor.SensorTableController;
import com.keydak.contronller.sensor.SensorTableRowController;
import com.keydak.data.SensorData;
import com.keydak.model.Sensor;
import com.keydak.uitool.page.Page;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.List;



/**
 * User: vk
 * Date: 2017/4/18
 * Time: 16:52
 * Description:
 */
public class SensorTablePageRender {

    public static Node createPage(SensorController controller, Integer pageIndex) {
        Page page = SensorData.getSensorPage( new Page( pageIndex + 1 ) );
        List<Sensor> sensors = page.getDatas();

        SensorTableController tableController = new SensorTableController( controller );
        VBox vBox = tableController.getVBox();
        Integer dataSize = sensors.size();
        vBox.setSpacing( dataSize );
        // 填充页内容
        Sensor sensor = null;
        for (int i = 0; i < page.getPageSize() ; i++) {
            if( i < dataSize ) {
                // 填充行内容
                sensor = sensors.get(i);
                vBox.getChildren().add( new SensorTableRowController( tableController, sensor ) );
            }
        }
        return tableController;
    }
}
