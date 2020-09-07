package com.softserve.marathon.mapper;

public interface MapperToEntity<D, E> {
    E convertToEntity(D dto);
}
