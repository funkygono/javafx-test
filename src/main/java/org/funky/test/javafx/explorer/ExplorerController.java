package org.funky.test.javafx.explorer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.funky.test.javafx.explorer.address.AddressBarController;
import org.funky.test.javafx.explorer.model.ExplorerModel;
import org.funky.test.javafx.explorer.view.ListViewController;
import org.funky.test.javafx.explorer.view.TableViewController;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * The controller of the explorer.
 */
public class ExplorerController implements Initializable {

    private final ExplorerModel explorerModel = new ExplorerModel();

    @FXML
    private AddressBarController addressBarController;
    @FXML
    private TableViewController tableViewController;
    @FXML
    private ListViewController listViewController;
    @FXML
    private Parent addressBar;
    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressBarController.setCurrentPathProperty(explorerModel.currentPathProperty());
        tableViewController.setExplorerModel(explorerModel);
        listViewController.setExplorerModel(explorerModel);

        treeView.setRoot(new ComputerTreeItem());
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof PathTreeItem) {
                explorerModel.setCurrentPath(((PathTreeItem) newValue).getPath().toString());
            }
        });


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

}
