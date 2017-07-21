package com.keydak.uitool;

import com.keydak.contronller.docment.DocController;
import com.keydak.contronller.docment.DocTableController;
import com.keydak.contronller.docment.DocTableRowController;
import com.keydak.data.DocData;
import com.keydak.model.Document;
import com.keydak.uitool.page.Page;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.List;



/**
 * User: vk
 * Date: 2017/4/18
 * Time: 12:07
 * Description:
 */
public class DocTablePageRender {


    public static Node createPage(DocController controller, Integer pageIndex) {
        Page page = DocData.getDocumentPage(controller.getProject().getId().toString(), new Page<Document>( pageIndex + 1 ) );
        List<Document> documents = page.getDatas();

        DocTableController tableController = new DocTableController( controller );
        VBox vBox = tableController.getVBox();
        Integer dataSize = documents.size();
        vBox.setSpacing( dataSize );
        // 填充页内容
        Document document = null;
        for (int i = 0; i < page.getPageSize() ; i++) {
            if( i < dataSize ) {
                // 填充行内容
                document = documents.get(i);
                vBox.getChildren().add( new DocTableRowController( tableController, document ) );
            }
        }
        return tableController;
    }
}
