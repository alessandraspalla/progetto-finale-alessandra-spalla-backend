package it.corso.jwt;

import java.io.IOException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@JWTTokenNeeded
@Provider
public class JWTTokenNeededFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		try {
			Secured annotationRole = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
			
			if(annotationRole == null) {
				
				annotationRole = resourceInfo.getResourceClass().getAnnotation(Secured.class);
			}
			
			String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			
			if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
				
				throw new NotAuthorizedException("Authorization Header Must Be Provied");
			}
			
			authorizationHeader.substring("Bearer".length()).trim();
			
		} catch (Exception e) {
			
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}
