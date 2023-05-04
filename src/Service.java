import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    //Pair<Method, Method> the first method is getter and second method is setter
    public <T, D> void convertToDTO(T entity, D dto) {
        Map<String, Pair<Method, Method>> entityMethods = mapFieldMethods(entity);
        Map<String, Pair<Method, Method>> dtoMethods = mapFieldMethods(dto);

        Arrays.stream(dto.getClass().getDeclaredFields())
                .map(field -> new Pair<>(field, field.getName().toLowerCase())) //mapped field to p1 and field name to p2 (in lower case)
                .forEach(fieldStringPair -> {
                    if(dtoMethods.containsKey(fieldStringPair.getP2())) {
                        try {
                            if(fieldStringPair.getP2().endsWith("dto") && entityMethods.containsKey(fieldStringPair.getP2().substring(0, fieldStringPair.getP2().length() - 3))) {
                                Object eEntity = entityMethods.get(fieldStringPair.getP2().substring(0, fieldStringPair.getP2().length() - 3)).getP1().invoke(entity);

                                Class<?> dDtoType = fieldStringPair.getP1().getType();
                                Object dDto = dDtoType.getDeclaredConstructor().newInstance();

                                convertToDTO(eEntity, dDto);
                                dtoMethods.get(fieldStringPair.getP2()).getP2().invoke(dto, dDto);
                            } else if (entityMethods.containsKey(fieldStringPair.getP2())) {
                                dtoMethods.get(fieldStringPair.getP2()).getP2().invoke(dto, entityMethods.get(fieldStringPair.getP2()).getP1().invoke(entity));
                            }
                        }
                        catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                               NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private <O> Map<String, Pair<Method, Method>> mapFieldMethods(O object) {
        List<Method> methods = Arrays.stream(object.getClass().getDeclaredMethods()).toList();
        Map<String, Pair<Method, Method>> mapped = new HashMap<>();

        String fieldName;
        Pair<Method, Method> p;
        for(Method method: methods) {

            //the current method isn't a getter or setter
            if(!method.getName().startsWith("is") && !method.getName().startsWith("get") && !method.getName().startsWith("set"))
                continue;

            //Set the current method field name
            if(method.getName().startsWith("is"))
                fieldName = method.getName().substring(2).toLowerCase();
            else
                fieldName = method.getName().substring(3).toLowerCase();

            p = new Pair<>();
            //our method is getter
            if(method.getName().startsWith("is") || method.getName().startsWith("get"))
                p.setP1(method);
            //our method is setter (we know this because all other methods are already passed
            else
                p.setP2(method);

            //the current field already has a getter or setter mapped
            if(mapped.containsKey(fieldName))
                p.combine(mapped.get(fieldName));

            mapped.put(fieldName, p);
        }

        return mapped;
    }
}
