package com.keydak.uitool;

import com.keydak.contronller.alarm.AlarmController;
import com.keydak.contronller.alarm.AlarmTableController;
import com.keydak.contronller.alarm.AlarmTableRowController;
import com.keydak.data.AlarmData;
import com.keydak.model.Alarm;
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
public class AlarmTablePageRender {

    public static Node createPage(AlarmController controller, Integer pageIndex) {
        Page page = AlarmData.getAlarmPage( new Page( pageIndex + 1 ) );
        List<Alarm> alarms = page.getDatas();

        AlarmTableController tableController = new AlarmTableController( controller );
        VBox vBox = tableController.getVBox();
        Integer dataSize = alarms.size();
        vBox.setSpacing( dataSize );
        // 填充页内容
        Alarm alarm = null;
        for (int i = 0; i < page.getPageSize() ; i++) {
            if( i < dataSize ) {
                // 填充行内容
                alarm = alarms.get(i);
                vBox.getChildren().add( new AlarmTableRowController( tableController, alarm ) );
            }
        }
        return tableController;
    }
}
