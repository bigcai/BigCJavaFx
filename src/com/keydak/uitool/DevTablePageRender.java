package com.keydak.uitool;

import com.keydak.contronller.device.DeviceController;
import com.keydak.contronller.device.DeviceTableController;
import com.keydak.contronller.device.DeviceTableRowController;
import com.keydak.data.DeviceData;
import com.keydak.model.Device;
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
public class DevTablePageRender {

    public static Node createPage(DeviceController controller, Integer pageIndex) {
        Page page = DeviceData.getDevicePage(controller.getProject().getId().toString(),  new Page( pageIndex + 1 ) );
        List<Device> devices = page.getDatas();

        DeviceTableController tableController = new DeviceTableController( controller );
        VBox vBox = tableController.getVBox();
        Integer dataSize = devices.size();
        vBox.setSpacing( dataSize );
        // 填充页内容
        Device device = null;
        for (int i = 0; i < page.getPageSize() ; i++) {
            if( i < dataSize ) {
                // 填充行内容
                device = devices.get(i);
                vBox.getChildren().add( new DeviceTableRowController( tableController, device ) );
            }
        }
        return tableController;
    }
}
