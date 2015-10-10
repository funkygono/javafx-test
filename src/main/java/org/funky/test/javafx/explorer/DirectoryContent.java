package org.funky.test.javafx.explorer;

import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A directory content.
 */
public class DirectoryContent {

    private final ReadOnlyStringProperty name;
    private final ReadOnlyLongProperty size;
    private final ReadOnlyStringProperty path;

    public DirectoryContent(Path path) {
        this.name = new SimpleStringProperty(path.getFileName().toString());
        this.size = new SimpleLongProperty(sizeOf(path));
        this.path = new SimpleStringProperty(path.toString());
    }

    private long sizeOf(Path path) {
        try {
            if (Files.isDirectory(path)) {
                return -1;
            } else {
                return Files.size(path);
            }
        } catch (IOException e) {
            return -1;
        }
    }

    public String getName() {
        return name.get();
    }

    public ReadOnlyStringProperty  nameProperty() {
        return name;
    }

    public ReadOnlyLongProperty sizeProperty() {
        return size;
    }

    @Override
    public String toString() {
        return name.get();
    }
}
