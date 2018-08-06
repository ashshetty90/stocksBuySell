package gbs;

import gbs.core.GBStock;
import gbs.core.StockMarket;
import gbs.exception.SystemUnhandledException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static gbs.utils.Utils.*;

/**
 * @author leroy
 * Application checks the price list passed and finds
 * Stock Buy price
 * Stock Sell price
 * Max Profit
 */
public class App {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        List<Integer> priceList = parseStockPrices(args[0]);

        int actualSize = priceList.size();

        int chunkSize = Integer.parseInt(args[1]);

        int maxChunkSize = actualSize / chunkSize;

        if (actualSize % chunkSize == 0) {
            --maxChunkSize;
        }
        int head = 0;
        int tail = head + chunkSize;

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<GBStock>> results = new ArrayList<>();

        System.out.println(CHECK_MAX);

        for (int i = 0; i <= maxChunkSize; i++) {
            if (tail > actualSize) {
                tail = actualSize;
            }

            Future<GBStock> future = executorService.submit(new StockMarket(priceList.subList(head, tail)));
            results.add(future);

            int temp = tail;
            tail = chunkSize + tail;
            head = temp;
        }
        int finalMax = 0;
        int finalMin = 0;
        int maxProfit = 0;
        try {
            int i = 0;
            for (Future<GBStock> future : results) {
                GBStock stock = future.get();
                if (stock.canProfit) {
                    if (i == 0) {
                        finalMax = stock.sellPrice;
                        finalMin = stock.buyPrice;
                        maxProfit = finalMax - finalMin;
                        i = 1;
                    } else {
                        if (stock.sellPrice - finalMin > maxProfit) {
                            maxProfit = stock.sellPrice - finalMin;
                            finalMax = stock.sellPrice;
                        }
                        if (stock.buyPrice < finalMin) {
                            finalMin = stock.buyPrice;
                        }
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new SystemUnhandledException(ERROR_SYS_INTERRUPT, e);
        } catch (Exception e) {
            throw new SystemUnhandledException(ERROR_MESSAGE, e);
        }

        if (maxProfit > 0) {
            printProfit(finalMax, finalMin);
        } else {
            System.out.println(NO_PROFIT);
        }

        System.out.println(DONE);

        System.out.println("Took " + (System.currentTimeMillis() - startTime) + " ms");

        executorService.shutdown();
    }


}
