package com.techdevsolutions.common.dao.elasticsearch.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techdevsolutions.common.beans.elasticsearchCommonSchema.Event;
import com.techdevsolutions.common.dao.elasticsearch.BaseElasticsearchHighLevel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class EventElasticsearchDAO extends BaseElasticsearchHighLevel {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public final EventRowMapper rowMapper = new EventRowMapper();
    private String index = "events";

    public EventElasticsearchDAO() {
    }

    public EventElasticsearchDAO(String host, String index) {
        super(host);
        this.setIndex(index);
    }

    public String getIndex() {
        return index;
    }

    public EventElasticsearchDAO setIndex(String index) {
        this.index = index;
        return this;
    }

    public static String EventToJson(final Event event) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> itemAsMap = Event.ToElasticsearchMap(event);
        CollectionUtils.filter(itemAsMap.values(), PredicateUtils.notNullPredicate());
        Map<String, Object> dataAsMap = objectMapper.convertValue(event.getData(), Map.class);

        if (dataAsMap != null) {
            CollectionUtils.filter(dataAsMap.values(), PredicateUtils.notNullPredicate());
            itemAsMap.put("event.data", dataAsMap);
        }

        return objectMapper.writeValueAsString(itemAsMap);
    }

    public Event get(String id) throws Exception {
        GetResponse getResponse = this.getDocument(id, this.getIndex());
        Event event = this.rowMapper.fromJson(getResponse.getSourceAsString());
        event.setId(getResponse.getId());
        return event;
    }

    public String create(final Event event) throws Exception {
        String itemAsJson = EventElasticsearchDAO.EventToJson(event);
        return this.createDocument(itemAsJson, this.getIndex());
    }

    public void createBulk(List<Event> events) {
        events.forEach((event)->{
            try {
                String itemAsJson = EventElasticsearchDAO.EventToJson(event);
                IndexRequest indexRequest = new IndexRequest(this.getIndex()).id(event.getId().toLowerCase());
                indexRequest.source(itemAsJson, XContentType.JSON);
                this.getBulkProcessor().add(indexRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void delete(Event event) throws Exception {
        this.deleteDocument(event.getId(), this.getIndex());
    }

    public void delete(String id) throws Exception {
        this.deleteDocument(id, this.getIndex());
    }

    public String update(final Event event) throws Exception {
        String itemAsJson = EventElasticsearchDAO.EventToJson(event);
        return this.updateDocument(itemAsJson, event.getId(), this.getIndex());
    }

    public void setup() {
        this.getClient(this.getHost());

        try {
            this.createIndex(this.getIndex());
        } catch (ElasticsearchStatusException e) {
            if (!e.getDetailedMessage().contains("resource_already_exists_exception")) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
