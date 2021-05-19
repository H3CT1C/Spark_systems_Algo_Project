import jdk.jfr.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main (String[] args) {
    EventManager eventManager = new EventManager();
    MarketDataManager marketDataManager = new MarketDataManager("ETHBTC", eventManager);
    marketDataManager.subscribeOrderBook();

    }
}
