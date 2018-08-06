package gbs.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author leroy
 */
public class StockMarketTest {

    @Test
    public void test_validPrice() {
        StockMarket market = new StockMarket(Arrays.asList(1, 2, 3, 4, 5));
        GBStock stock = market.setGBSStock(market.getPrice());

        Assert.assertEquals(1, stock.buyPrice);
        Assert.assertEquals(5, stock.sellPrice);
        Assert.assertTrue(stock.canProfit);
    }

    @Test
    public void test_InValidPrice() {
        StockMarket market = new StockMarket(Arrays.asList(5, 4, 3, 2, 1));
        GBStock stock = market.setGBSStock(market.getPrice());

        Assert.assertFalse(stock.canProfit);
    }

}