package org.funky.test.javafx.explorer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The state of the explorer application.
 */
public class State {

    private final StringProperty path;


    public State() {
        this.path = new SimpleStringProperty();
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

}
