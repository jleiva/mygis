package com.demo.mygis.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class RegionBiologica {
	@Id
	public String id;
	
	public String nombre;
	public String paisId;
	public String url = "/v1/divisiones_politicas";
	
	private List<Animal> animales;
	private List<Planta> plantas;
	
	public RegionBiologica() {}
	
	public String getUrl() {
		return url + "/" + this.id;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaisId() {
		return paisId;
	}

	public void setPaisId(String paisId) {
		this.paisId = paisId;
	}

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

}
