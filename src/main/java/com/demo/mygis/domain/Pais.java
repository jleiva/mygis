package com.demo.mygis.domain;

import org.springframework.data.annotation.Id;

public class Pais {
	@Id
	public String id;
	
	public String nombre;
	public String continente;
	public Long superficieTerrestre;
	public Long superficieMaritima;
	
	public Pais() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public Long getSuperficieTerrestre() {
		return superficieTerrestre;
	}

	public void setSuperficieTerrestre(Long superficieTerrestre) {
		this.superficieTerrestre = superficieTerrestre;
	}

	public Long getSuperficieMaritima() {
		return superficieMaritima;
	}

	public void setSuperficieMaritima(Long superficieMaritima) {
		this.superficieMaritima = superficieMaritima;
	}
	
	@Override
	public String toString() {
		return "Pais {" +
				", nombre='" + nombre + '\'' +
				", continente='" + continente + '\'' +
				'}';
	}

}
