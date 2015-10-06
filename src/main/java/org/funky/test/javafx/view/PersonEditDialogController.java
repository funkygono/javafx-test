package org.funky.test.javafx.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.funky.test.javafx.DateUtils;
import org.funky.test.javafx.Person;

public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtils.format(person.getBirthDate()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthDate(DateUtils.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (isEmpty(firstNameField)) {
            errorMessage += "No valid first name!\n";
        }
        if (isEmpty(lastNameField)) {
            errorMessage += "No valid last name!\n";
        }
        if (isEmpty(streetField)) {
            errorMessage += "No valid street!\n";
        }
        if (isEmpty(postalCodeField)) {
            errorMessage += "No valid postal code!\n";
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }
        if (isEmpty(cityField)) {
            errorMessage += "No valid city!\n";
        }
        if (isEmpty(birthdayField)) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtils.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday (Use the format 'dd.mm.yyyy')!\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please correct invalid fields.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private static boolean isEmpty(TextField textField) {
        return textField.getText() == null || textField.getText().length() == 0;
    }
}
