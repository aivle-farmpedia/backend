package com.farm.pedia.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farm.pedia.auth.config.UserLogin;
import com.farm.pedia.comment.domain.Comment;
import com.farm.pedia.comment.dto.request.CommentCreateRequest;
import com.farm.pedia.comment.dto.request.CommentUpdateRequest;
import com.farm.pedia.comment.dto.response.CommentReadResponse;
import com.farm.pedia.comment.dto.response.CommentWriteResponse;
import com.farm.pedia.comment.dto.response.CommentsResponse;
import com.farm.pedia.comment.service.CommentService;
import com.farm.pedia.user.domain.User;
import com.github.pagehelper.Page;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/board/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public CommentsResponse findAll(
		@PathVariable Long boardId,
		@RequestParam(defaultValue = "1") int pageNum,
		@RequestParam(defaultValue = "10") int pageSize) {
		Page<Comment> comments = commentService.findAll(boardId, pageNum, pageSize);
		return CommentsResponse.from(comments);
	}

	@PostMapping("/board/{boardId}")
	@ResponseStatus(HttpStatus.CREATED)
	public CommentWriteResponse createComment(
		@UserLogin User user,
		@PathVariable Long boardId,
		@RequestBody CommentCreateRequest commentCreateRequest) {
		Comment comment = commentService.createComment(user, boardId, commentCreateRequest);
		return CommentWriteResponse.from(comment);
	}

	@PostMapping("/reply/{boardId}")
	@ResponseStatus(HttpStatus.CREATED)
	public CommentWriteResponse createReply(
		@UserLogin User user,
		@PathVariable Long boardId,
		@RequestBody CommentCreateRequest commentCreateRequest) {
		Comment comment = commentService.createReply(user, boardId, commentCreateRequest);
		return CommentWriteResponse.from(comment);
	}

	@GetMapping("/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public CommentReadResponse findComment(
		@PathVariable Long commentId) {
		Comment comment = commentService.findComment(commentId);
		return CommentReadResponse.from(comment);
	}

	@PutMapping("/{commentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateComment(
		@UserLogin User user,
		@PathVariable Long commentId,
		@RequestBody CommentUpdateRequest commentUpdateRequest) {
		commentService.updateComment(user, commentId, commentUpdateRequest);
	}

	@DeleteMapping("/{commentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteComment(
		@UserLogin User user,
		@PathVariable Long commentId) {
		commentService.deleteComment(user, commentId);
	}
}