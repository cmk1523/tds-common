package com.techdevsolutions.common.beans;

import java.util.Objects;

public class PersonAssociation extends Association<Person> {

    public PersonAssociation() {
    }

    public PersonAssociation(Person association, String description, Source source) {
        super(association, description, source);
    }
}
