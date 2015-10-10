package org.funky.test.javafx.explorer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller of the explorer.
 */
public class ExplorerController implements Initializable {

    private final Directory currentDirectory = new Directory();

    @FXML
    private TextField addressTextField;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private TableView<DirectoryContent> tableView;
    @FXML
    private TableColumn<DirectoryContent, String> nameColumn;
    @FXML
    private TableColumn<DirectoryContent, Number> sizeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setRoot(new ComputerTreeItem());
        addressTextField.textProperty().bindBidirectional(currentDirectory.pathProperty());
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof PathTreeItem) {
                currentDirectory.setPath(((PathTreeItem) newValue).getPath().toString());
            }
        });
        tableView.itemsProperty().bind(currentDirectory.directoryContentsProperty());
        nameColumn.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        sizeColumn.setCellValueFactory(cellValue -> cellValue.getValue().sizeProperty());

        // initialize the default address to the user home
        currentDirectory.setPath(System.getProperty("user.home"));

    }

}
