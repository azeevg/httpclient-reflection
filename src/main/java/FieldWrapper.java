import java.lang.reflect.Field;

public class FieldWrapper {
    Object obj;

    public FieldWrapper() {
    }

    public FieldWrapper(Object obj) {
        this.obj = obj;
    }

    private static Object getFieldRecursively(Object o, String fieldName) throws IllegalAccessException {
        Class<?> clazz = o.getClass();
        for (int i = 0; i < 20 && !clazz.equals(Object.class); i++) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.getName().equals(fieldName)) {
                    field.setAccessible(true);
                    Object objectValue = field.get(o);
                    field.setAccessible(false);
                    return objectValue;
                }
            }
            clazz = clazz.getSuperclass();
        }

        return null;
    }

    private static Object field(Object o1, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        if (o1 == null) {
            return null;
        }
        return getFieldRecursively(o1, fieldName);
    }

    public FieldWrapper field(String fieldName) {
        if (obj == null) {
            return new FieldWrapper();
        }
        try {
            return new FieldWrapper(field(obj, fieldName));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new FieldWrapper();
    }
}
