package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	
@NamedQuery(name="comment.all", query="SELECT c FROM Comment c"),
//@NamedQuery(name="comment.insert", query="INSERT into Comment c values"),

//@NamedQuery(name="comment.allId", query="SELECT c FROM Comment c where c.product.id=productId"),
@NamedQuery(name="comment.id", query="Select c From Comment c where c.id=:commentId")
})

public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String title;
	@NotNull
	private String content;	
	private Date date;
	@ManyToOne
	private Product product;


	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getProduct() {
		return product.getId();
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	//////////////////////////Obejscie do dodanie id przez Em
	public Product getProductProduct() {
		return product;
	}

	
	
	

}
