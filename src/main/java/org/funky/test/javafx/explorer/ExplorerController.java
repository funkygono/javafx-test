package org.funky.test.javafx.explorer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * The controller of the explorer.
 */
public class ExplorerController implements Initializable {

    private final ExplorerModel explorerModel = new ExplorerModel();

    @FXML
    private TextField addressTextField;
    @FXML
    private TreeView<String> treeView;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setRoot(new ComputerTreeItem());
        addressTextField.textProperty().bindBidirectional(explorerModel.pathProperty());
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof PathTreeItem) {
                explorerModel.setPath(((PathTreeItem) newValue).getPath().toString());
            }
        });
        tableView.itemsProperty().bind(explorerModel.fileModelsProperty());
        nameColumn.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        sizeColumn.setCellValueFactory(cellValue -> cellValue.getValue().sizeProperty());
        contentTypeColumn.setCellValueFactory(cellValue -> cellValue.getValue().contentTypeProperty());
        lastModifiedColumn.setCellValueFactory(cellValue -> cellValue.getValue().lastModifiedProperty());

        tableView.setRowFactory(tableView -> {
            TableRow<FileModel> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    if (row.getItem().isDirectory()) {
                        explorerModel.setPath(row.getItem().getPath().toString());
                    }
                }
            });
            return row;
        });

        // initialize the default address to the user home
        explorerModel.setPath(System.getProperty("user.home"));

    }

}
