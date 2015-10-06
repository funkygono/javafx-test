package org.funky.test.javafx.explorer;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.isHidden;

/**
 * A {@link TreeItem} for {@link Path}s.
 */
public class PathTreeItem extends TreeItem<String> {

    private final Path path;
    private boolean isChildrenInitialized = false;

    public PathTreeItem(Path path) {
        super(path.getFileName() == null ? "/" :  path.getFileName().toString());
        this.path = path;
    }

    @Override
    public ObservableList<TreeItem<String>> getChildren() {
        if (!isChildrenInitialized) {
            isChildrenInitialized = true;
            try {
                for (Path path : subDirectories()) {
                    super.getChildren().add(new PathTreeItem(path));
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return super.getChildren();
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    public Path getPath() {
        return path;
    }

    private List<Path> subDirectories() {
        try {
            List<Path> subDirectories = new ArrayList<>();
            Files.newDirectoryStream(path, f -> isDirectory(f) && !isHidden(f)).forEach(subDirectories::add);
            Collections.sort(subDirectories, (o1, o2) -> o1.getFileName().compareTo(o2.getFileName()));
            return subDirectories;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
