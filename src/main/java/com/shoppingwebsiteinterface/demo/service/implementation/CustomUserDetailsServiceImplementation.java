package com.shoppingwebsiteinterface.demo.service.implementation;

import com.shoppingwebsiteinterface.demo.dto.CreateUserDTO;
import com.shoppingwebsiteinterface.demo.dto.GameInfoDto;
import com.shoppingwebsiteinterface.demo.dto.GameOrderDTO;
import com.shoppingwebsiteinterface.demo.model.*;
import com.shoppingwebsiteinterface.demo.repository.BasicGameInfoRepository;
import com.shoppingwebsiteinterface.demo.repository.CartRepository;
import com.shoppingwebsiteinterface.demo.repository.UserRepository;
import com.shoppingwebsiteinterface.demo.repository.WishlistRepository;
import jakarta.persistence.PersistenceException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class CustomUserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasicGameInfoRepository basicGameInfoRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("The User details entered were invalid");
    }

    public UserInfo getUserInfoByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String addToWishList() {
        return null;
    }


    public BasicGameInfo getGameByRawgId(String rawgId) {
        BasicGameInfo gameInfo = basicGameInfoRepository.findByRawgId(rawgId);
        return gameInfo;
    }

    public WishlistItem modifyWishList(String userName, BasicGameInfo gameInfo, int operation) {
        try {
            UserInfo userInfo = getUserInfoByUsername(userName);
            BasicGameInfo basicGameInfo = gameDatabaseCheckAndStore(gameInfo);
            WishlistItem wishlistItem = new WishlistItem();
            wishlistItem.setBasicGameInfo(basicGameInfo);
            wishlistItem.setUser(userInfo);
            return modifyAndAddWishListItem(wishlistItem, operation);
        } catch (PersistenceException ex) {
            throw new PersistenceException("Unable to add item to Wishlist!");
        }
    }

    public CartItem modifyCart(String userName, BasicGameInfo gameInfo, int operation) {
        try {
            UserInfo userInfo = getUserInfoByUsername(userName);
            BasicGameInfo basicGameInfo = gameDatabaseCheckAndStore(gameInfo);
            CartItem cartItem = new CartItem();
            cartItem.setUser(userInfo);
            cartItem.setBasicGameInfo(basicGameInfo);
            cartItem = modifyQuantity(cartItem, operation);
            return cartRepository.save(cartItem);
        } catch (PersistenceException ex) {
            throw new PersistenceException("Unable to add item to Card!");
        }
    }


    private BasicGameInfo gameDatabaseCheckAndStore(BasicGameInfo gameInfo) {
        BasicGameInfo basicGameInfo = null;
        if (basicGameInfoRepository.findByRawgId(gameInfo.getRawgId()) != null) {
            basicGameInfo = basicGameInfoRepository.findByRawgId(gameInfo.getRawgId());
        } else {
            basicGameInfo = basicGameInfoRepository.save(gameInfo);
        }
        return basicGameInfo;
    }

    private CartItem modifyQuantity(CartItem cartItem, int operation) {
        if (cartRepository.findByBasicGameInfoAndUser(cartItem.getBasicGameInfo(), cartItem.getUser()) != null) {
            cartItem = cartRepository.findByBasicGameInfoAndUser(cartItem.getBasicGameInfo(), cartItem.getUser());
            cartItem.setQuantity(cartItem.getQuantity() + operation);
        } else {
            if (operation == 1) {
                cartItem.setQuantity(1);
            } else {
                cartItem.setQuantity(0);
            }
        }
        return cartItem;
    }

    private WishlistItem modifyAndAddWishListItem(WishlistItem wishlistItem, int operation) {
        WishlistItem item = null;
        if (wishlistRepository.findByBasicGameInfoAndUser(wishlistItem.getBasicGameInfo(), wishlistItem.getUser()) != null) {
            if (operation == -1) {
                wishlistRepository.delete(wishlistRepository.findByBasicGameInfoAndUser(wishlistItem.getBasicGameInfo(), wishlistItem.getUser()));
            } else {

            }
        } else {
            if (operation == 1) {
                item = wishlistRepository.save(wishlistItem);
            }
            else{

            }
        }
        return item;
    }

    public String addToOwned() {
        return null;
    }


    public UserInfo createUser(CreateUserDTO userDTO) {
        UserInfo savedUser;
        try {
            UserInfo user = new UserInfo();
            user.setUsername(userDTO.getUsername());
//			Encrypting password for security
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(password);
            user.setAccountCreationDate(LocalDateTime.now().toString().split("\\.")[0]);
            savedUser = userRepository.save(user);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            String userName = userDTO.getUsername();
            if (userRepository.findByUsername(userName) != null) {
                throw new DataIntegrityViolationException(String.format("A User is already associated with the email - %s. Try logging in if that's yours. Else use a different email id", userName));
            }
            throw new DataIntegrityViolationException("Unable to create User with given details");

        }
    }

    public BasicGameInfo storeGameInfo(GameInfoDto gameInfoDto) {
        BasicGameInfo basicGameInfo = mapGameDTOToModel(gameInfoDto);
        BasicGameInfo gameInfo = basicGameInfoRepository.findByRawgId(basicGameInfo.getRawgId());
        if (gameInfo == null) {
            gameInfo = basicGameInfoRepository.save(basicGameInfo);
        }
        return gameInfo;
    }

    public BasicGameInfo mapGameDTOToModel(GameInfoDto gameInfoDto) {
        BasicGameInfo basicGameInfo = new BasicGameInfo();
        basicGameInfo.setRawgId(gameInfoDto.getId());
        basicGameInfo.setName(gameInfoDto.getName());
        basicGameInfo.setBackground_image(gameInfoDto.getBackground_image());
        basicGameInfo.setDescription(gameInfoDto.getDescription());
        basicGameInfo.setRating(gameInfoDto.getRating());
        basicGameInfo.setPlatforms(gameInfoDto.getPlatforms());
        basicGameInfo.setReleased(gameInfoDto.getReleased());
        basicGameInfo.setOtherImages(gameInfoDto.getOtherImages());
        return basicGameInfo;
    }

}
