package org.example;

public class GoodWordEntry {
	private int id;
	private String writer;
	private String goodword;

	GoodWordEntry(int id, String writer, String goodword) {
		this.id = id;
		this.writer = writer;
		this.goodword = goodword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getGoodword() {
		return goodword;
	}

	public void setGoodword(String goodword) {
		this.goodword = goodword;
	}

	public String toJson() {
		String str = "{\n\t\"id\": %s,\n\t\"content\": \"%s\",\n\t\"author\": \"%s\"\n}";

		return String.format(str, this.id, this.goodword, this.writer);
	}

}
