package pt.adrz.gymlogger.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import pt.adrz.gymlogger.model.Credentials;

@Path("authentication")
public class AuthenticationService {
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response authenticateUser(Credentials credentials) {
		
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		try {

			authenticate(username, password);
			String token = issueToken(username);
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	private void authenticate(String username, String password) throws Exception {
		// Authenticate against a database, LDAP, file or whatever
		// Throw an Exception if the credentials are invalid
	}

	private String issueToken(String username) {
		// Issue a token (can be a random String persisted to a database or a
		// JWT token)
		// The issued token must be associated to a user
		// Return the issued token
		return null;
	}
}
