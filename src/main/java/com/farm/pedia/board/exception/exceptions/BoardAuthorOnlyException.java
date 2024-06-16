package com.farm.pedia.board.exception.exceptions;

public class BoardAuthorOnlyException extends RuntimeException{

	public BoardAuthorOnlyException() {
		super("작성자만 수정, 삭제가 가능합니다.");
	}
}
