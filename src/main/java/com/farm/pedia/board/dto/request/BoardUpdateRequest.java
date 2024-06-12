package com.farm.pedia.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardUpdateRequest(

	@NotBlank(message = "게시글 제목이 비어있습니다.")
	String title,

	@NotBlank(message = "게시글 내용이 비어있습니다.")
	String content,

	@NotNull(message = "카테고리 ID가 비어있습니다.")
	Long categoryId
) {

}