package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.MeteoData;

public interface MeteoDataDao extends CrudRepository<MeteoData, Integer>{

}
