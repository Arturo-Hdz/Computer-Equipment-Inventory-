package com.inventario.model;

// Generated Sep 25, 2012 3:42:21 PM by Hibernate Tools 3.2.1.GA

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sucursal generated by hbm2java
 */
@Entity
@Table(name = "nobreak")
public class Nobreak implements java.io.Serializable {

	private static final long serialVersionUID = 4700379246267727806L;
	private Integer id_nobreak;
	private String serie;
	private String modelo;

	private Fabricante fabricante;
	private Set<Equipo> equipo = new HashSet<Equipo>(0);

	public Nobreak() {
	}

	public Nobreak(int id_nobreak, String serie, String modelo) {
		this.setId_nobreak(id_nobreak);
		this.setSerie(serie);
		this.setModelo(modelo);
	}

	public Nobreak(int id_nobreak, String serie, String modelo,
			 Fabricante fabricante, Set<Equipo> equipo) {
		this.setId_nobreak(id_nobreak);
		this.setSerie(serie);
		this.setModelo(modelo);
		this.setFabricante(fabricante);
		this.setEquipo(equipo);
	}

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_nobreak", unique = true, nullable = false)
	public Integer getId_nobreak() {
		return id_nobreak;
	}

	public void setId_nobreak(Integer id_nobreak) {
		this.id_nobreak = id_nobreak;
	}

	@Column(name = "serie", length = 100)
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Column(name = "modelo", length = 100)
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nobreak")
	public Set<Equipo> getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Set<Equipo> equipo) {
		this.equipo = equipo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fabricante", nullable = false)
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
}
