package org.funky.test.javafx;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Represents a person.
 */
public class Person {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthDate;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * @param firstName the first name
     * @param lastName the last name
     */
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthDate = new SimpleObjectProperty<>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public String getStreet() {
        return street.get();
    }
    public void setStreet(String street) {
        this.street.set(street);
    }
    public StringProperty streetProperty() {
        return street;
    }
    public int getPostalCode() {
        return postalCode.get();
    }
    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }
    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }
    public String getCity() {
        return this.city.get();
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public StringProperty cityProperty() {
        return city;
    }
    public LocalDate getBirthDate() {
        return birthDate.get();
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }
    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }
}
