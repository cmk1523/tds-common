package com.techdevsolutions.common.beans.auditable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Auditable implements Serializable {
    private String id = "";
    private String name = "";
    private String createdBy = "";
    private String updatedBy = "";
    private Date createdDate = new Date();
    private Date updatedDate = new Date();
    private Boolean removed = false;

    public Auditable() {
    }

    public Auditable(String id, String name, String createdBy, String updatedBy, Date createdDate, Date updatedDate,
                     Boolean removed) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Auditable{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", removed=" + removed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditable)) return false;
        Auditable auditable = (Auditable) o;
        return Objects.equals(id, auditable.id) &&
                Objects.equals(name, auditable.name) &&
                Objects.equals(createdBy, auditable.createdBy) &&
                Objects.equals(updatedBy, auditable.updatedBy) &&
                Objects.equals(createdDate, auditable.createdDate) &&
                Objects.equals(updatedDate, auditable.updatedDate) &&
                Objects.equals(removed, auditable.removed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, createdBy, updatedBy, createdDate, updatedDate, removed);
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

    public String getCreatedBy() {
        return createdBy;
    }

    public Auditable setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Auditable setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Auditable setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public Auditable setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public Auditable setRemoved(Boolean removed) {
        this.removed = removed;
        return this;
    }
}
