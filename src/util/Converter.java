package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Converter {
    public <C, T> void convertTo(C convert, T to) {
        //util.Pair<Method, Method> the first method is getter and second method is setter
        Map<Pair<String, Field>, Pair<Method, Method>> convertMethods = mapFieldMethods(convert);
        Map<Pair<String, Field>, Pair<Method, Method>> toMethods = mapFieldMethods(to);
        Pair<String, Field> convertKey;

        for(Pair<String, Field> key : toMethods.keySet()) {
            try {
                convertKey = findSimilarKey(key, convertMethods.keySet());
                if(convertKey != null && convertMethods.containsKey(convertKey)) {
                    if(!isPrimitive(key.getP1().getType())) { //We expect to more objects than primitives
                        Class<?> type = key.getP1().getType();
                        Object innerToObject;
                        if(!type.getName().toLowerCase().contains("list")) {
                            innerToObject = type.getDeclaredConstructor().newInstance();
                            Object innerConvertObject = convertMethods.get(convertKey).getP0().invoke(convert);
                            convertTo(innerConvertObject, innerToObject);
                            toMethods.get(key).getP1()
                                    .invoke(to, innerToObject);
                        }
                        else {
                            ParameterizedType innerType = (ParameterizedType) key.getP1().getGenericType();
                            Class<?> innertTypeArgument = (Class<?>) innerType.getActualTypeArguments()[0];
                            List<Object> convertInnerList = (List<Object>) convertMethods.get(convertKey).getP0().invoke(convert);
                            List<Object> toInnerList = new ArrayList<>();

                            for(int i = 0; i < convertInnerList.size(); i++) {
                                innerToObject = innertTypeArgument.getDeclaredConstructor().newInstance();
                                convertTo(convertInnerList.get(i), innerToObject);
                                toInnerList.add(innerToObject);
                            }

                            toMethods.get(key).getP1().invoke(to, toInnerList);
                        }
                    }
                    else
                        toMethods.get(key).getP1()
                                .invoke(to, convertMethods.get(convertKey).getP0().invoke(convert));
                }
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private <O> Map<Pair<String, Field>, Pair<Method, Method>> mapFieldMethods(O object) {
        Map<Pair<String, Field>, Pair<Method, Method>> mapped = new HashMap<>();

        List<Field> fields = Arrays.stream(object.getClass().getDeclaredFields()).toList();
        Map<String, Method> methodMap = Arrays.stream(object.getClass().getDeclaredMethods())
                .collect(Collectors.toMap(method -> method.getName().toLowerCase(), method -> method));

        Pair<String, Field> stringField;
        Pair<Method, Method> getSet;
        String methodName;

        for(Field field : fields) {
            stringField = new Pair<>(field.getName().toLowerCase(), field);
            getSet = new Pair<>();

            methodName = "is"+field.getName().toLowerCase();
            if(methodMap.containsKey(methodName))
                getSet.setP0(methodMap.get(methodName));

            methodName = "get"+field.getName().toLowerCase();
            if(methodMap.containsKey(methodName))
                getSet.setP0(methodMap.get(methodName));

            methodName = "set"+field.getName().toLowerCase();
            if(methodMap.containsKey(methodName))
                getSet.setP1(methodMap.get(methodName));

            mapped.put(stringField, getSet);
        }

        return mapped;
    }

    private Pair<String, Field> findSimilarKey(Pair<String, Field> key, Set<Pair<String, Field>> keySet) {
        for(Pair<String, Field> sKey : keySet)
            if(key.getP0().startsWith(sKey.getP0()))
                return sKey;

        return null;
    }

    private boolean isPrimitive(Class<?> type) {
        return switch (type.getName().toLowerCase()) {
            case    "boolean", "byte",
                    "char", "character",
                    "double",
                    "float",
                    "int", "integer",
                    "long",
                    "short" -> true;
            default -> false;
        };
    }
}
