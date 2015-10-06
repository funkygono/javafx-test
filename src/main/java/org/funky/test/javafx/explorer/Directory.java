package org.funky.test.javafx.explorer;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The directory being watched.
 */
public class Directory {

    private final StringProperty path;
    private final ListProperty<DirectoryContent> directoryContents;

    public Directory() {
        path = new SimpleStringProperty();
        directoryContents = new SimpleListProperty<>();
        path.addListener((observable, oldValue, newValue) -> setDirectoryContents(getDirectoryContents(Paths.get(newValue))));
    }

    private ObservableList<DirectoryContent> getDirectoryContents(Path path) {
        ObservableList<DirectoryContent> result = FXCollections.observableArrayList();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, entry -> !Files.isHidden(entry))) {
            for (Path child : stream) {
                result.add(new DirectoryContent(child));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public ObservableList<DirectoryContent> getDirectoryContents() {
        return directoryContents.get();
    }

    public ListProperty<DirectoryContent> directoryContentsProperty() {
        return directoryContents;
    }

    public void setDirectoryContents(ObservableList<DirectoryContent> directoryContents) {
        this.directoryContents.set(directoryContents);
    }
}
