package contract;

import java.util.ArrayList;



public class ProductDto {
	private int id;
	private String brand;
	private String name;
	private double price;
	private String category;
	private String description;
	private ArrayList<CommentDto> comments=new ArrayList<CommentDto>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<CommentDto> getComments() {
		return comments;
	}
	public void setComments(ArrayList<CommentDto> comments) {
		this.comments = comments;
	}
	
	
}
