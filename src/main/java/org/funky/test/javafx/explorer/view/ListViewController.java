package org.funky.test.javafx.explorer.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.funky.test.javafx.explorer.model.ExplorerModel;
import org.funky.test.javafx.explorer.model.FileModel;

/**
 * The controller of the list view.
 */
public class ListViewController {

    @FXML
    private ListView<FileModel> listView;

    /**
     * Sets the ExplorerModel.
     * @param explorerModel the ExplorerModel
     */
    public void setExplorerModel(ExplorerModel explorerModel) {
        listView.itemsProperty().bindBidirectional(explorerModel.filesProperty());
    }
}
