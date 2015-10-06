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
    private TreeView<String> folderTreeView;
    @FXML
    private TableView<DirectoryContent> folderTableView;
    @FXML
    private TableColumn<DirectoryContent, String> nameColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        folderTreeView.setRoot(new ComputerTreeItem());
        addressTextField.textProperty().bindBidirectional(currentDirectory.pathProperty());
        folderTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof PathTreeItem) {
                currentDirectory.setPath(((PathTreeItem) newValue).getPath().toString());
            }
        });
        folderTableView.itemsProperty().bind(currentDirectory.directoryContentsProperty());
        nameColumn.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());


        // initialize the default address to the user home
        currentDirectory.setPath(System.getProperty("user.home"));

    }

}
