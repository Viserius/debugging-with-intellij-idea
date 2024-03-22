package opdrachten.stroom;

import opdrachten.MeanderendeStroom;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.IntPredicate;

public class Zijstroom {

    private static final Method[] methods = LagerGelegenConstructies.class.getMethods();

    private static final MeanderendeStroom.TheoretischeKnelpunten lagerGelegenConstructies = new LagerGelegenConstructies();

    @NotNull
    public static IntPredicate weerspiegeling() {
        return c -> {
            try {
                return ((boolean) Arrays.stream(methods)
                        .filter(method -> method.getName().equals("brug"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("brug methode niet gevonden"))
                        .invoke(lagerGelegenConstructies, c));
            } catch (Exception e) {
                return false;
            }
        };
    }
}
