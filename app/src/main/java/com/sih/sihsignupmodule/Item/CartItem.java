package com.sih.sihsignupmodule.Item;

public class CartItem {

    private int cartItemImg;
    private String cartItemName , cartItemPrice , cartItemQuanity;

    public CartItem(int imageResource, String itemname,String itemprice,String itemquantity){
        cartItemImg=imageResource;
        cartItemName=itemname;
        cartItemPrice=itemprice;
        cartItemQuanity=itemquantity;
    }

    public int getCartItemImg(){
        return cartItemImg;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public String getCartItemPrice() {
        return cartItemPrice;
    }

    public String getCartItemQuanity() {
        return cartItemQuanity;
    }

}

