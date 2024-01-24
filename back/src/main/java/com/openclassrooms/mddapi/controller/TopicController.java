package com.openclassrooms.mddapi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.IUserService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.ITopicService;

@RestController
@RequestMapping("/api/topic")
@Validated
public class TopicController {

	@Autowired
	private ITopicService topicService;

	@Autowired
	private IUserService userService;

	@GetMapping
	public List<Topic> getTopics() {
		return topicService.getTopics();
	}
	
	@PostMapping("/{topicId}/subscribe")
	public ResponseEntity<MessageResponse> subscribe(
			@PathVariable("topicId") @NotNull @Positive Long topicId,
			Principal principal) throws NotFoundException, BadRequestException {

		User user = userService.getByEmail(principal.getName());
		if (user == null) {
			throw new BadRequestException("Illegal operation.");
		}

		userService.subscribe(user.getId(), topicId);

		return ResponseEntity.ok(new MessageResponse("Topic subscribed successfully !"));
	}

	@DeleteMapping("/{topicId}/subscribe")
	public ResponseEntity<MessageResponse> unsubscribe(
			@PathVariable("topicId") @NotNull @Positive Long topicId,
			Principal principal) throws NotFoundException, BadRequestException {

		User user = userService.getByEmail(principal.getName());
		if (user == null) {
			throw new BadRequestException("Illegal operation.");
		}

		userService.unsubscribe(user.getId(), topicId);

		return ResponseEntity.ok(new MessageResponse("Topic unsubscribed successfully !"));
	}
}
