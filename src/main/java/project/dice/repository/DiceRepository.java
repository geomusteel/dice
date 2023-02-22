package project.dice.repository;


import project.dice.domain.Dice;

import java.util.Map;

public interface DiceRepository {
    void save(Dice dice);
    Map<Integer,Integer> findAll();
    void clean();
    Dice getLastDice();
}
