/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.loginjsf.phaselistener;

import com.estudo.loginjsf.model.Usuario;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author informatica
 */
public class LoginPhaseListener implements PhaseListener {

    private FacesContext facesContext;

    @Override
    public void afterPhase(PhaseEvent event) {
        facesContext = event.getFacesContext();
        String viewId = facesContext.getViewRoot().getViewId();

        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        boolean paginaLogin = viewId.lastIndexOf("login") > -1;
        if (existeUsuarioLogado() && paginaLogin) {
            navigationHandler.handleNavigation(facesContext, null, "index?faces-redirect=true");
        } else if (!existeUsuarioLogado() && !paginaLogin) {
            navigationHandler.handleNavigation(facesContext, null, "login?faces-redirect=true");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public boolean existeUsuarioLogado() {
        return (((Usuario) getAtributoSessao("usuarioLogado")) != null);

    }

    public Object getAtributoSessao(String attributeName) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            return session.getAttribute(attributeName);
        }
        return null;
    }

}
