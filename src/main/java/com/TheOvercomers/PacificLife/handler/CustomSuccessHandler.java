package com.TheOvercomers.PacificLife.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy(); //Este objeto ayuda a establecer la nueva estrategia de direccionamiento cuando se inicie sesion.

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication); //LLamo al metodo que me da la url a donde debo ir
        if (response.isCommitted()) {
            System.out.println("No se puede redireccionar");
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    //Este metodo extraer y verificar  el rol de la persona que esta logeada en ese momento. Y con base a ese rol genera un url.
    protected String determineTargetUrl(Authentication authentication) { //Este es un metodo protegido.  me pide autenticacion
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); //Verifica el authority de todos usuarios que estan online y trae la lista y sus permisos.

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) { //Le digo que por cada rol que encuentre en el metodo anterior, alimente este arraylist
            roles.add(a.getAuthority());
        }

        if (esAdministrativo(roles)) {
            url = "/VerEmpresas";  //Me pide la url del navegador para rediccionarme. Si es administrativo me lleva a la url ver empresas
        } else if (esOperativo(roles)) {
            url = "/VerMovimientos"; //Si el rol es operativo me deja acceder solo a la pagina ver movimeintos.
        } else {
            url = "/Denegado"; //Si no tiene rol o esta inactivo me niega el acceso al aplicativo.
        }
        return url;
    }


    private boolean esOperativo(List<String> roles) {
        if (roles.contains("ROLE_USER")) { // este metodo verifica si el usuario es o no operativo al recorrer toda la lista.
            return true;
        }
        return false;
    }

    private boolean esAdministrativo(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) { // este metodo verifica si el usuario es o no administrativo al recorrer toda la lista.
            return true;
        }
        return false;
    }
}
