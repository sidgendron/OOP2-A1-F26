package org.champlain.oop2.oop2a1f26;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String aName;
    private LocalDate aDOB;
    private String aEmail;
    private boolean aHasParkingPass;
    private static List<Person> aPersons = new ArrayList<>();

    public Person(String pName, LocalDate pDOB, String pEmail) {
        this.aName = pName;
        this.aDOB = pDOB;
        this.aEmail = pEmail;
        this.aHasParkingPass = false;
        Person.aPersons.add(this);
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
        if (!this.aHasParkingPass) {
            this.aHasParkingPass = true;
        }

        return aHasParkingPass;
    }

    public boolean isPurchasedParkingPass() {
        return aHasParkingPass;
    }
}
