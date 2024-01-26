package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Post detail DTO.
 * @author tipikae
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostDetailDto extends PostDto {

    private List<CommentDto> comments;

    /**
     * Constructor.
     * @param postDto Post details.
     * @param commentDtos Comments post.
     */
    public PostDetailDto(PostDto postDto, List<CommentDto> commentDtos) {
        super(postDto.getId(), postDto.getTitle(), postDto.getContent(), postDto.getAuthorId(),
                postDto.getAuthorUsername(), postDto.getTopicId(), postDto.getTopicName(), postDto.getCreatedAt());
        this.comments = commentDtos;
    }
}
