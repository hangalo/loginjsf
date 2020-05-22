/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.loginjsf.cdibean;

import com.estudo.loginjsf.dao.UsuarioDAO;
import com.estudo.loginjsf.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author informatica
 */
@Named(value = "loginCDIBean")
@SessionScoped
public class LoginCDIBean implements Serializable {

    private Usuario usuarioLogado;
    private String username, senha;

    @Inject
    UsuarioDAO usuarioDAO;

    public LoginCDIBean() {

    }

    @PostConstruct
    public void init() {

    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    
    
    

    public String login() {

        usuarioLogado = usuarioDAO.findUser(username, senha);

        if (usuarioLogado == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ou senha invalidos", "Login Invalido"));
            return null;
        } else {

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.setAttribute("usuarioLogado", usuarioLogado);
            }
            return "/index?faces-redirect=true";
        }
    }

    public String logOff() {
        FacesContext fc = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "/login?faces-redirect=true";

    }
}
