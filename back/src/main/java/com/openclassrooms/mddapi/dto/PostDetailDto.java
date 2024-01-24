package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostDetailDto extends PostDto {

    private List<CommentDto> comments;

    public PostDetailDto(PostDto postDto, List<CommentDto> commentDtos) {
        super(postDto.getId(), postDto.getTitle(), postDto.getContent(), postDto.getAuthorId(),
                postDto.getAuthorUsername(), postDto.getTopicId(), postDto.getTopicName(), postDto.getCreatedAt());
        this.comments = commentDtos;
    }
}
