package org.champlain.oop2.oop2a1f26;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Person {
    private final String aName;
    private final LocalDate aDOB;
    private final String aEmail;
    private boolean aHasParkingPass;
    private static final List<Person> aPersons = new ArrayList<>();

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

    public String getName() {
        return this.aName;
    }

    public LocalDate getDOB() {
        return this.aDOB;
    }

    public String getEmailAddress() {
        return this.aEmail;
    }

    public boolean purchaseParkingPass() {
        boolean canPurchase = aHasParkingPass;

        if (!this.aHasParkingPass) {
            this.aHasParkingPass = true;
        }

        return !canPurchase;
    }

    public boolean isPurchasedParkingPass() {
        return aHasParkingPass;
    }

    @Override
    public String toString() {
        return this.aName + ", " + aDOB + ", " + aEmail;
    }
}
