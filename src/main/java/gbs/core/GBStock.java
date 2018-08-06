package gbs.core;

public class GBStock {
    public int buyPrice;
    public int sellPrice;
    public boolean canProfit;

    public GBStock(int buyPrice, int sellPrice, boolean canProfit) {
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.canProfit = canProfit;
    }
}
