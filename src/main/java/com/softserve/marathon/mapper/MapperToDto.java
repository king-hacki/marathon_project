package com.softserve.marathon.mapper;

public interface MapperToDto<E, D> {
    D convertToDto(E entity);
}
