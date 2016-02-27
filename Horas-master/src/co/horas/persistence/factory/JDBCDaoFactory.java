/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package co.horas.persistence.factory;

import co.horas.persistence.DaoFactory;
import co.horas.persistence.DaoUsuario;
import co.horas.persistence.PersistenceException;
import co.horas.persistence.jdbc.JDBCDaoUsuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Zacehiro
 */
public class JDBCDaoFactory extends DaoFactory {

    private static final ThreadLocal<Connection> connectionInstance = new ThreadLocal<Connection>() {
    };

    private static Properties appProperties = null;

    public JDBCDaoFactory(Properties appProperties) {
        this.appProperties = appProperties;
    }

    private static Connection openConnection() throws PersistenceException {
        String url = "jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/pdswg2";//appProperties.getProperty("url");
        String driver = "com.mysql.jdbc.Driver";//appProperties.getProperty("driver");
        String user = "pdswg2";//appProperties.getProperty("user");
        String pwd = "pdswg02";//appProperties.getProperty("pwd");

        try {
            Class.forName(driver);
            Connection _con = DriverManager.getConnection(url, user, pwd);
            
            _con.setAutoCommit(false);
            return _con;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new PersistenceException("Error on connection opening.", ex);
        }

    }

    @Override
    public void beginSession() throws PersistenceException {
        connectionInstance.set(openConnection());

    }

    @Override
    public void endSession() throws PersistenceException {
        try {
            if (connectionInstance.get() == null || connectionInstance.get().isClosed()) {
                throw new PersistenceException("Conection is null or is already closed.");
            } else {
                connectionInstance.get().close();
            }
        } catch (SQLException ex) {
            throw new PersistenceException("Error on connection closing.", ex);
        }
    }

    @Override
    public void commitTransaction() throws PersistenceException {
        try {
            if (connectionInstance.get() == null || connectionInstance.get().isClosed()) {
                throw new PersistenceException("Conection is null or is already closed.");
            } else {
                connectionInstance.get().commit();
            }
        } catch (SQLException ex) {
            throw new PersistenceException("Error on connection closing.", ex);
        }
    }

    @Override
    public void rollbackTransaction() throws PersistenceException {
        try {
            if (connectionInstance.get() == null || connectionInstance.get().isClosed()) {
                throw new PersistenceException("Conection is null or is already closed.");
            } else {
                connectionInstance.get().rollback();
            }
        } catch (SQLException ex) {
            throw new PersistenceException("Error on connection closing.", ex);
        }
    }

    @Override
    public DaoUsuario getDaoUsuario() throws PersistenceException {
         return new JDBCDaoUsuario(connectionInstance.get());
    }

}