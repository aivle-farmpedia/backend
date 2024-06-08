package com.farm.pedia.comment.service;

import com.farm.pedia.board.domain.Board;
import com.farm.pedia.board.mapper.BoardMapper;
import com.farm.pedia.comment.domain.Comment;
import com.farm.pedia.comment.dto.request.CommentCreateRequest;
import com.farm.pedia.comment.dto.request.CommentUpdateRequest;
import com.farm.pedia.comment.mapper.CommentMapper;
import com.farm.pedia.user.domain.User;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final BoardMapper boardMapper;
	private final CommentMapper commentMapper;

	public Page<Comment> findAll(Long boardId, int pageNum, int pageSize) {
		findBoard(boardId);
		PageHelper.startPage(pageNum, pageSize);
		return commentMapper.findAll();
	}

	public Comment findComment(Long commentId) {
		Comment comment = commentMapper.findById(commentId)
			.orElseThrow(() -> new RuntimeException("Comment not found"));
		comment.isDeleted();
		return comment;
	}

	@Transactional
	public Comment createComment(User user, Long boardId, CommentCreateRequest request) {
		findBoard(boardId);
		Comment comment = Comment.parent(boardId, user.getId(), request.getContent());
		commentMapper.save(comment);
		return comment;
	}

	@Transactional
	public Comment createReply(User user, Long boardId, CommentCreateRequest request) {
		findBoard(boardId);
		Comment parentComment = findComment(request.getParentId());
		Comment comment = Comment.child(boardId, user.getId(), request.getContent(), parentComment.getId());
		commentMapper.save(comment);
		return comment;
	}

	@Transactional
	public void updateComment(User user, Long commentId, CommentUpdateRequest request) {
		Comment comment = findComment(commentId);
		comment.isAuthor(user.getId());
		comment.update(request.getContent());
		commentMapper.update(comment);
	}

	@Transactional
	public void deleteComment(User user, Long commentId) {
		Comment comment = findComment(commentId);
		comment.isAuthor(user.getId());
		commentMapper.logicalDelete(comment.getId());
	}

	public void findBoard(Long boardId) {
		Board board = boardMapper.findById(boardId)
			.orElseThrow(() -> new RuntimeException("Board not found"));
		board.isDeleted();
	}
}