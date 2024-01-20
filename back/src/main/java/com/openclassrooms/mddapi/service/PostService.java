package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post create(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post getById(long id) throws NotFoundException {
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new NotFoundException(String.format("Post with id = %d is not found.", id));
		}

		return post;
	}

	@Override
	public List<Post> findAllByCreatedAtDesc() {
		return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	}
}
