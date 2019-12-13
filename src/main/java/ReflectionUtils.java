public class ReflectionUtils {

    public static FieldWrapper getField(Object object, String fieldName) {
        try {
            return new FieldWrapper(object, object.getClass().getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return new FieldWrapper();
        }
    }
}
