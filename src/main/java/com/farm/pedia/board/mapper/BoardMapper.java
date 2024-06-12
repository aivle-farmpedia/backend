package com.farm.pedia.board.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.farm.pedia.board.domain.Board;
import com.github.pagehelper.Page;

@Mapper
public interface BoardMapper {

	void save(Board board);

	Optional<Board> findById(@Param("id") Long id);

	Page<Board> findAll();

	void update(Board board);

	void logicalDelete(@Param("id") Long id);
}
