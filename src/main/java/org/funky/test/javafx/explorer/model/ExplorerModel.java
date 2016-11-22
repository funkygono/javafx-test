package org.funky.test.javafx.explorer.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static javafx.collections.FXCollections.observableArrayList;

/**
 * Main model of the explorer.
 */
public class ExplorerModel {

    private final StringProperty currentPath = new SimpleStringProperty();
    private final ListProperty<FileModel> files = new SimpleListProperty<>(observableArrayList());
    private final ListProperty<Exception> errors = new SimpleListProperty<>(observableArrayList());

    /**
     * Creates a new ExplorerModel instance.
     */
    public ExplorerModel() {
        currentPath.addListener((observable, oldValue, newValue) -> updateDirectoryFiles());
    }

    private void updateDirectoryFiles() {
        files.set(observableArrayList(getFileModelsOfCurrentPath()));
    }

    private List<FileModel> getFileModelsOfCurrentPath() {
        try (Stream<Path> paths = getCurrentDirectoryFilesPaths()) {
            return paths
                    .map(FileModel::new)
                    .sorted((a, b) -> a.getName().compareTo(b.getName()))
                    .collect(toList());
        } catch (IOException e) {
            errors.add(e);
            return emptyList();
        }
    }

    private Stream<Path> getCurrentDirectoryFilesPaths() throws IOException {
        return Files.list(Paths.get(currentPath.get()));
    }

    /**
     * Returns the current path StringProperty.
     * @return the current path property
     */
    public StringProperty currentPathProperty() {
        return currentPath;
    }

    /**
     * Returns the files ListProperty. Files are represented as instances of FileModel.
     * @return the files property
     */
    public ListProperty<FileModel> filesProperty() {
        return files;
    }

    /**
     * Returns the current path.
     * @return the current path
     */
    public String getCurrentPath() {
        return currentPath.get();
    }

    /**
     * Sets the current path. This is a shortcut to <code>currentPathProperty().set(currentPath);</code>.
     * @param currentPath the current path to set
     */
    public void setCurrentPath(String currentPath) {
        this.currentPath.set(currentPath);
    }

    public ListProperty<Exception> getErrorsProperty() {
        return errors;
    }
}
