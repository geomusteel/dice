package dice.repository;


import dice.domain.Dice;

import java.util.List;
import java.util.Map;

public interface DiceRepository {
    void save(Dice dice);
    Map<Integer,Integer> findAll();
    void clean();
    Dice getLastDice();
}
