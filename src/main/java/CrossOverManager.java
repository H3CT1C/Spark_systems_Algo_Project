import Source.OrderBook;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CrossOverManager implements EventListeners {
    private SimpleMovingAverage sma1;
    private SimpleMovingAverage sma2;
    private Double threshold;
    private NavigableMap<Long, OrderBook> orderBookCache = new TreeMap<>();
    private long orderBookId = 0L;
    private SignalGenerator signalGenerator;

    public CrossOverManager(int window1, int window2, Double threshold){
        sma1 = new SimpleMovingAverage(window1);
        sma2 = new SimpleMovingAverage(window2);
        this.threshold = threshold;
        signalGenerator= new SignalGenerator(this);
    }
    public void handleEvent(OrderBook orderBook) throws InterruptedException{
        orderBookCache.put(orderBookId++,orderBook);
    }

    public void handleEvent(ScheduleEvent timer) throws InterruptedException{
        if(timer.getTag().equals("sma1")){
            sma1.updateRecentPrices(orderBookCache);
        }else if (timer.getTag().equals("sma2")) {
            sma2.updateRecentPrices(orderBookCache);
        }
        signalGenerator.generateSignal(threshold);
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
}
