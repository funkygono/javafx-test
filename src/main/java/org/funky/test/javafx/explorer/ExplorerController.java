package org.funky.test.javafx.explorer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.funky.test.javafx.explorer.model.ExplorerModel;
import org.funky.test.javafx.explorer.model.FileModel;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @FXML
    private ListView<FileModel> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setRoot(new ComputerTreeItem());
        addressTextField.textProperty().bindBidirectional(explorerModel.currentPathProperty());
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof PathTreeItem) {
                explorerModel.setCurrentPath(((PathTreeItem) newValue).getPath().toString());
            }
        });
        tableView.itemsProperty().bind(explorerModel.filesProperty());
        nameColumn.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        sizeColumn.setCellValueFactory(cellValue -> cellValue.getValue().sizeProperty());
        contentTypeColumn.setCellValueFactory(cellValue -> cellValue.getValue().contentTypeProperty());
        lastModifiedColumn.setCellValueFactory(cellValue -> cellValue.getValue().lastModifiedProperty());
        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                handleDoubleClickInTableView();
            }
        });

        listView.itemsProperty().bindBidirectional(explorerModel.filesProperty());

        explorerModel.currentPathProperty().addListener((observable, oldValue, newValue) -> expandTreeViewToCurrentPath());

        // initialize the default address to the user home
        explorerModel.setCurrentPath(System.getProperty("user.home"));

    }

    private void expandTreeViewToCurrentPath() {
        Path path = Paths.get(explorerModel.getCurrentPath());
        TreeItem<String> current = treeView.getRoot().getChildren().get(0); // for the "slash" sub-root path
        for (Path subPath : path) {
            current.setExpanded(true);
            current = current.getChildren().stream().filter(i -> i.getValue().equals(subPath.toString())).findFirst().get();
        }
    }

    private void handleDoubleClickInTableView() {
        if (tableView.getSelectionModel().getSelectedItem().isDirectory()) {
            explorerModel.setCurrentPath(tableView.getSelectionModel().getSelectedItem().getPath().toString());
        }
    }

}
