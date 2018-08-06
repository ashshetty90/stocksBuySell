package gbs.core;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author leroy
 */
public class StockMarket implements Callable<GBStock> {

    private final List<Integer> price;


    public StockMarket(List<Integer> price) {
        this.price = price;
    }

    @Override
    public GBStock call() throws Exception {
        validate(price);    // check if empty array

        GBStock stockBuySell = setGBSStock(price);  // set low, min price and profit if possible

        return stockBuySell;
    }

    public List<Integer> getPrice() {
        return price;
    }

    /**
     * Iterates over the given array and fetches the minimum and maximum price
     *
     * @param price int[] array of time ordered prices
     * @return GBStock
     */
    public GBStock setGBSStock(List<Integer> price) {
        int minPrice = price.get(0);
        int maxPrice = minPrice;
        boolean canProfit = true;
        for (int i = 0; i < price.size(); i++) {
            int val = price.get(i);
            if (val > maxPrice) {
                maxPrice = val;
            }
            if (val < minPrice) {
                minPrice = val;
            }
        }

        // we will buy only at low price and sell at high
        // also price array size should be greater than 2
        if (!(price.indexOf(minPrice) < price.lastIndexOf(maxPrice)) && price.size() > 1) {
            canProfit = false;
        }
        return new GBStock(minPrice, maxPrice, canProfit);
    }

    private void validate(List<Integer> price) {
        if (price.size() == 0) {
            throw new IllegalArgumentException("Stock Prices passed is Empty.!!");
        }
    }
}
