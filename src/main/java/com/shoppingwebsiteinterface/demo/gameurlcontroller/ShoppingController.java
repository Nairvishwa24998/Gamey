package com.shoppingwebsiteinterface.demo.gameurlcontroller;

import com.shoppingwebsiteinterface.demo.dto.CreateUserDTO;
import com.shoppingwebsiteinterface.demo.dto.GameInfoDto;
import com.shoppingwebsiteinterface.demo.model.BasicGameInfo;
import com.shoppingwebsiteinterface.demo.model.UserInfo;
import com.shoppingwebsiteinterface.demo.model.WishlistItem;
import com.shoppingwebsiteinterface.demo.service.RawgCallService;
import com.shoppingwebsiteinterface.demo.service.implementation.CustomUserDetailsServiceImplementation;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gamey")
public class ShoppingController {


    private final RawgCallService rawgCallService;


    private final CustomUserDetailsServiceImplementation customUserDetailsServiceImplementation;

    public ShoppingController(RawgCallService rawgCallService, CustomUserDetailsServiceImplementation customUserDetailsServiceImplementation) {
        this.rawgCallService = rawgCallService;
        this.customUserDetailsServiceImplementation = customUserDetailsServiceImplementation;
    }


    @GetMapping("/home")
    public String home() {
        return "Home";
    }

    @GetMapping("/userprofile")
    public String userprofile(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserInfo userInfo = customUserDetailsServiceImplementation.getUserInfoByUsername(userName);
        model.addAttribute("userInformation", userInfo);
        return "UserProfile";
    }

    @GetMapping("/owned")
    public String owned() {
        return "Owned";
    }

    @GetMapping("/explore")
    public String explore() {
        return "Explore";
    }


    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("errorMessage") != null) {
            String errorMessage = (String) session.getAttribute("errorMessage");
            model.addAttribute("displayErrorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }
        return "Login";
    }

    @PostMapping("/gameinfo")
    public ResponseEntity<String> gameinfo(@RequestBody GameInfoDto partialGameInfoDto, HttpSession session) {
        GameInfoDto completedGameInfoDto = rawgCallService.getGameInfoDto(partialGameInfoDto);
        BasicGameInfo basicGameInfo = customUserDetailsServiceImplementation.storeGameInfo(completedGameInfoDto);
        session.setAttribute("gameInfo", completedGameInfoDto);
        return ResponseEntity.ok().body("Data processed successfully");
    }

    @GetMapping("/gameinfo")
    public String gameinfo(Model model, HttpSession session) {
        GameInfoDto gameInfoDto = (GameInfoDto) session.getAttribute("gameInfo");
        if (gameInfoDto != null) {
            model.addAttribute("gameInfo", gameInfoDto);

            // Optionally, remove the attribute from the session if it's no longer needed
//            session.removeAttribute("gameInfo");
        }

        // Return the name of the Thymeleaf template to be rendered
        return "Gameinfo";
    }


    @PostMapping("/createuser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO, HttpSession session) {
        try {
            customUserDetailsServiceImplementation.createUser(createUserDTO);
//            System.out.println("****** Customer created succesfully");
            return ResponseEntity.ok().body("User created successfully");
        } catch (DataIntegrityViolationException ex) {
            session.setAttribute("errorMessage", ex.getMessage());
            return ResponseEntity.internalServerError().body("User creation failed");
        }
    }

    @PostMapping("/modifycart/{rawgid}/{operation}")
    ResponseEntity<String> modifyCart(@PathVariable String rawgid, @PathVariable int operation, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        BasicGameInfo basicGameInfo = customUserDetailsServiceImplementation.getGameByRawgId(rawgid);
        customUserDetailsServiceImplementation.modifyCart(userName, basicGameInfo, operation);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/modifywishlist/{rawgid}/{operation}")
    ResponseEntity<String> modifyWishList(@PathVariable String rawgid, @PathVariable int operation, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        BasicGameInfo basicGameInfo = customUserDetailsServiceImplementation.getGameByRawgId(rawgid);
        WishlistItem wishlistItem = customUserDetailsServiceImplementation.modifyWishList(userName, basicGameInfo, operation);
        if (wishlistItem != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/cart")
    public String cart() {
        return "Cart";
    }

//   password- 0TichAzI+_#HuvlTrEzi
// api key - 0191586e29d04fa09769937e35accbbb
}


