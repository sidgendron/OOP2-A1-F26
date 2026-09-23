package org.champlain.oop2.oop2a1f26;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents a person which has a name, a date of birth, and an email address.
 *
 * @author Sydney
 */
public class Person {
    /**
     * Saves the person's name. It cannot be left empty.
     */
    private final String aName;
    /**
     * Saves the person's date of birth. It cannot be left empty.
     */
    private final LocalDate aDOB;
    /**
     * Saves the person's email address. It must be written in the format "local-part@domain" and it cannot be left empty.
     */
    private final String aEmail;
    /**
     * Saves whether a person has a parking pass or not.
     */
    private boolean aHasParkingPass;
    /**
     * Saves every new instance of a Person in an ArrayList.
     */
    private static final List<Person> aPersons = new ArrayList<>();

    /**
     * Copy constructor to create a new instance of a Person as a copy of the specified Person and adds the copy to {@link #aPersons}.
     *
     * @param pName String value of the person's name to be copied.
     * @param pDOB LocalDate value of the person's date of birth to be copied.
     * @param pEmail String value of the person's email address to be copied.
     * @throws IllegalArgumentException If the person's name, date of birth, or email address are null.
     * @throws IllegalArgumentException If the person's name or email address only contain whitespaces.
     * @throws IllegalArgumentException If the person's email address has an incorrect format.
     * @throws IllegalArgumentException If the person is already registered.
     */
    public Person(String pName, LocalDate pDOB, String pEmail) {
        // Check if the person's name, DOB, or email address are null.
        if (pName == null || pDOB == null || pEmail == null) {
            throw new IllegalArgumentException("The person's name, date of birth, and email address cannot be null.");
        }

        // Check if the person's name or email only contain whitespaces.
        if (pName.isBlank() || pEmail.isBlank()) {
            throw new IllegalArgumentException("The person's name and email address must contain proper information.");
        }

        // The format of a proper email address.
        String emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@" + "[\\p{L}0-9][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        // Check if the person's email address follows the format "local-part@domain".
        if (!emailPattern.matcher(pEmail).matches()) {
            throw new IllegalArgumentException("The person's email address must follow the format: local-part@domain.");
        }

        // Copy the values.
        this.aName = pName.trim();
        this.aDOB = pDOB;
        this.aEmail = pEmail.trim();
        this.aHasParkingPass = false;

        if (Person.aPersons.contains(this)) {
            // If statement that allows the user to display the example however much they want, but not purchase a pass under its name.
            if (!(aName.equals("John Doe") && aDOB.isEqual(LocalDate.of(2000, 1, 1)) && aEmail.equals("john@gmail.com"))) {
                throw new IllegalArgumentException("This person is already registered.");
            } else {
                this.aHasParkingPass = true;
            }
        // Keep track of every user registered in a list.
        } else {
            Person.aPersons.add(this);
        }
    }

    /**
     * Checks if the current object already exists as an instance of Person and returns true, false otherwise.
     *
     * @param pObject The reference object being compared.
     * @return True if the object already exists as an instance of Person, false otherwise.
     */
    @Override
    public boolean equals(Object pObject) {
        // Check if the object is being compared to itself.
        if (pObject == this) {
            return true;
        }

        // Check if the object isn't a Person (will also return false if pObject is null).
        if (!(pObject instanceof Person)) {
            return false;
        }

        // Typecast pObject to Person to validate if objects are equal.
        Person validator = (Person) pObject;
        return aName.equals(validator.aName) && aDOB.isEqual(validator.aDOB) && aEmail.equals(validator.aEmail);
    }

    /**
     * Get the person's name.
     *
     * @return The person's name as a String.
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Get the person's date of birth.
     *
     * @return The person's date of birth as a LocalDate.
     */
    public LocalDate getDOB() {
        return this.aDOB;
    }

    /**
     * Get the person's email address.
     *
     * @return The person's email address as a String.
     */
    public String getEmailAddress() {
        return this.aEmail;
    }

    /**
     * Check if the current Person can purchase a parking pass.
     *
     * @return True if the current Person doesn't own a parking pass, false otherwise.
     */
    public boolean purchaseParkingPass() {
        boolean canPurchase = aHasParkingPass;

        if (!this.aHasParkingPass) {
            this.aHasParkingPass = true;
        }

        return !canPurchase;
    }

    /**
     * Check if the current Person owns a parking pass.
     *
     * @return True if the current Person owns a parking pass, false otherwise.
     */
    public boolean isPurchasedParkingPass() {
        return aHasParkingPass;
    }

    /**
     * Returns the String representation of a person's name, date of birth, and email address.
     *
     * @return The String representation of a person's name, date of birth, and email address.
     */
    @Override
    public String toString() {
        return this.aName + ", " + aDOB + ", " + aEmail;
    }
}
