package com.shoppingwebsiteinterface.demo.gameurlcontroller;

import com.shoppingwebsiteinterface.demo.dto.GameInfoDto;
import com.shoppingwebsiteinterface.demo.service.RawgCallService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/gamey")
public class ShoppingController {


    private final RawgCallService rawgCallService;

    public ShoppingController(RawgCallService rawgCallService) {
        this.rawgCallService = rawgCallService;
    }


    @GetMapping("/home")
    public String home() {
        return "Home";
    }

    @GetMapping("/owned")
    public String owned() {
        return "Owned";
    }

    @GetMapping("/explore")
    public String explore() {
        return "Explore";
    }

    @PostMapping("/gameinfo")
    public ResponseEntity<String> gameinfo(@RequestBody GameInfoDto partialGameInfoDto, HttpSession session){
        GameInfoDto completedGameInfoDto = rawgCallService.getGameInfoDto(partialGameInfoDto);
        session.setAttribute("gameInfo", completedGameInfoDto);
        return ResponseEntity.ok().body("Data processed successfully");
    }

    @GetMapping("/gameinfo")
    public String gameinfo(Model model, HttpSession session)
    {
        GameInfoDto gameInfoDto = (GameInfoDto) session.getAttribute("gameInfo");
        if (gameInfoDto != null) {
            model.addAttribute("gameInfo", gameInfoDto);

            // Optionally, remove the attribute from the session if it's no longer needed
            session.removeAttribute("gameInfo");
        }

        // Return the name of the Thymeleaf template to be rendered
        return "Gameinfo";
    }


    @GetMapping("/cart")
    public String cart() {
        return "Cart";
    }

//   password- 0TichAzI+_#HuvlTrEzi
// api key - 0191586e29d04fa09769937e35accbbb
}


