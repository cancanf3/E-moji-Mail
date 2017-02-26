import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;



@Path("/e-moji/")
public class Server {



	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	static public Response analyze ( Body b) {

		String text = b.body;
		String result = new String("testing");

		// Watson Analyze

		
		return Response.status(201).entity(result).build();

	}



}


class Body {
	public String body;
}