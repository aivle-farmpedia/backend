package com.farm.pedia.board.exception.exceptions;

public class BoardAlreadyDeletedException extends RuntimeException {

	public BoardAlreadyDeletedException() {
		super("이미 삭제된 게시글입니다.");
	}
}
