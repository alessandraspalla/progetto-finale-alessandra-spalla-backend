package it.corso.dto;

import java.util.Date;

public class DatiMeteoCreazioneDto {

	private String city;
    private double temperature;
    private double feelsLike;
    private String description;
    private double windSpeed;
    private double humidity;
    private double clouds;
    private Date sunrise;
    private Date sunset;
    private int idUtente;
    
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getFeelsLike() {
		return feelsLike;
	}
	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getClouds() {
		return clouds;
	}
	public void setClouds(double clouds) {
		this.clouds = clouds;
	}
	public Date getSunrise() {
		return sunrise;
	}
	public void setSunrise(Date sunrise) {
		this.sunrise = sunrise;
	}
	public Date getSunset() {
		return sunset;
	}
	public void setSunset(Date sunset) {
		this.sunset = sunset;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
}
