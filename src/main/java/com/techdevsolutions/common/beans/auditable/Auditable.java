package com.techdevsolutions.common.beans.auditable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Auditable implements Serializable, Comparable<Auditable> {
    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private Long created;

    @Override
    public String toString() {
        return "Auditable{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditable)) return false;
        Auditable auditable = (Auditable) o;
        return Objects.equals(id, auditable.id) &&
                Objects.equals(name, auditable.name) &&
                Objects.equals(created, auditable.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, created);
    }

    @Override
    public int compareTo(Auditable o) {
        return this.getId().compareTo(o.getId());
    }

    public String getId() {
        return id;
    }

    public Auditable setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Auditable setName(String name) {
        this.name = name;
        return this;
    }

    public Long getCreated() {
        return created;
    }

    public Auditable setCreated(Long created) {
        this.created = created;
        return this;
    }
}
