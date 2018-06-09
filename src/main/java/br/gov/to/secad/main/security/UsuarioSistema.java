package br.gov.to.secad.main.security;

import br.gov.to.secad.main.domain.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.Collection;

public class UsuarioSistema extends User implements Principal {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    public UsuarioSistema(String username, String password, Collection<? extends GrantedAuthority> authorities, Usuario usuario) {
        super(username, password, authorities);
		this.usuario = usuario;
	}

    @Override
    public String getName() {
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UsuarioSistema{");
        sb.append("usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}

