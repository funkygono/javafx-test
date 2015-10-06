package org.funky.test.javafx.explorer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

/**
 * The controller of the explorer.
 */
public class ExplorerController implements Initializable {

    @FXML
    private TextField addressTextField;
    @FXML
    private TreeView<String> folderTreeView;
    @FXML
    private TableView<Path> folderTableView;
    @FXML
    private TableColumn<Path, String> nameColumn;

    public void setAddress(String address) {
        Path path = FileSystems.getDefault().getPath(address);

        if (Files.isDirectory(path)) {
            addressTextField.setText(address);
            updateTreeView(path);
            updateTableView(path);
        } else {
            throw new RuntimeException("Invalid path: " + path);
        }    }

    private void updateTableView(Path path) {
    }

    private void updateTreeView(Path path) {
        TreeItem<String> root = folderTreeView.getRoot();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        folderTreeView.setRoot(new ComputerTreeItem());

        addressTextField.textProperty().addListener((observable, oldValue, newValue) -> setAddress(newValue));
        folderTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof PathTreeItem) {
                setAddress(((PathTreeItem) newValue).getPath().toString());
            }
        });

//        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getFileName().toString());

        // initialize the default address to the user home
        setAddress(System.getProperty("user.home"));

    }

}
