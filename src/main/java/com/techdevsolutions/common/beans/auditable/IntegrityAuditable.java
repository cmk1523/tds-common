package com.techdevsolutions.common.beans.auditable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class IntegrityAuditable extends Auditable implements Serializable, Comparable<Auditable> {
    @NotNull
    private String hash;

    @NotNull
    private String formerHash;

    @Override
    public String toString() {
        return "IntegrityAuditable{" +
                "hash='" + hash + '\'' +
                ", formerHash='" + formerHash + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegrityAuditable)) return false;
        if (!super.equals(o)) return false;
        IntegrityAuditable that = (IntegrityAuditable) o;
        return Objects.equals(hash, that.hash) &&
                Objects.equals(formerHash, that.formerHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hash, formerHash);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFormerHash() {
        return formerHash;
    }

    public void setFormerHash(String formerHash) {
        this.formerHash = formerHash;
    }
}
