package com.techdevsolutions.common.beans.elasticsearchCommonSchema;

import java.util.Date;
import java.util.Objects;

public class Event extends Base {
    private String action;
    private String category;
    private String code;
    private Date created;
    private String dataset;
    private Long duration;
    private Date end;
    private String hash;
    private String id;
    private String kind;
    private String module;
    private String original;
    private String outcome;
    private String provider;
    private Double riskScore;
    private Double risrScoreNormalized;
    private Long sequence;
    private Long severity;
    private String timezone;
    private String type;

    @Override
    public String toString() {
        return "Event{" +
                "action='" + action + '\'' +
                ", category='" + category + '\'' +
                ", code='" + code + '\'' +
                ", created=" + created +
                ", dataset='" + dataset + '\'' +
                ", duration=" + duration +
                ", end=" + end +
                ", hash='" + hash + '\'' +
                ", id='" + id + '\'' +
                ", kind='" + kind + '\'' +
                ", module='" + module + '\'' +
                ", original='" + original + '\'' +
                ", outcome='" + outcome + '\'' +
                ", provider='" + provider + '\'' +
                ", riskScore=" + riskScore +
                ", risrScoreNormalized=" + risrScoreNormalized +
                ", sequence=" + sequence +
                ", severity=" + severity +
                ", timezone='" + timezone + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(action, event.action) &&
                Objects.equals(category, event.category) &&
                Objects.equals(code, event.code) &&
                Objects.equals(created, event.created) &&
                Objects.equals(dataset, event.dataset) &&
                Objects.equals(duration, event.duration) &&
                Objects.equals(end, event.end) &&
                Objects.equals(hash, event.hash) &&
                Objects.equals(id, event.id) &&
                Objects.equals(kind, event.kind) &&
                Objects.equals(module, event.module) &&
                Objects.equals(original, event.original) &&
                Objects.equals(outcome, event.outcome) &&
                Objects.equals(provider, event.provider) &&
                Objects.equals(riskScore, event.riskScore) &&
                Objects.equals(risrScoreNormalized, event.risrScoreNormalized) &&
                Objects.equals(sequence, event.sequence) &&
                Objects.equals(severity, event.severity) &&
                Objects.equals(timezone, event.timezone) &&
                Objects.equals(type, event.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(action, category, code, created, dataset, duration, end, hash, id, kind, module, original, outcome, provider, riskScore, risrScoreNormalized, sequence, severity, timezone, type);
    }

    public String getAction() {
        return action;
    }

    public Event setAction(String action) {
        this.action = action;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Event setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Event setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public Event setCreated(Date created) {
        this.created = created;
        return this;
    }

    public String getDataset() {
        return dataset;
    }

    public Event setDataset(String dataset) {
        this.dataset = dataset;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Event setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public Event setEnd(Date end) {
        this.end = end;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public Event setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getId() {
        return id;
    }

    public Event setId(String id) {
        this.id = id;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public Event setKind(String kind) {
        this.kind = kind;
        return this;
    }

    public String getModule() {
        return module;
    }

    public Event setModule(String module) {
        this.module = module;
        return this;
    }

    public String getOriginal() {
        return original;
    }

    public Event setOriginal(String original) {
        this.original = original;
        return this;
    }

    public String getOutcome() {
        return outcome;
    }

    public Event setOutcome(String outcome) {
        this.outcome = outcome;
        return this;
    }

    public String getProvider() {
        return provider;
    }

    public Event setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public Event setRiskScore(Double riskScore) {
        this.riskScore = riskScore;
        return this;
    }

    public Double getRisrScoreNormalized() {
        return risrScoreNormalized;
    }

    public Event setRisrScoreNormalized(Double risrScoreNormalized) {
        this.risrScoreNormalized = risrScoreNormalized;
        return this;
    }

    public Long getSequence() {
        return sequence;
    }

    public Event setSequence(Long sequence) {
        this.sequence = sequence;
        return this;
    }

    public Long getSeverity() {
        return severity;
    }

    public Event setSeverity(Long severity) {
        this.severity = severity;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public Event setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public String getType() {
        return type;
    }

    public Event setType(String type) {
        this.type = type;
        return this;
    }
}
