package org.funky.test.javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FolderTree extends Application {

    public static void main(String ... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Folder tree sample");
        TreeView<Path> treeView = new TreeView<>(PathTreeItem.getRoot());
        StackPane rootView = new StackPane();
        rootView.getChildren().add(treeView);
        primaryStage.setScene(new Scene(rootView, 320, 200));
        primaryStage.show();
    }

    private static final class PathTreeItem extends TreeItem<Path> {

        private Path path;
        private boolean isFirstTimeChildren = true;
        private boolean isFirstTimeLeaf = true;
        private boolean isLeaf;

        public PathTreeItem(Path path) {
            super(path);
            this.path = path;
        }

        public static PathTreeItem getRoot() {
            return new PathTreeItem(Paths.get("/"));
        }

        @Override
        public ObservableList<TreeItem<Path>> getChildren() {
            if (isFirstTimeChildren) {
                isFirstTimeChildren = false;
                super.getChildren().addAll(buildChildren());
            }
            return super.getChildren();
        }

        @Override
        public boolean isLeaf() {
            if (isFirstTimeLeaf)  {
                isFirstTimeLeaf = false;
                isLeaf = !Files.isDirectory(path);
            }
            return isLeaf;
        }

        private List<PathTreeItem> buildChildren() {
            List<PathTreeItem> children = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, Files::isDirectory)) {
                stream.forEach(p -> children.add(new PathTreeItem(p)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return children;
        }
    }

}
