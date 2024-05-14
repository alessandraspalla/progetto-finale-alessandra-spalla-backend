package it.corso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.MeteoDataDao;
import it.corso.dao.UtenteDao;
import it.corso.dto.DatiMeteoCreazioneDto;
import it.corso.model.MeteoData;
import it.corso.model.Utente;

@Service
public class MeteoDataServiceImpl implements MeteoDataService {
	
	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private MeteoDataDao meteoDataDao;

	@Override
	public void registrazioneMeteo(DatiMeteoCreazioneDto datiMeteoDto) {
		Optional<Utente> utenteDb = utenteDao.findById(datiMeteoDto.getIdUtente());
		
		if(utenteDb.isPresent()) {
			Utente utente = utenteDb.get();
			
			MeteoData meteoData = new MeteoData();
			
			meteoData.setCity(datiMeteoDto.getCity());
		    meteoData.setTemperature(datiMeteoDto.getTemperature());
		    meteoData.setFeelsLike(datiMeteoDto.getFeelsLike());
		    meteoData.setDescription(datiMeteoDto.getDescription());
		    meteoData.setWindSpeed(datiMeteoDto.getWindSpeed());
		    meteoData.setHumidity(datiMeteoDto.getHumidity());
		    meteoData.setClouds(datiMeteoDto.getClouds());
		    meteoData.setSunrise(datiMeteoDto.getSunrise());
		    meteoData.setSunset(datiMeteoDto.getSunset());
		    meteoData.setUtente(utente);
		    
		    meteoDataDao.save(meteoData);
		}

	}

}
