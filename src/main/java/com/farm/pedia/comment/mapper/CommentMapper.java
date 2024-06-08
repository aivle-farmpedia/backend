package com.farm.pedia.comment.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.farm.pedia.comment.domain.Comment;
import com.github.pagehelper.Page;

@Mapper
public interface CommentMapper {

	void save(Comment comment);

	Optional<Comment> findById(Long id);

	Page<Comment> findAll();

	void update(Comment comment);

	void logicalDelete(Long id);
}
