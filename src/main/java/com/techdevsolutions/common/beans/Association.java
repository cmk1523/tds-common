package com.techdevsolutions.common.beans;

import java.util.Objects;

public class Association<T> {
    protected String description;
    protected T association;
    protected Source source;

    public Association() {
    }

    public Association( T association, String description, Source source) {
        this.association = association;
        this.description = description;
        this.source = source;
    }

    @Override
    public String toString() {
        return "Association{" +
                "description='" + description + '\'' +
                ", association=" + association +
                ", source=" + source +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Association)) return false;
        Association<?> that = (Association<?>) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(association, that.association) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, association, source);
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getAssociation() {
        return association;
    }

    public Association<T> setAssociation(T association) {
        this.association = association;
        return this;
    }
}
