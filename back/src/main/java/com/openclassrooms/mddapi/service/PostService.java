package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Post service.
 * @author tipikae
 * @version 1.0.0
 */
@Service
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	/**
	 * Create a post.
	 * @param post Post to create.
	 * @return Post
	 */
	@Override
	public Post create(Post post) {
		post.setCreatedAt(LocalDateTime.now());
		return postRepository.save(post);
	}

	/**
	 * Get a post by id.
	 * @param id Post id.
	 * @return Post
	 * @throws NotFoundException thrown when te post is not found.
	 */
	@Override
	public Post getById(long id) throws NotFoundException {
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new NotFoundException(String.format("Post with id = %d is not found.", id));
		}

		return post;
	}

	/**
	 * Get all posts ordered by created date desc.
	 * @return List
	 */
	@Override
	public List<Post> findAllByCreatedAtDesc() {
		return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	}

	/**
	 * Get all posts.
	 * @param order Sort order.
	 * @return List
	 */
	public List<Post> findAll(String order) {
		Sort.Direction direction = order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		return postRepository.findAll(Sort.by(direction, "createdAt"));
	}
}
