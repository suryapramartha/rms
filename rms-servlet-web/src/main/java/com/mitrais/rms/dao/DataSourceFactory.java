package com.mitrais.rms.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides MySQL datasource to be used to connect to database.
 * It implements singleton pattern See <a href="http://www.oodesign.com/singleton-pattern.html">Singleton Pattern</a>
 */
public class DataSourceFactory
{
    private final DataSource dataSource;

    DataSourceFactory()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        Properties prop = new Properties();
        String propFileName = "database.properties";
        InputStream is = null;
        try
        {
			is = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (is != null) 
			{
				prop.load(is);
			} else 
			{
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
    	}catch(IOException e) 
        {
    		e.printStackTrace();
    		is = null;
    	}
        dataSource.setDatabaseName(prop.getProperty("dbname"));
        dataSource.setUser(prop.getProperty("dbuser"));
        dataSource.setPassword(prop.getProperty("dbpassword"));
        this.dataSource = dataSource;
    }

    /**
     * Get a data source to database
     *
     * @return DataSource object
     */
    public static Connection getConnection() throws SQLException
    {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
