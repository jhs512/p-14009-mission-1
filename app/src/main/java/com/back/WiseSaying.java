package com.back;

public class WiseSaying {
	int sequence;
	String wiseSaying;
	String writer;

	public WiseSaying(int sequence, String wiseSaying, String writer) {
		this.sequence = sequence;
		this.wiseSaying = wiseSaying;
		this.writer = writer;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getWiseSaying() {
		return wiseSaying;
	}

	public void setWiseSaying(String wiseSaying) {
		this.wiseSaying = wiseSaying;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
}
