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
public class FileModel {

    private final ReadOnlyStringProperty name;
    private final ReadOnlyLongProperty size;
    private final SimpleStringProperty contentType;
    private final SimpleObjectProperty<LocalDate> lastModified;
    private final boolean directory;
    private Path path;

    public FileModel(Path path) {
        this.name = new SimpleStringProperty(path.getFileName().toString());
        this.size = new SimpleLongProperty(sizeOf(path));
        this.contentType = new SimpleStringProperty(contentTypeOf(path));
        this.lastModified = new SimpleObjectProperty<>(lastModifiedOf(path));
        this.directory = Files.isDirectory(path);
        this.path = path;
    }

    private LocalDate lastModifiedOf(Path path) {
        try {
            return Files.getLastModifiedTime(path).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private String contentTypeOf(Path path) {
        if (Files.isDirectory(path)) {
            return "Directory";
        } else {
            return probeContentType(path);
        }
    }

    private String probeContentType(Path path) {
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
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

    public boolean isDirectory() {
        return directory;
    }

    public Path getPath() {
        return path;
    }
}
