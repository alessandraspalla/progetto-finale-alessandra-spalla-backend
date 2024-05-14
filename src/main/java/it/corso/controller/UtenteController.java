package it.corso.controller;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteLoginResponseDto;
import it.corso.dto.UtenteRegistrazioneDto;
import it.corso.model.Utente;
import it.corso.service.UtenteService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrazioneUtente(@Valid @RequestBody UtenteRegistrazioneDto utenteDto) {
		
		try {
			
			if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", utenteDto.getPassword())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
			if(utenteService.esisteUtente(utenteDto.getEmail())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
			utenteService.registrazioneUtente(utenteDto);
			
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginUtente(@RequestBody UtenteLoginRequestDto utenteLoginRequestDto) {
		try {
			
			if(utenteService.loginUtente(utenteLoginRequestDto)) {
				return Response.ok(issueToken(utenteLoginRequestDto.getEmail())).build();  
			}
			
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();	
		}
	}
	
	private UtenteLoginResponseDto issueToken(String email) {
		
		byte[] secretKey = "wefcheut495tty3498cry4qr908302qr9u3409tyctc3wv5y4".getBytes(); 
		
		Key key = Keys.hmacShaKeyFor(secretKey);
		
		Utente utente = utenteService.cercaConEmail(email);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", utente.getId());
		map.put("nome", utente.getNome());
		map.put("cognome", utente.getCognome());
		map.put("email", email);
		
		Date creationDate = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		
		String jwtToken = Jwts.builder()
			.setClaims(map)
			.setIssuer("http://localhost:8080")
			.setIssuedAt(creationDate)
			.setExpiration(end)
			.signWith(key)
			.compact();
		
		UtenteLoginResponseDto token = new UtenteLoginResponseDto();
		token.setToken(jwtToken);
		token.setTokenCreationTime(creationDate);
		token.setTtl(end);
		
		return token;
	}
}
