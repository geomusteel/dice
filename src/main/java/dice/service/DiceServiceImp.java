package dice.service;

import dice.domain.Dice;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceServiceImp implements DiceService{

    public static final Random random = new Random();

    @Override
    public Dice lollDice() {
        Dice dice = new Dice();
        dice.setNumber1(random.nextInt(6)+1);
        dice.setNumber2(random.nextInt(6)+1);
        return dice;
    }

}
