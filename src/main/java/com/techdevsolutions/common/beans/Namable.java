package com.techdevsolutions.common.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Namable {
    protected String name;
    protected List<String> aliases = new ArrayList<>();

    public Namable() {
    }

    public Namable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Namable{" +
                "name='" + name + '\'' +
                ", aliases=" + aliases +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Namable)) return false;
        Namable namable = (Namable) o;
        return Objects.equals(name, namable.name) &&
                Objects.equals(aliases, namable.aliases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, aliases);
    }

    public String getName() {
        return name;
    }

    public Namable setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public Namable setAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }
}
