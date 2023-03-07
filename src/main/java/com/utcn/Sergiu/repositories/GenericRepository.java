package com.utcn.Sergiu.repositories;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.utcn.Sergiu.database.ConnectionFactory;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;



public class GenericRepository<T> {
    protected static final Logger LOGGER = Logger.getLogger(GenericRepository.class.getName());
    private ConnectionFactory connectionFactory;
    Class<T> type;
    String name;

    public GenericRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        name = type.getSimpleName().toLowerCase();
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++) {
            constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0) {
                break;
            }
        }
        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }


    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `schooldb`.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createUpdateQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE `schooldb`.");
        sb.append(name);
        sb.append(" SET ");
        for (Field field:type.getDeclaredFields()
             ) {
            if(field.getName().equals("id"))
                continue;
            sb.append(field.getName()  +"=?, ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" Where id =?");
        return sb.toString();
    }
    public void update(T t) {
        String query = createUpdateQuery();
        Connection connection=null;
        System.out.println(query);
        try {
            int i=1;
            Long id= null;
             connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();

                Object value;
                field.setAccessible(true);
                value = field.get(t);

                if (fieldName.equals("id")) {
                    id = (Long)value;
                    continue;
                }

                statement.setObject(i, value);
                i++;
            }
            statement.setLong(i, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            connectionFactory.closeConnection(connection);
        }
    }
    public T findById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = connectionFactory.createConnection();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {

            connectionFactory.closeConnection(connection);
        }
        return null;
    }

    private String getFields() {
        StringBuilder sb = new StringBuilder();
        for (Field field : type.getDeclaredFields()
        ) {
            String fieldName = field.getName();
            if (fieldName.equals("id"))
                continue;

            sb.append(fieldName);

            sb.append(", ");
        }
        return sb.toString();
    }

    public String createInsertStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `schooldb`.");
        sb.append(name);
        sb.append(" (");
        sb.append(getFields());
        sb.delete(sb.length() - 2, sb.length());
        sb.append(") VALUES (");
        for (Field field:type.getDeclaredFields()
             ) {
            String fieldName = field.getName();
            if (fieldName.equals("id"))
                continue;
            sb.append("?, ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");

        return sb.toString();

    }

    private String createDeleteStatement(Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE from `schooldb`.");
        sb.append(name);
        sb.append(" WHERE id = ");
        sb.append(id);
        return sb.toString();
    }



    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(" * ");
        sb.append(" from `schooldb`.");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    public Long create(T obj) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertStatement();
        System.out.println(query);
        try {
            connection = connectionFactory.createConnection();
            statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int i = 1;

            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                if (fieldName.equals("id"))
                    continue;

                Object value;
                field.setAccessible(true);
                value = field.get(obj);

                statement.setObject(i, value);
                i++;
            }

            statement.execute();
            ResultSet rs=statement.getGeneratedKeys();
            rs.next();
            Long id = rs.getLong(1);
            return id;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:create " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {

            connectionFactory.closeConnection(connection);
        }
        return null;


    }

    public List<T> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();

        try {
            connection = connectionFactory.createConnection();
            statement = connection.createStatement();
            resultSet=statement.executeQuery(query);

            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            connectionFactory.closeConnection(connection);
        }
        return null;

    }

    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteStatement(id);
        try {
            connection = connectionFactory.createConnection();
            statement = connection.prepareStatement(query);

             statement.executeUpdate(query);


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete" + e.getMessage());
        } finally {

            connectionFactory.closeConnection(connection);
        }
    }
    public List<T> list()
    {
        return findAll();
    }
}
