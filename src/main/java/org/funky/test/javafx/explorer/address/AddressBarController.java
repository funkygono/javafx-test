package org.funky.test.javafx.explorer.address;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * The controller of the address bar.
 */
public class AddressBarController {
    @FXML
    private TextField addressTextField;

    /**
     * Sets the current path property.
     * @param currentPathProperty the current path property
     */
    public void setCurrentPathProperty(StringProperty currentPathProperty) {
        currentPathProperty.bindBidirectional(addressTextField.textProperty());
    }

}
