package com.techdevsolutions.common.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company extends Namable {
    protected Person leader;
    protected List<String> symbols = new ArrayList<>();
    protected List<PersonAssociation> people = new ArrayList<>();

    public Company() {
    }

    public Company(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "leader=" + leader +
                ", symbols=" + symbols +
                ", personAssociations=" + people +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return Objects.equals(leader, company.leader) &&
                Objects.equals(symbols, company.symbols) &&
                Objects.equals(people, company.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), leader, symbols, people);
    }

    public List<PersonAssociation> getPeople() {
        return people;
    }

    public Company setPeople(List<PersonAssociation> people) {
        this.people = people;
        return this;
    }

    public Person getLeader() {
        return leader;
    }

    public Company setLeader(Person leader) {
        this.leader = leader;
        return this;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public Company setSymbols(List<String> symbols) {
        this.symbols = symbols;
        return this;
    }
}
