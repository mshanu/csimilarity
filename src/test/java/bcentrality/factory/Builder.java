package bcentrality.factory;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Builder<T> {
    private Class<T> clazz;
    private Map<String, Object> objectMap = new HashMap();

    private Builder(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T> Builder<T> forClass(Class<T> clazz) {
        return new Builder<>(clazz);
    }

    public Builder<T> with(String propertyName, Object value) {
        objectMap.put(propertyName, value);
        return this;
    }

    public T build() {
        T newInstance;
        try {
            newInstance = clazz.newInstance();
            for (Map.Entry<String, Object> eachKeyValue : objectMap.entrySet()) {
                Field declaredField = clazz.getDeclaredField(eachKeyValue.getKey());
                declaredField.setAccessible(true);
                declaredField.set(newInstance, eachKeyValue.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newInstance;
    }
}
