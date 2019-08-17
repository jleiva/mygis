package com.demo.mygis.domain;

import org.springframework.data.annotation.Id;

public class Animal {
	@Id
	public String id;
	
	public String nombreComun;
	public String nombreCientifico;
	public int poblacion;
	public boolean enPeligroExtincion;
	public String url = "/v1/animales";
	
	public Animal() {}

	
	public String getUrl() {
		return url + "/" + this.id;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public boolean isEnPeligroExtincion() {
		return enPeligroExtincion;
	}

	public void setEnPeligroExtincion(boolean enPeligroExtincion) {
		this.enPeligroExtincion = enPeligroExtincion;
	}
	
	
}
