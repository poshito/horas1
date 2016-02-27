package co.horas.labadm.services;


import co.horas.entities.Usuario;
import co.horas.persistence.DaoFactory;
import co.horas.persistence.DaoUsuario;
import co.horas.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zacehiro
 */
public class ServicesFacade {
    private DaoFactory df = null;
    private static ServicesFacade instance=null;
    private final Properties properties=new Properties();
    
    
    private ServicesFacade(String propFileName) throws IOException{        
	InputStream input = null;
        input = this.getClass().getClassLoader().getResourceAsStream(propFileName);
        properties.load(input);
    }
    
    public static ServicesFacade getInstance(String propertiesFileName) throws RuntimeException{
        if (instance==null){
            try {
                instance=new ServicesFacade(propertiesFileName);
            } catch (IOException ex) {
                throw new RuntimeException("Error on application configuration:",ex);
            }
        }
        return instance;
    }
    
    /**
     * Guarda la soliditud parametro en la base de datos.
     * @param s Solicitud a guardar
     * @throws ServicesFacadeException Problema al leer en la base de datos.
     */
    public void saveTiempo(String nombre, int tiempo){
        df=DaoFactory.getInstance(properties);
        DaoUsuario user;
        try {
            df.beginSession();
            user = df.getDaoUsuario();
            user.Update(nombre, tiempo);
            df.endSession();
        }catch (PersistenceException ex) {
            Logger.getLogger(ServicesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario loadUsuario(String nombre) {
        df=DaoFactory.getInstance(properties);
        DaoUsuario user;
        Usuario u = new Usuario("pancho", 0);
        try{
            df.beginSession();
            user= df.getDaoUsuario();
            u=user.loadUser(nombre);
        } catch (PersistenceException ex) {
            Logger.getLogger(ServicesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public List<Usuario> loadAllUsuario(){
        List<Usuario> u = new ArrayList<Usuario>();
        df=DaoFactory.getInstance(properties);
        DaoUsuario user;
        try{
            df.beginSession();
            user= df.getDaoUsuario();
            u=user.loadAll();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServicesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
}
    
 