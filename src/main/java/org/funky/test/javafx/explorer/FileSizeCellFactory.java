package org.funky.test.javafx.explorer;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * A cell factory that can be used to display a "file size" column.
 */
public class FileSizeCellFactory implements Callback<TableColumn<DirectoryContent, Number>, TableCell<DirectoryContent, Number>> {
    @Override
    public TableCell<DirectoryContent, Number> call(TableColumn<DirectoryContent, Number> param) {
        return new TableCell<DirectoryContent, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                if (empty || item.longValue() == -1) {
                    setText(null);
                } else if (item.longValue() < 1024) {
                    setText(item.toString() + " o");
                } else if (item.longValue() < 1024 * 1024) {
                    setText(Long.toString(item.longValue() / 1024) + " Ko");
                } else {
                    setText(Long.toString(item.longValue() / (1024 * 1024)) + " Mo");
                }
                super.updateItem(item, empty);
            }
        };
    }
}
