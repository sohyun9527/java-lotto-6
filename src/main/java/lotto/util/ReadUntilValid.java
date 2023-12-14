package lotto.util;

import java.util.function.Supplier;

public class ReadUntilValid {

    public static <T> T readUntilValidInput(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
