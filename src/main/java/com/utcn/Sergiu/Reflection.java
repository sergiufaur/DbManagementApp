package com.utcn.Sergiu;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Reflection {
    public static Object[] retrieveProperties(Object object) {


        Object [] data = new Object[30];
        int index = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                data[index] = value;
                index++;

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return data;

    }
}
