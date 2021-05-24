import jdk.jfr.Event;
import org.quartz.SchedulerException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main (String[] args) throws SchedulerException {
        //debug and stop at certain lines
    EventManager eventManager = new EventManager();
    MarketDataManager marketDataManager = new MarketDataManager("ETHBTC", eventManager);
    SchedulerManager schedulerManager = new SchedulerManager(eventManager);
    AnalyticManager analyticManager = new AnalyticManager(eventManager,schedulerManager);
    CrossOverManager crossOverManager = new CrossOverManager(5, 10, 100.0);
    analyticManager.addListener(crossOverManager);

    ExecutorService threadpool = Executors.newFixedThreadPool(3);
    threadpool.execute(marketDataManager);
    threadpool.execute(analyticManager);

    }
}
