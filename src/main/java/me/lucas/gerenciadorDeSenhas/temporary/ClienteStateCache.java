package me.lucas.gerenciadorDeSenhas.temporary;

import me.lucas.gerenciadorDeSenhas.model.domain.cliente.Cliente;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClienteStateCache {

    private final Map<Long, Cliente> userStateMap = new ConcurrentHashMap<>();

    public void saveClienteState(Long id, Cliente clientes) {
        userStateMap.put(id, clientes);
    }

    public Cliente getClienteState(Long id) {
        return userStateMap.get(id);
    }

    public void removeClienteState(Long id) {
        userStateMap.remove(id);
    }

}
