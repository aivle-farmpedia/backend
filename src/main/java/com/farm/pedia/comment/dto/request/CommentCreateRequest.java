package com.farm.pedia.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentCreateRequest {

	@NotNull(message = "댓글은 비어있을 수 없습니다.")
	private String content;

	private Long parentId;
}
