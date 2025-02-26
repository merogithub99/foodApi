package com.sushant.controller;

import com.sushant.model.CardItem;
import com.sushant.model.Cart;
import com.sushant.model.User;
import com.sushant.request.AddCartItemRequest;
import com.sushant.request.UpdateCartItemRequest;
import com.sushant.service.CartService;
import com.sushant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
   private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CardItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        CardItem cardItem = cartService.addItemToCart(req, jwt);
        return  new ResponseEntity<>(cardItem, HttpStatus.OK);
    }


    @PutMapping("/cart-item/update")
    public ResponseEntity<CardItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        CardItem cardItem = cartService.updateCardItemQuantity(req.getCartItemId(), req.getQuantity());
        return  new ResponseEntity<>(cardItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCardItem(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id,jwt);
        return  new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return  new ResponseEntity<>(cart, HttpStatus.OK);
    }

 @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user= userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return  new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
