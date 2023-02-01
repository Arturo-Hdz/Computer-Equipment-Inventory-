package com.inventario.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.model.Usuario;
import com.inventario.service.UsuarioService;

public class UsuarioConverter {
    @Autowired
    protected UsuarioService usuarioService;

    // Actions
    // ------------------------------------------------------------------------------------

    public Object getAsObject(FacesContext context, UIComponent component,
	    String value) throws Exception {
	return usuarioService.findUsuarioByKey(value);
    }

    public String getAsString(FacesContext context, UIComponent component,
	    Object value) {
	return ((Usuario) value).getUsuario();
    }

}
