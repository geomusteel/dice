package dice.repository;

import dice.domain.Dice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DiceRepositoryImp implements DiceRepository {

    private final static Map<Integer, Integer> diceRepository = new HashMap<>();

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


    }

    @Override
    public Map<Integer, Integer> findAll() {
        return diceRepository;
    }

    @Override
    public void clean() {
        diceRepository.put(1, 0);
        diceRepository.put(2, 0);
        diceRepository.put(3, 0);
        diceRepository.put(4, 0);
        diceRepository.put(5, 0);
        diceRepository.put(6, 0);
    }
}
