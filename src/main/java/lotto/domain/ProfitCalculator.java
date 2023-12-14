package lotto.domain;

import java.util.EnumMap;
import java.util.Map.Entry;

public class ProfitCalculator {

    private static final long PERCENTAGE = 100;
    private static final long LOTTO_PRICE = 1_000;
    private final EnumMap<Prize, Integer> prizeResult;

    public ProfitCalculator(EnumMap<Prize, Integer> prizeResult) {
        this.prizeResult = prizeResult;
    }

    public double calculateProfit() {
        long purchaseAmount = getPurchaseAmount();
        long prizeAmount = getTotalPrizeAmount();

        return (double) prizeAmount / purchaseAmount * PERCENTAGE;
    }

    public long getPurchaseAmount() {
        int count = 0;
        for (Entry<Prize, Integer> pair : prizeResult.entrySet()) {
            count += pair.getValue();
        }
        return count * LOTTO_PRICE;
    }

    public long getTotalPrizeAmount() {
        long totalAmount = 0;
        for (Entry<Prize, Integer> pair : prizeResult.entrySet()) {
            totalAmount += pair.getKey().getPrizeMoney() * pair.getValue();
        }
        return totalAmount;
    }
}
