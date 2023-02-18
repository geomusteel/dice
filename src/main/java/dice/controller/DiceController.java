package dice.controller;

import dice.domain.Dice;
import dice.repository.DiceRepository;
import dice.service.DiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DiceController {

    private final DiceService diceService;
    private final DiceRepository diceRepository;

    @GetMapping("/dice")
    public String createDice(Model model) {
        Dice dice = diceService.lollDice();
        model.addAttribute("dice",dice);
        model.addAttribute("diceNumbers",diceRepository.findAll());
        return "/dice/diceIndex";
    }

    @GetMapping("/dice/loll")
    public String lollDice(Model model) {
        Dice dice = diceService.lollDice();
        diceRepository.save(dice);
        model.addAttribute("dice",dice);
        model.addAttribute("diceNumbers",diceRepository.findAll());
        return "redirect:/dice";
    }


    @GetMapping("/dice/clean")
    public String diceClean() {
        diceRepository.clean();
        return "redirect:/dice";
    }



}
