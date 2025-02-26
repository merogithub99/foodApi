package com.sushant.service;

import com.sushant.model.CardItem;
import com.sushant.model.Cart;
import com.sushant.request.AddCartItemRequest;

public interface CartService {

    public CardItem addItemToCart(AddCartItemRequest req , String jwt) throws  Exception;

    public CardItem updateCardItemQuantity(Long cardItemId,int quantity) throws  Exception;

    public Cart removeItemFromCart(Long cardItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws  Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws  Exception;
}
