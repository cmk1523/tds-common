package com.techdevsolutions.common.dao;

import com.techdevsolutions.common.beans.Search;

import java.util.List;

public interface DaoImmutableCrudInterface<T> {
    List<T> search(Search search) throws Exception;

    T get(String id) throws Exception;

    T create(T item) throws Exception;

    void install() throws Exception;
}
