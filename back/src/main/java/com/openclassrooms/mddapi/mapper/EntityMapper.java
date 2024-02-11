package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.exception.NotFoundException;

import java.util.List;

/**
 * Entity Mapper.
 * @param <D> DTO
 * @param <E> Entity
 */
public interface EntityMapper<D, E> {

    /**
     * Convert a DTO to an entity.
     * @param dto DTO
     * @return E
     * @throws NotFoundException thrown when entity is not found.
     */
    E toEntity(D dto) throws NotFoundException;

    /**
     * Convert an entity to a DTO.
     * @param entity Entity
     * @return D
     */
    D toDto(E entity);

    /**
     * Convert a DTOs list to an entities list.
     * @param dtos DTOs list.
     * @return List
     */
    List<E> toEntities(List<D> dtos);

    /**
     * Convert an entities list to a DTOs list.
     * @param entities Entities list.
     * @return List
     */
    List<D> toDtos(List<E> entities);
}
