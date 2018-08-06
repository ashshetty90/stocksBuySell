package gbs.utils;

import gbs.exception.FileInputException;
import gbs.exception.InvalidFileException;
import gbs.exception.SystemUnhandledException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leroy
 */
public class Utils {

    public static final String ERROR_MESSAGE = "Something went wrong in execution.\r\nPlease try again";
    public static final String ERROR_SYS_INTERRUPT = "System interruption detected.\r\nPlease try again";
    public static final String NO_PROFIT = "No profit made";
    public static final String FILE_NOT_FOUND = "File invalid/not found.";
    public static final String ERROR_FILE_READ = "Error while reading file.\r\nFile should contain comma separated price";
    public static final String DONE = "Done.!!";
    public static final String CHECK_MAX = "Checking max profit if possible...";

    public static List<Integer> parseStockPrices(String file) {
        String line;
        List<Integer> prices = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                prices = Arrays.asList(line.split(","))
                        .stream()
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
            }
        } catch (FileNotFoundException e) {
            throw new InvalidFileException(FILE_NOT_FOUND, e);
        } catch (IOException e) {
            throw new FileInputException(ERROR_FILE_READ, e);
        } catch (Exception e) {
            throw new SystemUnhandledException(ERROR_MESSAGE, e);
        }
        return prices;
    }

    public static void printProfit(int finalmax, int finalmin) {
        System.out.println(
                "\r\nStock buy price = " + finalmin + "\r\n" +
                        "Stock sell price = " + finalmax + "\r\n" +
                        "Max profit = " + (finalmax - finalmin) + "\r\n");
    }
}
