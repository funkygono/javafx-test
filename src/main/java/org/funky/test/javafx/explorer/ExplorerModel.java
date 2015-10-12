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
 * A directory model.
 */
public class ExplorerModel {

    private final StringProperty path = new SimpleStringProperty();
    private final ListProperty<FileModel> fileModels = new SimpleListProperty<>();

    public ExplorerModel() {
        path.addListener((observable, oldValue, newValue) -> updateFileModels(newValue));
    }

    private void updateFileModels(String path) {
        ObservableList<FileModel> result = FXCollections.observableArrayList();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path), entry -> !Files.isHidden(entry))) {
            stream.forEach(child -> result.add(new FileModel(child)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        fileModels.set(result);
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public ListProperty<FileModel> fileModelsProperty() {
        return fileModels;
    }

    public String getPath() {
        return path.get();
    }
}
