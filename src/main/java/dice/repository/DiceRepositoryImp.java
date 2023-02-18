package dice.repository;

import dice.domain.Dice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DiceRepositoryImp implements DiceRepository {

    private final static Map<Integer, Integer> diceRepository = new HashMap<>();
    private Dice lastDice = new Dice();

    public DiceRepositoryImp() {
        diceRepository.put(1, 0);
        diceRepository.put(2, 0);
        diceRepository.put(3, 0);
        diceRepository.put(4, 0);
        diceRepository.put(5, 0);
        diceRepository.put(6, 0);
    }

    @Override
    public void save(Dice dice) {
        Integer number1 = dice.getNumber1();
        Integer number2 = dice.getNumber2();

        Integer temp1 = diceRepository.get(number1);
        diceRepository.put(number1, ++temp1);

        Integer temp2 = diceRepository.get(number2);
        diceRepository.put(number2, ++temp2);

        lastDice = dice;
    }

    @Override
    public Dice getLastDice() {
        return lastDice;
    }

    @Override
    public Map<Integer, Integer> findAll() {
        return diceRepository;
    }

    @Override
    public void clean() {
        lastDice.setNumber1(0);
        lastDice.setNumber2(0);
        diceRepository.put(1, 0);
        diceRepository.put(2, 0);
        diceRepository.put(3, 0);
        diceRepository.put(4, 0);
        diceRepository.put(5, 0);
        diceRepository.put(6, 0);
    }
}
