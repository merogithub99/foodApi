package com.sushant.service;

import com.sushant.model.CardItem;
import com.sushant.model.Cart;
import com.sushant.model.Food;
import com.sushant.model.User;
import com.sushant.request.AddCartItemRequest;
import com.sushant.respository.CardItemRepository;
import com.sushant.respository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private CardItemRepository cardItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CardItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CardItem cardItem : cart.getItems()) {
            if (cardItem.getFood().equals(food)) {
                int newQuantity = cardItem.getQuantity() + req.getQuantity();
                return updateCardItemQuantity(cardItem.getId(), newQuantity);
            }
        }

        CardItem newCardItem = new CardItem();
        newCardItem.setFood(food);
        newCardItem.setCart(cart);
        newCardItem.setQuantity(req.getQuantity());
        newCardItem.setIngredients(req.getIngredients());
        newCardItem.setTotalPrice(req.getQuantity() * food.getPrice());

        CardItem savedCardItem = cardItemRepository.save(newCardItem);

        cart.getItems().add(savedCardItem);

        return savedCardItem;
    }

    @Override
    public CardItem updateCardItemQuantity(Long cardItemId, int quantity) throws Exception {

        Optional<CardItem> cardItemOptional = cardItemRepository.findById(cardItemId);

        if (cardItemOptional.isEmpty()) {
            throw new Exception("card item not exist not found");
        }
        CardItem item = cardItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);

        return cardItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cardItemId, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CardItem> cardItemOptional = cardItemRepository.findById(cardItemId);

        if (cardItemOptional.isEmpty()) {
            throw new Exception("card item not exist not found");
        }
        CardItem item=cardItemOptional.get();
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
      Long total =0L;
      for (CardItem cartItem : cart.getItems()){
          total+=cartItem.getFood().getPrice()*cartItem.getQuantity();

      }
      return  total;

    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw new Exception("cart not found with id "+id);
        }
        return optionalCart.get();
    }

    //6 53 min

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(userId);

        Cart cart= cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
