package rest;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import contract.CommentDto;
import contract.ProductDto;
import domain.Comment;
import domain.Product;
import domain.Category;

	@Path("/product")
	@Stateless
	public class ProductResources{
		Mapper mapper = new DozerBeanMapper();
	@PersistenceContext
	EntityManager em;

	//--------------GET ALL PRODUCTS--------------
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDto> getAll(){
		List<Product> result =em.createNamedQuery("product.all", Product.class).getResultList();

		return convertProductList(result);
	}
	//--------------SELECT BY ID--------------
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Product result = em.find(Product.class, id);
		if(result==null) 
			return Response.status(404).build();
		
		return Response.ok(convertProductEntry(result)).build();
		}
	//--------------SELECT BY NAME--------------
	@GET
	@Path("{productName}/name")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDto> getName(@PathParam("productName") String name) {
		List<Product> result = em.createNamedQuery("product.name",Product.class)
				.setParameter("productName", name)
				.getResultList();
		if(result==null) 
			return null;
		
		return convertProductList(result);
		}
	//--------------SELECT BY CATTEGORY--------------
	@GET
	@Path("{categoryName}/category")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDto> getCategory(@PathParam("categoryName") String name) {
		List<Product> result = em.createNamedQuery("product.category",Product.class)
				.setParameter("productCategory", name)
				.getResultList();
		if(result==null) 
			return null;

		return convertProductList(result);
		}
	//--------------SELECT BY PRICE--------------
	@GET
	@Path("{from},{to}/price")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDto> getPrice(@PathParam("from")  double from , @PathParam("to") double to) {
		List<Product> result = em.createNamedQuery("product.price",Product.class)
				.setParameter("productFromPrice", from)
				.setParameter("productToPrice", to)
				.getResultList();
		if(result==null) 
			return null;
	
		return convertProductList(result);
		}

	// --------------ADD PRODUCT--------------
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Product product) {
		if(!new Category().checkCategories(product.getCategory())) 
			return Response.status(412).build();
			
		em.persist(product);
		return Response.ok(product.getId()).build();
		
			
	}

	// --------------ADD COMMENT--------------
	@POST
	@Path("/{productId}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(@PathParam("productId") int id,Comment comment) {
		Product result = em.find(Product.class, id);
		if(result==null) 
			return Response.status(404).build();
		comment.setProduct(result);
		result.getComments().add(comment);
		em.persist(comment);

		return Response.ok().build();
	}
	// --------------DELETE PRODUCT--------------
		@DELETE
		@Path("/{id}")
		public Response DeleteP(@PathParam("id") int id) {
			Product result = em.find(Product.class, id);
			if(result==null) 
				return Response.status(404).build();
			em.remove(result);

			return Response.ok().build();
		}
	// --------------DELETE COMMENT--------------
	@DELETE
	@Path("/{commentId}/comments")
	public Response DeleteC(@PathParam("commentId") int id) {
		Comment result = em.find(Comment.class, id);
		if(result==null) 
			return Response.status(404).build();
		result.getProductProduct().getComments().remove(result);
		em.remove(result);
		return Response.ok().build();
	}
	// --------------List ALL COMMENTS--------------
	@GET
	@Path("/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CommentDto> getAllComments() {
		List<Comment> result = em.createNamedQuery("comment.all",Comment.class).getResultList();
		
		return convertCommentList(result);
	}
	// --------------List product COMMENT--------------
	@GET
	@Path("/{productId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CommentDto> getComments(@PathParam("productId") int id) {
		Product result = em.find(Product.class, id);
		if(result==null) 
			return null;
		
		return convertProductEntry(result).getComments();
	}
	//------------------EDIT PRODUCT-------------------
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id,Product product) {
		Product result = em.find(Product.class, id);
		if(result==null )
			return Response.status(404).build();
		if(!new Category().checkCategories(product.getCategory()))
			return Response.status(412).build();
		em.persist(updateProduct(result,product));
		return Response.ok().build();		
	}
	//////////////////////////////FUNKCJE POMOCNICZE

	private Product updateProduct(Product origin,Product update) {
		origin.setName(update.getName());
		origin.setBrand(update.getBrand());
		origin.setCategory(update.getCategory());
		origin.setPrice(update.getPrice());
		origin.setDescription(update.getDescription());
		return origin;
	}
	
	private List<ProductDto> convertProductList(List<Product> list){
		List<ProductDto> response=new ArrayList<ProductDto>();
		for(Product p : list ) {
			response.add(mapper.map(p, ProductDto.class));
		}
		return response;
	}
	private List<CommentDto> convertCommentList(List<Comment> list){
		List<CommentDto> response=new ArrayList<CommentDto>();
		for(Comment c : list ) {
			response.add(mapper.map(c, CommentDto.class));
		}
		return response;
	}
	private ProductDto convertProductEntry(Product entry){
		ProductDto response=mapper.map(entry, ProductDto.class);
		return response;
	}

	
}