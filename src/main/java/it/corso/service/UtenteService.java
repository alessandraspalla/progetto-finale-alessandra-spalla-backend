package it.corso.service;

import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteRegistrazioneDto;
import it.corso.model.Utente;

public interface UtenteService {

	boolean esisteUtente(String email);
	void registrazioneUtente(UtenteRegistrazioneDto utenteDto);
	boolean loginUtente(UtenteLoginRequestDto utenteLoginRequestDto);
	Utente cercaConEmail(String email);
}
