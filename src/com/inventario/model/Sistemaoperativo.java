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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Autoridad generated by hbm2java
 */
@Entity
@Table(name = "sistema_operativo")
public class Sistemaoperativo implements java.io.Serializable {

	private static final long serialVersionUID = -2857201549302860473L;
	private Integer id_so;
	private String sistema_operativo;

	private Set<Equipo> equipo = new HashSet<Equipo>(0);

	public Sistemaoperativo() {
	}

	public Sistemaoperativo(Integer id_so, String sistema_operativo) {
		this.id_so = id_so;
		this.sistema_operativo = sistema_operativo;
	}
	
	public Sistemaoperativo(Integer id_so, String sistema_operativo, Set<Equipo> equipo) {
		this.id_so = id_so;
		this.sistema_operativo = sistema_operativo;
		this.equipo = equipo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_so", unique = true, nullable = false)
	public Integer getId_so() {
		return this.id_so;
	}
	
	public void setId_so(Integer id_so){
		this.id_so = id_so;
	}

	@Column(name = "sistema_operativo", nullable = false, length = 200)
	public String getSistema_operativo() {
		return this.sistema_operativo;
	}

	public void setSistema_operativo(String sistema_operativo) {
		this.sistema_operativo = sistema_operativo;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sistemaoperativo")
	public Set<Equipo> getEquipo() {
		return this.equipo;
	}

	public void setequipo(Set<Equipo> equipo) {
		this.equipo = equipo;
	}

	/*
	 * @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "grupo_autoridad", catalog = "dbpizza", joinColumns = {
	 * @JoinColumn(name = "idautoridad", nullable = false, updatable = false) },
	 * inverseJoinColumns = { @JoinColumn(name = "idgrupo", nullable = false,
	 * updatable = false) }) public Set<Grupo> getGrupos() { return this.grupos;
	 * }
	 * 
	 * public void setGrupos(Set<Grupo> grupos) { this.grupos = grupos; }
	 */
}