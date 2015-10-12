package org.funky.test.javafx.explorer;

import javafx.beans.property.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * A directory content.
 */
public class DirectoryContent {

    private final ReadOnlyStringProperty name;
    private final ReadOnlyLongProperty size;
    private final SimpleStringProperty contentType;
    private final SimpleObjectProperty<LocalDate> lastModified;

    public DirectoryContent(Path path) throws IOException {
        this.name = new SimpleStringProperty(path.getFileName().toString());
        this.size = new SimpleLongProperty(sizeOf(path));
        this.contentType = new SimpleStringProperty(contentTypeOf(path));
        this.lastModified = new SimpleObjectProperty<>(lastModifiedOf(path));
    }

    private LocalDate lastModifiedOf(Path path) throws IOException {
        return Files.getLastModifiedTime(path).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private String contentTypeOf(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            return "Directory";
        } else {
            return Files.probeContentType(path);
        }
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

    public SimpleStringProperty contentTypeProperty() {
        return contentType;
    }

    public SimpleObjectProperty<LocalDate> lastModifiedProperty() {
        return lastModified;
    }

    @Override
    public String toString() {
        return name.get();
    }
}
