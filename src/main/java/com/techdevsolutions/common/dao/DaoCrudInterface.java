package com.techdevsolutions.common.dao;

import com.techdevsolutions.common.beans.Search;

import java.util.List;

public interface DaoCrudInterface<T> {
    List<T> search(Search search) throws Exception;

    T get(String id) throws Exception;

    T create(T item) throws Exception;

    void remove(String id) throws Exception;

    void delete(String id) throws Exception;

    T update(T item) throws Exception;

    Boolean verifyRemoval(final String id) throws Exception;

    void install() throws Exception;
}
