package lotto.domain;

public enum Prize {
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    NONE(0, 0);

    private final int matchCount;
    private final long prizeMoney;

    Prize(int matchCount, long prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static Prize getPrize(int matchCount, boolean isSecond) {
        Prize prize = findByMatchCount(matchCount);
        if (prize == THIRD && isSecond) {
            return SECOND;
        }
        return prize;
    }

    public static Prize findByMatchCount(int matchCount) {
        for (Prize prize : Prize.values()) {
            if (prize.matchCount == matchCount) {
                return prize;
            }
        }
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
