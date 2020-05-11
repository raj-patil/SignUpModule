package com.sih.sihsignupmodule.Item;

public class shopItem {
    private int mImageResource;
    private String mText1,mText2,mText3;

    public shopItem( String productname, String productprice ){
       // mImageResource=imageResource;
        mText1=productname;
        mText2=productprice;

    }

  //  public int getmImageResource(){
  //      return mImageResource;
  //  }

    public String getproductname() {
        return mText1;
    }

    public String getproductprice() {
        return mText2;
    }



}



