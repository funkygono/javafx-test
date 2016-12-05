package org.funky.test.javafx.explorer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import org.funky.test.javafx.explorer.address.AddressBarController;
import org.funky.test.javafx.explorer.model.ExplorerModel;
import org.funky.test.javafx.explorer.view.ListViewController;
import org.funky.test.javafx.explorer.view.TableViewController;
import org.funky.test.javafx.explorer.view.TreeViewController;

import java.net.URL;
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
    private TreeViewController treeViewController;
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressBarController.setCurrentPathProperty(explorerModel.currentPathProperty());
        tableViewController.setExplorerModel(explorerModel);
        listViewController.setExplorerModel(explorerModel);
        treeViewController.setExplorerModel(explorerModel);
        tabPane.getTabs().get(0).textProperty().bind(explorerModel.currentPathProperty());

        // initialize the default address to the user home
        explorerModel.setCurrentPath(System.getProperty("user.home"));

    }


}
