package front.dao;

import entity.UserCart;

import java.util.List;

public interface UserCartDao {
    public boolean AddItemCart(UserCart userCart);

    public boolean DelItemCart(UserCart userCart);

    public List<UserCart> UserCartList(int userId);

    public int ItemIsInCart(UserCart userCart);

    public boolean UpdateItemCartCount(UserCart userCart, int id);

    public Boolean FindUserCartByUserId(int userCartId);


    public UserCart FindUserCartById(UserCart userCart);
}


