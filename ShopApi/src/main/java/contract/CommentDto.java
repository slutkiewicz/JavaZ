package contract;

import java.util.Date;



public class CommentDto {
	private int id;
	private int productId;
	private String title;
	private String content;	
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProduct() {
		return productId;
	}
	public void setProduct(int product) {
		this.productId = product;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
