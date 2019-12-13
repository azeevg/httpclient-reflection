import java.lang.reflect.Field;

public class FieldWrapper {
    Field field;
    Object parent;

    public FieldWrapper() {
    }

    public FieldWrapper(Object parent, Field field) {
        this.parent = parent;
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public FieldWrapper field(String fieldName) {
        try {
            field.setAccessible(true);
            Object fieldValue = field.get(parent);
            field.setAccessible(false);

            return new FieldWrapper(fieldValue, fieldValue.getClass().getDeclaredField(fieldName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
