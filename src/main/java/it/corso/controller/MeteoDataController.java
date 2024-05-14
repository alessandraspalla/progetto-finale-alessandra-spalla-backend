package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import it.corso.dto.DatiMeteoCreazioneDto;
import it.corso.jwt.JWTTokenNeeded;
import it.corso.jwt.Secured;
import it.corso.service.MeteoDataService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Secured
@JWTTokenNeeded
@Path("/meteo")
public class MeteoDataController {

	@Autowired
	private MeteoDataService meteoDataService;
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrazioneMeteo(@RequestBody DatiMeteoCreazioneDto datiMeteoCreazioneDto) {
		
		try {
			
			meteoDataService.registrazioneMeteo(datiMeteoCreazioneDto);
			
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
