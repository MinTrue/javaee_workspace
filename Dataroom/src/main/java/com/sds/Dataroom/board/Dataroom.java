package com.sds.Dataroom.board;

import lombok.Data;

@Data
//DTO 생성
public class Dataroom {
	private int dataroom_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private String filename;
}
