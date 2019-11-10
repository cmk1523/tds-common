package com.techdevsolutions.common.beans.elasticsearchCommonSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Base {
    private List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public Base setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "Base{" +
                "tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Base)) return false;
        Base base = (Base) o;
        return Objects.equals(tags, base.tags);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tags);
    }
}
