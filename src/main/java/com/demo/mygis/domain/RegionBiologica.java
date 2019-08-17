package com.demo.mygis.domain;

import org.springframework.data.annotation.Id;

public class RegionBiologica {
	@Id
	public String id;
	
	public String nombre;
	public String paisId;
	public String url = "/v1/divisiones_politicas";
	
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

}
