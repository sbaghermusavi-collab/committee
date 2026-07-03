package com.nicico.committee.mapper;

public interface DefaultMapper<D, U, C, E> {
    D toDto(E entity);
    E toEntityForCreate(C createDto);
    E toEntityForUpdate(U updateDto);
}
