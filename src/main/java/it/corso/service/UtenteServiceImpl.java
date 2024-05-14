package it.corso.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.UtenteDao;
import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteRegistrazioneDto;
import it.corso.model.Utente;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDao utenteDao;
	
	@Override
	public void registrazioneUtente(UtenteRegistrazioneDto utenteDto) {

		Utente utente = new Utente();
		
		utente.setNome(utenteDto.getNome());
		utente.setCognome(utenteDto.getCognome());
		utente.setEmail(utenteDto.getEmail());
		
		String sha256hex = DigestUtils.sha256Hex(utenteDto.getPassword());
		utente.setPassword(sha256hex);
		
		utenteDao.save(utente);
	}

	@Override
	public boolean esisteUtente(String email) {
		
		return utenteDao.existsByEmail(email);
	}

	@Override
	public boolean loginUtente(UtenteLoginRequestDto utenteLoginRequestDto) {
		// TODO Auto-generated method stub
		Utente utente = new Utente();
		utente.setEmail(utenteLoginRequestDto.getEmail());
		utente.setPassword(utenteLoginRequestDto.getPassword());
		
		String sha256hex = DigestUtils.sha256Hex(utente.getPassword());
		
		Utente credenziali = utenteDao.findByEmailAndPassword(utente.getEmail(), sha256hex);
		
		return credenziali != null ? true : false;
	}

	@Override
	public Utente cercaConEmail(String email) {

		return utenteDao.findByEmail(email);
	}

}
