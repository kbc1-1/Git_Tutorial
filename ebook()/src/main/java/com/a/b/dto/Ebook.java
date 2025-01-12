package com.a.b.dto;

public class Ebook {
	
	private long bBookno;
	private String bBookname;
	private String bContent;
	private long bPrice;
	private String bWriter;
	private String bPublisher;
	private String bCategory;
	
	public Ebook() {}

	public Ebook(long bBookno, String bBookname, String bContent, 
			long bPrice, String bWriter, String bPublisher,
				String bCategory) {
		this.bBookno = bBookno;
		this.bBookname = bBookname;
		this.bContent = bContent;
		this.bPrice = bPrice;
		this.bWriter = bWriter;
		this.bPublisher = bPublisher;
		this.bCategory = bCategory;
	}

	public long getbBookno() {
		return bBookno;
	}

	public void setbBookno(long bBookno) {
		this.bBookno = bBookno;
	}

	public String getbBookname() {
		return bBookname;
	}

	public void setbBookname(String bBookname) {
		this.bBookname = bBookname;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public long getbPrice() {
		return bPrice;
	}

	public void setbPrice(long bPrice) {
		this.bPrice = bPrice;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public String getbPublisher() {
		return bPublisher;
	}

	public void setbPublisher(String bPublisher) {
		this.bPublisher = bPublisher;
	}

	public String getbCategory() {
		return bCategory;
	}

	public void setbCategory(String bCategory) {
		this.bCategory = bCategory;
	}

}
