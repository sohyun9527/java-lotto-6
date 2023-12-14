package lotto.domain;

import java.util.EnumMap;
import java.util.List;

public class WinningNumbers {
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningLotto, String bonusInput) {
        this.winningLotto = winningLotto;
        this.bonusNumber = validateBonusNumber(bonusInput);
    }

    public EnumMap<Prize, Integer> checkPrizeResult(List<Lotto> purchaseLotto) {
        EnumMap<Prize, Integer> result = initialize();

        for (Lotto lotto : purchaseLotto) {
            int matchCount = winningLotto.matchCount(lotto);
            Prize prize = Prize.getPrize(matchCount, lotto.isContain(bonusNumber));
            result.put(prize, result.getOrDefault(prize, 0) + 1);
        }
        return result;
    }

    private EnumMap<Prize, Integer> initialize() {
        EnumMap<Prize, Integer> map = new EnumMap<>(Prize.class);
        for (Prize value : Prize.values()) {
            map.put(value, 0);
        }

        return map;
    }

    private int validateBonusNumber(String bonusInput) {
        // 숫자만? 당첨번호랑 중복 안됨?
        validateOnlyDigit(bonusInput);
        int bonusNumber = Integer.parseInt(bonusInput);
        validateNumberRange(bonusNumber);
        validateDuplicateWithWinningNumber(bonusNumber);

        return bonusNumber;
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < START_NUMBER || bonusNumber > END_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 1 ~ 45 사이의 수를 입력해주세요.");
        }
    }

    private void validateDuplicateWithWinningNumber(int bonusNumber) {
        if (winningLotto.isContain(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복될 수 없습니다!");
        }
    }

    private void validateOnlyDigit(String bonusInput) {
        if (!bonusInput.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요");
        }
    }


}
