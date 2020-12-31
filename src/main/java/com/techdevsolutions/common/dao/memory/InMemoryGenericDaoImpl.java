package com.techdevsolutions.common.dao.memory;

import com.techdevsolutions.common.beans.Search;
import com.techdevsolutions.common.dao.DaoCrudInterface;
import com.techdevsolutions.common.service.core.Timer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class InMemoryGenericDaoImpl implements DaoCrudInterface<Map> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<String, Map> data = new HashMap<>();

    @Override
    public List<Map> search(Search search) throws Exception {
        return new ArrayList<>(this.data.values());
    }

    @Override
    public Map get(String id) throws Exception {
        Timer timer = new Timer().start();

        if (StringUtils.isEmpty(id)) {
            throw new Exception("Item ID is null or empty");
        }

        Optional<String> optional = this.data.keySet().parallelStream().filter((i) -> i.equals(id)).findFirst();

        this.logger.info("Get item by ID: " + id + " in " + timer.stopAndGetDiff() + " ms");

        if (optional.isEmpty()) {
            return null;
        } else {
            return this.data.get(id);
        }
    }

    @Override
    public Map create(Map item) throws Exception {
        Timer timer = new Timer().start();

        if (item == null) {
            throw new Exception("Item is null");
        } else if (item.get("id") == null) {
            throw new Exception("Item ID is null");
        }

        this.data.put((String) item.get("id"), item);

        this.logger.info("Created item by ID: " + item.get("id") + " in " + timer.stopAndGetDiff() + " ms");
        return item;
    }

    @Override
    public void remove(String id) throws Exception {
        this.delete(id);
    }

    @Override
    public void delete(String id) throws Exception {
        Timer timer = new Timer().start();

        if (StringUtils.isEmpty(id)) {
            throw new Exception("Item ID is null");
        }

        this.data.remove(id);
        this.logger.info("Removed item by ID: " + id + " in " + timer.stopAndGetDiff() + " ms");
    }

    @Override
    public Map update(Map item) throws Exception {
        Timer timer = new Timer().start();

        if (item == null) {
            throw new Exception("Item is null");
        } else if (item.get("id") == null) {
            throw new Exception("Item ID is null");
        }

        Map found = this.get((String) item.get("id"));

        if (found != null) {
            this.data.put((String) item.get("id"), item);
            this.logger.info("Updated item by ID: " + item.get("id") + " in " + timer.stopAndGetDiff() + " ms");
            return item;
        } else {
            throw new Exception("Unable to find item by ID: " + item.get("id"));
        }
    }

    @Override
    public Boolean verifyRemoval(String id) throws Exception {
        return null;
    }

    @Override
    public void install() throws Exception {

    }
}
