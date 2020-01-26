package com.techdevsolutions.common.service.core;

import com.techdevsolutions.common.dao.elasticsearch.BaseElasticsearchHighLevel;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchModule;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElasticsearchUtils {
    public static String backup(String host, String port, String index, String query, String fullPath) throws Exception {
        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException("host is null or empty");
        } else if (StringUtils.isEmpty(port)) {
            throw new IllegalArgumentException("port is null or empty");
        } else if (StringUtils.isEmpty(index)) {
            throw new IllegalArgumentException("index is null or empty");
        } else if (StringUtils.isEmpty(query)) {
            throw new IllegalArgumentException("query is null or empty");
        } else if (StringUtils.isEmpty(fullPath)) {
            throw new IllegalArgumentException("fullPath is null or empty");
        }

        BaseElasticsearchHighLevel baseElasticsearchHighLevel = new BaseElasticsearchHighLevel();
        baseElasticsearchHighLevel.getClient(host, port);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchModule searchModule = new SearchModule(Settings.EMPTY, false, Collections.emptyList());

        try {
            NamedXContentRegistry namedXContentRegistry = new NamedXContentRegistry(searchModule.getNamedXContents());
            XContent xContent = XContentFactory.xContent(XContentType.JSON);
            XContentParser parser = xContent.createParser(namedXContentRegistry,
                    DeprecationHandler.THROW_UNSUPPORTED_OPERATION, query);
            searchSourceBuilder.parseXContent(parser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        List<SearchHit> results = baseElasticsearchHighLevel.getDocumentsWithScroll(searchRequest);
        return StringUtils.join(results, "\n");
    }

    public static void bulkIngestFromString(String host, String index, String data) throws IOException {
        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException("host is null or empty");
        } else if (StringUtils.isEmpty(index)) {
            throw new IllegalArgumentException("index is null or empty");
        } else if (StringUtils.isEmpty(data)) {
            throw new IllegalArgumentException("data is null or empty");
        }

        String[] array = data.split("\n");
        List<String> list = new ArrayList<>(Arrays.asList(array));

        BaseElasticsearchHighLevel baseElasticsearchHighLevel = new BaseElasticsearchHighLevel();

        list.stream().forEach((i)->{
            IndexRequest indexRequest = new IndexRequest(index).source(i, XContentType.JSON);
            baseElasticsearchHighLevel.getBulkProcessor().add(indexRequest);
        });

    }

    public static void bulkIngestFromFile(String host, String index, String fullPath) throws IOException {
        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException("host is null or empty");
        } else if (StringUtils.isEmpty(index)) {
            throw new IllegalArgumentException("index is null or empty");
        } else if (StringUtils.isEmpty(fullPath)) {
            throw new IllegalArgumentException("fullPath is null or empty");
        }

        File file = new File(fullPath);
        String data = org.apache.commons.io.FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
        String[] array = data.split("\n");
        List<String> list = new ArrayList<>(Arrays.asList(array));

        BaseElasticsearchHighLevel baseElasticsearchHighLevel = new BaseElasticsearchHighLevel();

        list.stream().forEach((i)->{
            IndexRequest indexRequest = new IndexRequest(index).type("_doc").source(i, XContentType.JSON);
            baseElasticsearchHighLevel.getBulkProcessor().add(indexRequest);
        });

    }
}
