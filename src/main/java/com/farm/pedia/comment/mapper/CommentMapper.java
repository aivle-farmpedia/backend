package com.farm.pedia.comment.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.farm.pedia.comment.domain.Comment;
@Mapper
public interface CommentMapper {

	void save(Comment comment);

	Optional<Comment> findById(Long id);

	List<Comment> findAll(int limit, int offset, Long boardId);

	int countAll(Long boardId);

	void update(Comment comment);

	void logicalDelete(Long id);
}
