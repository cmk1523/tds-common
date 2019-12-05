package com.techdevsolutions.common.beans.elasticsearchCommonSchema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.techdevsolutions.common.service.core.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;

import java.util.*;

public class Event<T> {
    private String timezone = DateUtils.TIMEZONE_GMT;
    private Long duration;
    private Date start;
    private Date end;
    private Date created;
    private String action;
    private String category;
    private String code;
    private String dataset;
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
    private String type;
    private T data;

    public Map<String, Object> toElasticsearchMap(Event item) {
        return Event.ToElasticsearchMap(item);
    }

    public static Map<String, Object> ToElasticsearchMap(Event item) {
        Map<String, Object> map = new HashMap<>();
        map.put("event.action", item.getAction());
        map.put("event.category", item.getCategory());
        map.put("event.code", item.getCode());
        map.put("event.created", item.getCreated() != null ? DateUtils.DateToISO(item.getCreated()) : null);
        map.put("event.dataset", item.getDataset());
        map.put("event.duration", item.getDuration());
        map.put("event.start", item.getStart() != null ? DateUtils.DateToISO(item.getStart()) : null);
        map.put("event.end", item.getStart() != null ? DateUtils.DateToISO(item.getEnd()) : null);
        map.put("event.hash", item.getHash());
        map.put("event.id", item.getId());
        map.put("event.kind", item.getKind());
        map.put("event.module", item.getModule());
        map.put("event.original", item.getOriginal());
        map.put("event.outcome", item.getOutcome());
        map.put("event.provider", item.getProvider());
        map.put("event.risk_score", item.getRiskScore());
        map.put("event.risk_score_norm", item.getRisrScoreNormalized());
        map.put("event.sequence", item.getSequence());
        map.put("event.severity", item.getSeverity());
        map.put("event.timezone", item.getTimezone());
        map.put("event.type", item.getType());

        if (item.getData() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new Jdk8Module());
            Map<String, Object> dataAsMap = objectMapper.convertValue(item.getData(), Map.class);
            CollectionUtils.filter(dataAsMap.values(), PredicateUtils.notNullPredicate());
            map.put("event.data", item.getData());
        }

        return map;
    }

    @Override
    public String toString() {
        return "Event{" +
                "timezone='" + timezone + '\'' +
                ", duration=" + duration +
                ", start=" + start +
                ", end=" + end +
                ", action='" + action + '\'' +
                ", category='" + category + '\'' +
                ", code='" + code + '\'' +
                ", created=" + created +
                ", dataset='" + dataset + '\'' +
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
                ", type='" + type + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return duration == event.duration &&
                Double.compare(event.riskScore, riskScore) == 0 &&
                Double.compare(event.risrScoreNormalized, risrScoreNormalized) == 0 &&
                sequence == event.sequence &&
                severity == event.severity &&
                Objects.equals(timezone, event.timezone) &&
                Objects.equals(start, event.start) &&
                Objects.equals(end, event.end) &&
                Objects.equals(action, event.action) &&
                Objects.equals(category, event.category) &&
                Objects.equals(code, event.code) &&
                Objects.equals(created, event.created) &&
                Objects.equals(dataset, event.dataset) &&
                Objects.equals(hash, event.hash) &&
                Objects.equals(id, event.id) &&
                Objects.equals(kind, event.kind) &&
                Objects.equals(module, event.module) &&
                Objects.equals(original, event.original) &&
                Objects.equals(outcome, event.outcome) &&
                Objects.equals(provider, event.provider) &&
                Objects.equals(type, event.type) &&
                Objects.equals(data, event.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(timezone, duration, start, end, action, category, code, created, dataset, hash, id, kind, module, original, outcome, provider, riskScore, risrScoreNormalized, sequence, severity, type, data);
    }

    public T getData() {
        return data;
    }

    public Event setData(T data) {
        this.data = data;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public Event setStart(Date start) {
        this.start = start;
        return this;
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
