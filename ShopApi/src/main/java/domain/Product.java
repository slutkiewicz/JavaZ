package domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@NamedQueries({
	
@NamedQuery(name="product.all", query="SELECT p FROM Product p"),
@NamedQuery(name="product.name", query="Select p From Product p where p.name like :productName"),
@NamedQuery(name="product.price",
query="Select p From Product p where"
		+ " p.price >= :productFromPrice and p.price <= :productToPrice"),
@NamedQuery(name="product.category", query="Select p From Product p where p.category like :productCategory")
})

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@NotNull
	private String brand;
	@NotNull
	private String name;
	@NotNull
	private double price;

	@NotNull
	private String category;
	private String description;
	@XmlTransient
	@OneToMany(mappedBy="product",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	private ArrayList<Comment> comments=new ArrayList<Comment>();

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

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	
	
	
	

}
