package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import Entities.Employe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("employes")
@Api
public class GestionEmploye {
	
	public static  List<Employe> employes=new ArrayList<Employe>();
	GenericEntity<List<Employe>> entity = new GenericEntity<List<Employe>>(employes) {};

	public GestionEmploye()  {
		Employe E1 = new Employe(12,"foulen","ben foulen") ;  
		Employe E2 = new Employe(12,"mohamed","abbes");
		employes.add(E1);
		employes.add(E2);
	 
	 	
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@ApiOperation(value = "GET all Employes")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public  Response  displayEmployeesList() {
		
		if(employes.size()!=0)
			return Response.status(Status.FOUND).entity(entity).build();
		else
			return Response.status(Status.NOT_FOUND).build();
					
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Post an epmploye")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public String ajouterEmploye(Employe employe) {
		 if(employes.add(employe))
	 return "Add Successful";
		return "Echec";
	}
	
	



	public Response getEmploye(int cin) {
		for (Employe info:employes) {
	       if(info.getCin()==cin) {
	    	   return  Response.status(Status.FOUND)
						.entity(info)
						.build(); 
	    	
	       }
		}
	       		
			return  Response.status(Status.NOT_FOUND).build();
		
		
	}
	
		
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "update an Employe")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public Response updateEmploye(Employe e) {
		int index= this.getIndexByCin(e.getCin());
		if (index!=-1) {
			employes.set(index, e);
			return Response.status(Status.OK).entity("update successful").build();
			
		}
		return Response.status(Status.NOT_FOUND).entity("update unsuccessful").build();
	
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Delete an Employe")
	@ApiResponses({
		@ApiResponse(code=200, message="Success")
	})
	public boolean deleteEmpl(int cin){
		int index= getIndexByCin(cin);
		
		if (index!=-1) {
			employes.remove(index);
			return true;
		}else 
			return false;
			
    }
	

	
	public int getIndexByCin(int cin) {
		for(Employe emp: employes) {
			if (emp.getCin()==cin)
				return employes.indexOf(emp);
		}
		return -1;
	}
	
		
}
