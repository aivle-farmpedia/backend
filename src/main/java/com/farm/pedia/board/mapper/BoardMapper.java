package com.farm.pedia.board.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.farm.pedia.board.domain.Board;
@Mapper
public interface BoardMapper {

	void save(Board board);

	Optional<Board> findById(@Param("id") Long id);

	List<Board> findAll(@Param("limit") int limit, @Param("offset") int offset);

	int countAll();

	void update(Board board);

	void logicalDelete(@Param("id") Long id);
}
