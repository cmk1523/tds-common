package com.techdevsolutions.common.beans;

import com.techdevsolutions.common.beans.Namable;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;

public class Source extends Namable {
    protected DateTime date;

    public Source() {
    }

    public Source(String name, DateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return "Source{" +
                "date=" + date +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        if (!super.equals(o)) return false;
        Source source = (Source) o;
        return Objects.equals(date, source.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date);
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
