/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.horas.persistence.jdbc;
import co.horas.entities.Usuario;
import co.horas.persistence.DaoUsuario;
import co.horas.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zacehiro
 */
public class JDBCDaoUsuario implements DaoUsuario{
    Connection con;
    
    /**
     * Creación del JDBCDaoUsuario.
     * @param con conexión del Dao.
     */
    public JDBCDaoUsuario(Connection con) {
        this.con = con;
    }
    
    /**
     * Guardar un usuario en la base de datos.
     * @param u usuario que se va a guardar en la base de datos.
     * @throws PersistenceException 
     */
    @Override
    public void save(Usuario u) throws PersistenceException {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into Usuario (nombre,carnet,horas,pwd) values(?,?,?,?)");
            ps.setString(1, u.getNombre());
            ps.setInt(2, u.getHoras());
            ps.execute();
            con.commit();
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while saving an user.",ex);
        }
    }

        

    /**
     * Actualizar la información de un usuario existente.
     * @param u usuario que va a ser actualizado.
     * @throws PersistenceException 
     */
    @Override
    public void Update(String nombre, int horas) throws PersistenceException {
        PreparedStatement ps;
        int hora =0;
        try {
            ps=con.prepareStatement("Select horas From Usuario Where nombre=?");
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();
            rs.next();
            hora = rs.getInt(1);
            
            ps=con.prepareStatement("UPDATE Usuario SET horas=? WHERE nombre=?");
            ps.setInt(1, horas+hora);
            ps.setString(2, nombre);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    /**
     * Consultar todos los usuarios existentes de la base de datos.
     * @return una lista con todas los usuarios existentes, si no hay retorna una lista vacia.
     * @throws PersistenceException 
     */
    @Override
    public List<Usuario> loadAll() throws PersistenceException {
        List<Usuario> ans= new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select nombre, horas from Usuario");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ans.add(new Usuario(rs.getString(1),rs.getInt(2)));
            }
                
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading a product.",ex);
        }
        return ans;
    }

    @Override
    public Usuario loadUser(String nombre) throws PersistenceException {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select nombre,horas from Usuario where nombre=? ");
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();
            rs.next();
            return new Usuario(rs.getString(1), rs.getInt(2));
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading an user.",ex);
        }
    }
    
}
