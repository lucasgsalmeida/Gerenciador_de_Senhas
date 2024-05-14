package me.lucas.gerenciadorDeSenhas.service;

import me.lucas.gerenciadorDeSenhas.model.domain.user.Usuario;
import me.lucas.gerenciadorDeSenhas.temporary.UsuarioStateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class VerifyUserService {

    @Autowired
    private UsuarioStateCache stateCache;

    public Usuario getUser(UserDetails userDetails) {
        return stateCache.getUserState(userDetails.getUsername());
    }
}
