package com.sds.asynboard.board;

import lombok.Data;

@Data
public class Board2 {
	
	private int borard_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	
}
