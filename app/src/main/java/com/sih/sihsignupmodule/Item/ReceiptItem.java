package com.sih.sihsignupmodule.Item;

public class ReceiptItem {


    private int receiptItemNumber;
    private String receiptItemName , receiptItemPrice , receiptItemQuantity;

    public ReceiptItem(int receiptitemnumber, String receiptitemname,String receiptitemprice,String receiptitemquantity){
        receiptItemNumber=receiptitemnumber;
        receiptItemName=receiptitemname;
      receiptItemPrice=receiptitemprice;
      receiptItemQuantity=receiptitemquantity;
    }

    public int getReceiptItemNumber(){
        return receiptItemNumber;
    }

    public String getReceiptItemName() {
        return receiptItemName;
    }

    public String getReceiptItemPrice() {
        return receiptItemPrice;
    }

    public String getReceiptItemQuantity() {
        return receiptItemQuantity;
    }


}
