package net.sgoliver.android.fragments;

import android.media.Image;

public class Artista
{
	private String nombre;
	private String descripcion;
	//private Image foto;

	public Artista(String nombre, String descripcion/*, Image foto*/) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		//this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}*/
}
