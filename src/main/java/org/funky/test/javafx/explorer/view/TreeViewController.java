package org.funky.test.javafx.explorer.view;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.funky.test.javafx.explorer.ComputerTreeItem;
import org.funky.test.javafx.explorer.PathTreeItem;
import org.funky.test.javafx.explorer.model.ExplorerModel;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Controller of the tree view.
 */
public class TreeViewController {

    @FXML
    private TreeView<String> treeView;

    /**
     * Sets the ExplorerModel.
     * @param explorerModel the ExplorerModel
     */
    public void setExplorerModel(ExplorerModel explorerModel) {
        treeView.setRoot(new ComputerTreeItem());
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof PathTreeItem) {
                explorerModel.setCurrentPath(((PathTreeItem) newValue).getPath().toString());
            }
        });
        explorerModel.currentPathProperty().addListener((observable, oldValue, newValue) -> open(newValue));
    }

    private void open(String newValue) {
        Path path = Paths.get(newValue);
        TreeItem<String> current = rootItem();
        for (Path subPath : path) {
            current.setExpanded(true);
            current = current.getChildren().stream().filter(i -> i.getValue().equals(subPath.toString())).findFirst().get();
        }
    }

    private TreeItem<String> rootItem() {
        return treeView.getRoot().getChildren().get(0);
    }

}
