
import java.util.function.Function;

public class ThrowingUtils {
    public static <T, R> Function<T, R> unchecked(ThrowingFunction<T, R> f) {
        return t -> applySafe(f, t);
    }

    private static <T, R> R applySafe(ThrowingFunction<T, R> f, T t) {
        try {
            return f.apply(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FunctionalInterface
    public interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }
}
