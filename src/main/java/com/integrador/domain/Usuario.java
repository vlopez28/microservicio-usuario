package com.integrador.domain;

import java.util.List;

import com.integrador.service.dto.usuario.UsuarioRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String celular;
    @Column
    private String email;
    @ManyToMany (fetch = FetchType.LAZY)
    private List<Cuenta> cuentas;
    
    public Usuario() {
    	
    }
    
    public Usuario(UsuarioRequestDto request) {
    	this.nombre = request.getNombre();
    	this.apellido = request.getApellido();
    	this.celular = request.getCelular();
    	this.email = request.getEmail();
    }
    
    public void insertarCuenta(Cuenta c) {
    	if(!cuentas.contains(c)) {
    		cuentas.add(c);
    	}
    }
    
    public boolean quitarCuenta(Cuenta c) {
    	if(cuentas.contains(c)) {
    		return cuentas.remove(c);
    	}else {
    		return false;
    	}
    }

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCelular() {
		return celular;
	}

	public String getEmail() {
		return email;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
    
    
}