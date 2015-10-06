package org.funky.test.javafx.explorer;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.nio.file.Path;

/**
 * A directory content.
 */
public class DirectoryContent {

    private final ReadOnlyStringProperty name;
    private final ReadOnlyStringProperty path;

    public DirectoryContent(Path path) {
        this.name = new SimpleStringProperty(path.getFileName().toString());
        this.path = new SimpleStringProperty(path.toString());
    }

    public String getName() {
        return name.get();
    }

    public ReadOnlyStringProperty  nameProperty() {
        return name;
    }

    public String getPath() {
        return path.get();
    }

    public ReadOnlyStringProperty  pathProperty() {
        return path;
    }

}
