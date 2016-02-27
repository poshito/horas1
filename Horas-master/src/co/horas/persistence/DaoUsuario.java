package co.horas.persistence;

import co.horas.entities.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zacehiro
 */
public interface DaoUsuario {
   
    public void save(Usuario u) throws PersistenceException;
    public Usuario loadUser(String nombre) throws PersistenceException;
    public void Update(String nombre, int horas) throws PersistenceException;
    public List<Usuario> loadAll() throws PersistenceException;
    
}
