package com.example.ig.controller;

public class dashboardGoods {
    private int imagePathLeft;
    private String goodsIntroductionLeft;
    private String priceLeft;
    private String recordLeft;
    private int imagePathRight;
    private String goodsIntroductionRight;
    private String priceRight;
    private String recordRight;

    public dashboardGoods(int imagePathLeft, String goodsIntroductionLeft, String priceLeft, String recordLeft,
                          int imagePathRight, String goodsIntroductionRight, String priceRight, String recordRight) {
        this.imagePathLeft = imagePathLeft;
        this.goodsIntroductionLeft = goodsIntroductionLeft;
        this.priceLeft = priceLeft;
        this.recordLeft = recordLeft + "人兑换";
        this.imagePathRight = imagePathRight;
        this.goodsIntroductionRight = goodsIntroductionRight;
        this.priceRight = priceRight;
        this.recordRight = recordRight + "人兑换";
    }

    public int getImagePathLeft() {
        return imagePathLeft;
    }

    public int getImagePathRight() {
        return imagePathRight;
    }

    public String getGoodsIntroductionLeft() {
        return goodsIntroductionLeft;
    }

    public String getGoodsIntroductionRight() {
        return goodsIntroductionRight;
    }

    public String getPriceLeft() {
        return priceLeft;
    }

    public String getPriceRight() {
        return priceRight;
    }

    public String getRecordLeft() {
        return recordLeft;
    }

    public String getRecordRight() {
        return recordRight;
    }
}
