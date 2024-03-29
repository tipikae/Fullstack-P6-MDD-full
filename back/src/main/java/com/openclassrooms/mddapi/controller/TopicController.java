package com.openclassrooms.mddapi.controller;

import java.security.Principal;
import java.util.List;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.response.ErrorResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.ITopicService;

/**
 * Topic controller.
 * @author tipikae
 * @version  1.0.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/topic")
@Validated
public class TopicController {

	@Autowired
	private ITopicService topicService;

	@Autowired
	private IUserService userService;

	/**
	 * Get all topics.
	 * @return List
	 */
	@Operation(summary = "Get all topics")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Returns all topics",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }
			),
			@ApiResponse(
					responseCode = "401",
					description = "Unauthorized",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
			)
	})
	@GetMapping
	public List<Topic> getTopics() {
		return topicService.getTopics();
	}

	/**
	 * Subscribe to a topic endpoint.
	 * @param topicId Topic id.
	 * @param principal Current user.
	 * @return ResponseEntity
	 * @throws NotFoundException thrown when current user or topic is not found.
	 * @throws BadRequestException thrown when an error occurred during authentication.
	 */
	@Operation(summary = "Subscribe a user to a topic")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Subscription succeeded",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }
			),
			@ApiResponse(
					responseCode = "400",
					description = "Path variable not valid",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
			),
			@ApiResponse(
					responseCode = "401",
					description = "Unauthorized",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
			),
			@ApiResponse(
					responseCode = "404",
					description = "User or topic not found",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
			)
	})
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

	/**
	 * Unsubscribe to a topic endpoint.
	 * @param topicId Topic id.
	 * @param principal Current user.
	 * @return ResponseEntity
	 * @throws NotFoundException thrown when current user or topic is not found.
	 * @throws BadRequestException thrown when an error occurred during authentication.
	 */
	@Operation(summary = "Unsubscribe a user to a topic")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Unsubscription succeeded",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }
			),
			@ApiResponse(
					responseCode = "400",
					description = "Path variable not valid",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
			),
			@ApiResponse(
					responseCode = "401",
					description = "Unauthorized",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
			),
			@ApiResponse(
					responseCode = "404",
					description = "User or topic not found",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }
			)
	})
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
