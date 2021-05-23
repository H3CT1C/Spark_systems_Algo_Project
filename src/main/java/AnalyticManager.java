import java.util.NavigableMap;
import java.util.TreeMap;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.quartz.SchedulerException;
import Source.OrderBook;

public class AnalyticManager extends EventListener implements Runnable {
    private SimpleMovingAverage sma1;
    private SimpleMovingAverage sma2;
    private EventBroker orderBookBroker;
    private EventBroker scheduleBroker;
    private NavigableMap<Long,OrderBook> orderBookCache = new TreeMap<>();
    private long orderBookId = 0L;
    private SchedulerManager schedulerManager;

    public AnalyticManager(int window1, int window2, EventManager EM, SchedulerManager SM){
        sma1 = new SimpleMovingAverage(window1);
        sma2 = new SimpleMovingAverage(window2);
        orderBookBroker = EM.getOrderBookBroker();
        scheduleBroker = EM.getSchedulerBroker();
        this.schedulerManager = SM;
        SignalGenerator signalGenerator = new SignalGenerator(this);
    }

    protected void initialize(){
        try{
            schedulerManager.periodicCallBack(500,"sma1");
            schedulerManager.periodicCallBack(1000,"sma2");

        }catch(SchedulerException e ) {
        e.printStackTrace();
        }
        }

    public void handleEvent(Source.OrderBook orderBook) throws InterruptedException {
        orderBookCache.put(orderBookId++, orderBook);
    }

    public void handlEvent(ScheduleEvent timer) throws InterruptedException{
        if(timer.getTag().equals("sma1")){
            sma1.updateRecentPrices(orderBookCache);
        }else if(timer.getTag().equals("sma2"));{
            sma2.updateRecentPrices(orderBookCache);
        }
        //signalGenerator.generateSignal();
    }

    public NavigableMap<Long, OrderBook> getOrderBookCache(){
        return orderBookCache;
    }

    public SimpleMovingAverage getSma1(){
        return sma1;
    }

    public SimpleMovingAverage getSma2(){
        return sma2;
    }

    public void run(){
        initialize();
        while(true){
            try{
                handleEvent((OrderBook) orderBookBroker.get() );
                handlEvent((ScheduleEvent) scheduleBroker.get());
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    }

    // print the order book at once every 5 and 10 seconds, for scheduleEvent
    // on a separate thread from the binance thread


