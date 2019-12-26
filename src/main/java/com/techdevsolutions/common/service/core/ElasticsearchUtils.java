package com.techdevsolutions.common.service.core;

import com.techdevsolutions.common.dao.elasticsearch.BaseElasticsearchHighLevel;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchModule;
import org.elasticsearch.search.builder.SearchSourceBuilder;

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
}
