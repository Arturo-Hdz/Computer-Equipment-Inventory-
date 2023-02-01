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
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario")
public class Usuario implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3547436526200716475L;
    private Integer id;
   // private Sucursal sucursal;
    //private Grupo grupo;
    private String usuario;
    private String password;
    private String email;
    private Integer estatus;

   //private Set<Movimientos> movimiento = new HashSet<Movimientos>(0);
    
    public Usuario() {
    }
    
    
    @Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", password=" + password + ", email=" + email
				+ ", estatus=" + estatus + "]";
	}

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
	return this.id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    @Column(name = "usuario", nullable = false, length = 16)
    public String getUsuario() {
	return this.usuario;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Column(name = "estatus", nullable = false)
    public Integer getEstatus() {
	return this.estatus;
    }

    public void setEstatus(Integer estatus) {
	this.estatus = estatus;
    }

}