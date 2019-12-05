package com.techdevsolutions.common.dao.elasticsearch.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techdevsolutions.common.beans.elasticsearchCommonSchema.Event;
import com.techdevsolutions.common.dao.elasticsearch.BaseElasticsearchHighLevel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchModule;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventElasticsearchDAO extends BaseElasticsearchHighLevel {
    public static final String INDEX_BASE_NAME = "events";

    public static final String ACTION_CREATED = "created";
    public static final String CODE_CREATED = "201";
    public static final String KIND_CREATE = "create";

    public static final String ACTION_REMOVED = "removed";
    public static final String CODE_REMOVED = "204";
    public static final String KIND_REMOVED = "remove";

    public static final String ACTION_UPDATED = "updated";
    public static final String CODE_UPDATED = "200";
    public static final String KIND_UPDATED = "update";

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

    public Event getByEventId(final String documentId, final String index) throws Exception {
        if (StringUtils.isEmpty(documentId)) {
            throw new IllegalArgumentException("documentId is null or empty");
        }

        GetResponse getResponse = this.getDocument(documentId, index);
        return this.rowMapper.fromJson(getResponse.getSourceAsString());
    }

    public List<Event> getEventsByEventDataId(final String id, final String category, final String dataset) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("id is null or empty");
        }

        BaseElasticsearchHighLevel baseElasticsearchHighLevel = new BaseElasticsearchHighLevel();
        baseElasticsearchHighLevel.getClient(this.getHost());

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchModule searchModule = new SearchModule(Settings.EMPTY, false, Collections.emptyList());

        String query = "{\n" +
                "  \"size\": 10000,\n" +
                "  \"sort\": [\n" +
                "    {\n" +
                "      \"event.created\": {\n" +
                "        \"order\": \"desc\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"filter\": [\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"event.data.id.keyword\": \"" + id + "\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"event.category\": \"" + category + "\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"event.dataset\": \"" + dataset + "\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        try {
            NamedXContentRegistry namedXContentRegistry = new NamedXContentRegistry(searchModule.getNamedXContents());
            XContent xContent = XContentFactory.xContent(XContentType.JSON);
            XContentParser parser = xContent.createParser(namedXContentRegistry,
                    DeprecationHandler.THROW_UNSUPPORTED_OPERATION, query);
            searchSourceBuilder.parseXContent(parser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SearchRequest searchRequest = new SearchRequest(this.getIndex());
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        List<SearchHit> results = this.getDocumentsWithScroll(searchRequest);
        return results.stream().map((i) -> {
            try {
                Event event = this.rowMapper.fromJson(i.getSourceAsString());
                event.setId(i.getId());
                return event;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Event getEventByEventDataId(final String id, final String category, final String dataset) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("id is null or empty");
        }

        BaseElasticsearchHighLevel baseElasticsearchHighLevel = new BaseElasticsearchHighLevel();
        baseElasticsearchHighLevel.getClient(this.getHost());

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchModule searchModule = new SearchModule(Settings.EMPTY, false, Collections.emptyList());

        String query = "{\n" +
                "  \"size\": 1,\n" +
                "  \"sort\": [\n" +
                "    {\n" +
                "      \"event.created\": {\n" +
                "        \"order\": \"desc\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"filter\": [\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"event.data.id.keyword\": \"" + id + "\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"event.category\": \"" + category + "\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"event.dataset\": \"" + dataset + "\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        try {
            NamedXContentRegistry namedXContentRegistry = new NamedXContentRegistry(searchModule.getNamedXContents());
            XContent xContent = XContentFactory.xContent(XContentType.JSON);
            XContentParser parser = xContent.createParser(namedXContentRegistry,
                    DeprecationHandler.THROW_UNSUPPORTED_OPERATION, query);
            searchSourceBuilder.parseXContent(parser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SearchRequest searchRequest = new SearchRequest(this.getIndex());
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        List<SearchHit> results = this.getDocumentsWithScroll(searchRequest);

        if (results.size() == 0) {
            throw new Exception("Unable to find item by id: " + id);
        }

        SearchHit searchHit = results.get(0);
        Event event = this.rowMapper.fromJson(searchHit.getSourceAsString());
        event.setId(searchHit.getId());
        return event;
    }

    public Event getEventByEventDataIdLazy(final String id, final String category, final String dataset) throws Exception {
        int currentRetry = 0;

        while(currentRetry < 10 * 2) {
            try {
                Event event = this.getEventByEventDataId(id, category, dataset);
//                this.logger.info("Took " + currentRetry + " retries");
                return event;
            } catch (Exception e) {
                if (!e.getMessage().contains("Unable to find item by id: ")) {
                    throw e;
                }

                currentRetry++;
                Thread.sleep(50L);
            }
        }

        throw new Exception("Unable to find item by id: " + id);
    }
}
