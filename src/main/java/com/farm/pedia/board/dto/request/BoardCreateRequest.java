package com.farm.pedia.board.dto.request;

import jakarta.validation.constraints.NotNull;

public record BoardCreateRequest(

	@NotNull(message = "게시글 제목이 비어있습니다.")
	String title,

	@NotNull(message = "게시글 내용이 비어있습니다.")
	String content
) {

}