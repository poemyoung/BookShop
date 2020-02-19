package com.xzp.entity;

public class Items {
    public int getOrdered() {
        return orderId;
    }

    public void setOrdered(int ordered) {
        this.orderId = ordered;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    private int orderId = 0;
    private int bookId = 0;
    private int count = 0;
    private String price = null;
    private String total_price = null;
}
