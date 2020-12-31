package com.techdevsolutions.common.service.generic;

import com.techdevsolutions.common.beans.Search;
import com.techdevsolutions.common.dao.DaoCrudInterface;
import com.techdevsolutions.common.dao.memory.InMemoryGenericDaoImpl;
import com.techdevsolutions.common.service.core.Timer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericServiceImpl implements GenericService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected DaoCrudInterface<Map> dao;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public GenericServiceImpl() {
    }

    public GenericServiceImpl(InMemoryGenericDaoImpl dao) {
        this.dao = dao;
    }

    @Override
    public List<Map> search(Search search) throws Exception {
        return this.dao.search(search);
    }

    @Override
    public List<Map> getAll(Search search) throws Exception {
        return this.dao.search(search);
    }

    @Override
    public Map<String, Object> get(String id) throws Exception {
        Timer timer = new Timer().start();

        if (StringUtils.isEmpty(id)) {
            throw new Exception("id is null or empty");
        }

        Map<String, Object> item = this.dao.get(id);
        this.logger.info("Got item by ID: " + id + " in " + timer.stopAndGetDiff() + " ms");
        return item;
    }

    @Override
    public Map create(Map item) throws Exception {
        Timer timer = new Timer().start();

        if (item == null) {
            throw new Exception("item is null");
        } else if (item.get("id") == null) {
            throw new Exception("item ID is null");
        } else {
//            if (item.getCreated() == null) {
//                item.setCreated(new Date().getTime());
//            }
        }

        Set<ConstraintViolation<Map<String, Object>>> violations = this.validator.validate(item);

        if (violations.size() > 0) {
            if (violations.size() != 1 && !violations.toString().contains("must not be blank', propertyPath=id")) {
                throw new Exception("Invalid item: " + violations.toString());
            }
        }

        Map<String, Object> created = this.dao.create(item);
        this.logger.info("Created item by ID: " + item.get("id") + " in " + timer.stopAndGetDiff() + " ms");
        return created;
    }

    @Override
    public void remove(String id) throws Exception {
        Timer timer = new Timer().start();

        if (StringUtils.isEmpty(id)) {
            throw new Exception("id is null or empty");
        }

        this.dao.remove(id);
        this.logger.info("Removed item by ID: " + id + " in " + timer.stopAndGetDiff() + " ms");
    }

    @Override
    public void delete(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new Exception("id is null or empty");
        }

        this.dao.delete(id);
    }

    @Override
    public Map update(Map item) throws Exception {
        Timer timer = new Timer().start();

        if (item == null) {
            throw new Exception("item is null");
        } else if (item.get("id") == null) {
            throw new Exception("item ID is null");
        }

        Set<ConstraintViolation<Map<String, Object>>> violations = this.validator.validate(item);

        if (violations.size() > 0) {
            throw new Exception("Invalid item: " + violations.toString());
        }

        Map<String, Object> updated = this.dao.update(item);
        this.logger.info("Updated item by ID: " + item.get("id") + " in " + timer.stopAndGetDiff() + " ms");
        return updated;
    }

    @Override
    public void install() throws Exception {
        this.dao.install();
    }
}
