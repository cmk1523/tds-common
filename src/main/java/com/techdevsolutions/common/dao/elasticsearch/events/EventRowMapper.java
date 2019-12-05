package com.techdevsolutions.common.dao.elasticsearch.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.techdevsolutions.common.beans.elasticsearchCommonSchema.Event;
import com.techdevsolutions.common.service.core.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

public class EventRowMapper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public EventRowMapper() {
        this.objectMapper.registerModule(new Jdk8Module());
    }

    public Event fromJson(String json) throws IOException, ParseException {
        return this.fromJson(json, "");
    }

    public Event fromJson(String json, String id) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.ISO_STRING);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Map<String, Object> map = this.objectMapper.readValue(json, Map.class);
        map.put("action", map.remove("event.action"));
        map.put("category", map.remove("event.category"));
        map.put("code", map.remove("event.code"));
        map.put("created", map.remove("event.created"));
        map.put("dataset", map.remove("event.dataset"));
        map.put("duration", map.remove("event.duration"));
        map.put("start", map.remove("event.start"));
        map.put("end", map.remove("event.end"));
        map.put("hash", map.remove("event.hash"));
        map.put("id", map.remove("event.id"));
        map.put("kind", map.remove("event.kind"));
        map.put("module", map.remove("event.module"));
        map.put("original", map.remove("event.original"));
        map.put("outcome", map.remove("event.outcome"));
        map.put("provider", map.remove("event.provider"));
        map.put("riskScore", map.remove("event.risk_score"));
        map.put("risrScoreNormalized", map.remove("event.risk_score_norm"));
        map.put("sequence", map.remove("event.sequence"));
        map.put("severity", map.remove("event.severity"));
        map.put("timezone", map.remove("event.timezone"));
        map.put("type", map.remove("event.type"));
        map.put("data", map.remove("event.data"));

        if (map.get("id") == null) {
            map.put("id", id);
        }

        return this.objectMapper.convertValue(map, Event.class);
    }

    public String toJson(Event object) throws JsonProcessingException {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
