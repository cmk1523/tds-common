package com.techdevsolutions.common.service.core;

import java.util.List;

public interface CrudServiceInterface<T> {
    List<T> search() throws Exception;
    List<T> getAll() throws Exception;
    T get(String id) throws Exception;
    T create(T item) throws Exception;
    void remove(String id) throws Exception;
    void delete(String id) throws Exception;
    T update(T item) throws Exception;
    void install() throws Exception;
}