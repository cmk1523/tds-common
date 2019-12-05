package com.techdevsolutions.common.dao;

import com.techdevsolutions.common.beans.elasticsearchCommonSchema.Event;
import com.techdevsolutions.common.dao.elasticsearch.BaseElasticsearchHighLevel;
import com.techdevsolutions.common.dao.elasticsearch.events.EventElasticsearchDAO;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.get.GetResponse;
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
import java.util.Objects;
import java.util.stream.Collectors;

public class EventDao {
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
    protected EventElasticsearchDAO dao;

    public EventDao(EventElasticsearchDAO dao) {
        this.dao = dao;
    }

    public Event getByEventId(final String documentId, final String index) throws Exception {
        if (StringUtils.isEmpty(documentId)) {
            throw new IllegalArgumentException("documentId is null or empty");
        }

        GetResponse getResponse = this.dao.getDocument(documentId, index);
        return this.dao.rowMapper.fromJson(getResponse.getSourceAsString());
    }

    public List<Event> getEventsByEventDataId(final String id, final String category, final String dataset) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("id is null or empty");
        }

        BaseElasticsearchHighLevel baseElasticsearchHighLevel = new BaseElasticsearchHighLevel();
        baseElasticsearchHighLevel.getClient(this.dao.getHost());

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

        SearchRequest searchRequest = new SearchRequest(this.dao.getIndex());
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        List<SearchHit> results = this.dao.getDocumentsWithScroll(searchRequest);
        return results.stream().map((i) -> {
            try {
                Event event = this.dao.rowMapper.fromJson(i.getSourceAsString());
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
        baseElasticsearchHighLevel.getClient(this.dao.getHost());

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

        SearchRequest searchRequest = new SearchRequest(this.dao.getIndex());
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        List<SearchHit> results = this.dao.getDocumentsWithScroll(searchRequest);

        if (results.size() == 0) {
            throw new Exception("Unable to find item by id: " + id);
        }

        SearchHit searchHit = results.get(0);
        Event event = this.dao.rowMapper.fromJson(searchHit.getSourceAsString());
        event.setId(searchHit.getId());
        return event;
    }

    public Event getEventByEventDataIdLazy(final String id, final String category, final String dataset) throws Exception {
        int currentRetry = 0;

        while(currentRetry < 10) {
            try {
                Event event = this.getEventByEventDataId(id, category, dataset);
//                this.logger.info("Took " + currentRetry + " retries");
                return event;
            } catch (Exception e) {
                if (!e.getMessage().contains("Unable to find item by id: ")) {
                    throw e;
                }

                currentRetry++;
                Thread.sleep(100L);
            }
        }

        throw new Exception("Unable to find item by id: " + id);
    }
}