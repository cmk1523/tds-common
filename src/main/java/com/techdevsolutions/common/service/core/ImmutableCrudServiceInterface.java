package com.techdevsolutions.common.service.core;

import com.techdevsolutions.common.beans.Filter;
import com.techdevsolutions.common.beans.Search;

import java.util.List;

public interface ImmutableCrudServiceInterface<T> {
    List<T> search(Search search) throws Exception;
    List<T> getAll() throws Exception;
    T get(String id) throws Exception;
    T create(T item) throws Exception;
    void install() throws Exception;
}
