package org.funky.test.javafx.explorer.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.funky.test.javafx.explorer.model.ExplorerModel;
import org.funky.test.javafx.explorer.model.FileModel;

import java.time.LocalDate;

/**
 * The controller of the table view.
 */
public class TableViewController {

    @FXML
    private TableView<FileModel> tableView;
    @FXML
    private TableColumn<FileModel, String> nameColumn;
    @FXML
    private TableColumn<FileModel, Number> sizeColumn;
    @FXML
    private TableColumn<FileModel, String> contentTypeColumn;
    @FXML
    private TableColumn<FileModel, LocalDate> lastModifiedColumn;

    /**
     * Sets the Explorer Model.
     * @param explorerModel the ExplorerModel
     */
    public void setExplorerModel(ExplorerModel explorerModel) {
        tableView.itemsProperty().bind(explorerModel.filesProperty());
        nameColumn.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        sizeColumn.setCellValueFactory(cellValue -> cellValue.getValue().sizeProperty());
        contentTypeColumn.setCellValueFactory(cellValue -> cellValue.getValue().contentTypeProperty());
        lastModifiedColumn.setCellValueFactory(cellValue -> cellValue.getValue().lastModifiedProperty());
        tableView.setOnMouseClicked(e -> updatePath(explorerModel, e));
    }

    private void updatePath(ExplorerModel explorerModel, MouseEvent e) {
        if (e.getClickCount() == 2) {
            if (tableView.getSelectionModel().getSelectedItem().isDirectory()) {
                explorerModel.setCurrentPath(tableView.getSelectionModel().getSelectedItem().getPath().toString());
            }
        }
    }

}
