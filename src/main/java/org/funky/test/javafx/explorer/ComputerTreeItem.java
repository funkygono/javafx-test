package org.funky.test.javafx.explorer;

import javafx.scene.control.TreeItem;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * A {@link TreeItem} that represents the computer within the folder tree view. This item
 * contains all the root directories, and is usually hidden.
 */
public class ComputerTreeItem extends TreeItem<String> {

    public ComputerTreeItem() {
        super("Computer");
        for (Path root : FileSystems.getDefault().getRootDirectories()) {
            getChildren().add(new PathTreeItem(root));
        }
    }

}
