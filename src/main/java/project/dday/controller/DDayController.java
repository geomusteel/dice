package project.dday.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DDayController {

    @GetMapping("/d-day")
    public String dDay(){

        return "/d-day/d-day-main";
    }

}
