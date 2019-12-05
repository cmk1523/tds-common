package com.techdevsolutions.common.beans.auditable;

import com.techdevsolutions.common.service.core.DateUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Auditable implements Serializable, Comparable<Auditable> {
    @NotBlank
    private String id;

    private String name;

    @NotNull
    private Long created;

    @NotNull
    private String createdStr;

    @NotNull
    private List<@NotBlank String> tags = new ArrayList<>();

    @Override
    public int compareTo(Auditable o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Auditable{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", createdStr='" + createdStr + '\'' +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditable)) return false;
        Auditable auditable = (Auditable) o;
        return Objects.equals(id, auditable.id) &&
                Objects.equals(name, auditable.name) &&
                Objects.equals(created, auditable.created) &&
                Objects.equals(createdStr, auditable.createdStr) &&
                Objects.equals(tags, auditable.tags);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, created, createdStr, tags);
    }

    public String getCreatedStr() {
        return createdStr;
    }

    public List<String> getTags() {
        return tags;
    }

    public Auditable setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
//    public Auditable setCreatedStr(String createdStr) {
//        this.createdStr = createdStr;
//        return this;
//    }

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

        if (this.created != null) {
            this.createdStr = DateUtils.DateToISO(this.created);
        }

        return this;
    }
}
