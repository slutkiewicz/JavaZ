package rest.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import domain.Person;

@Path("people")
@Stateless
public class PersonResource {
	@PersistenceContext
	EntityManager em;
	int quantityPerPage=5;

	//--------------GET PERSON PAGE--------------
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Person> getPage(@QueryParam("page") int page){
			List<Person> result =em.createNamedQuery("person.all", Person.class)
					.setFirstResult(countPageQuantity(--page))
					.setMaxResults(quantityPerPage)
					.getResultList();
			
			return result;
		}
	// --------------ADD PERSON--------------
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response Add(Person person) {
			
			em.persist(person);
			return Response.ok(person.getId()).build();
		}
	// --------------DELETE PERSON--------------
		@DELETE
		@Path("/{id}")
		public Response DeleteP(@PathParam("id") int id) {
			Person result = em.find(Person.class, id);
			if(result==null) 
				return Response.status(404).build();
			em.remove(result);
			return Response.ok().build();
		}
	//------------------EDIT PRODUCT-------------------
		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response update(@PathParam("id") int id,Person person) {
			Person result = em.find(Person.class, id);
			if(result==null )
				return Response.status(404).build();		
			em.persist(updatePerson(result,person));
			return Response.ok().build();		
		}
		//////////////////////////////FUNKCJE POMOCNICZE

		private Person updatePerson(Person origin,Person update) {
			origin.setFirstName(update.getFirstName());
			origin.setLastName(update.getLastName());
			origin.seteMail(update.geteMail());
			origin.setGender(update.getGender());
			origin.setAge(update.getAge());
			origin.setBirthday(update.getBirthday());
			return origin;
		}	
		private int countPageQuantity(int page) {	
			return page*quantityPerPage;
		}
	
	
		
		
}
