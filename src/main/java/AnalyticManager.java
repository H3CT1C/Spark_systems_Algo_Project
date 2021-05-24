import java.util.*;

import java.util.EventListener;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.quartz.SchedulerException;
import Source.OrderBook;

public class AnalyticManager implements EventListener, Runnable {
    private EventBroker orderBookBroker;
    private EventBroker scheduleBroker;
    public  List<EventListeners> listeners = new ArrayList<>();
    private SchedulerManager schedulerManager;

    public AnalyticManager(EventManager eventManager, SchedulerManager schedulerManager){
        orderBookBroker = eventManager.getOrderBookBroker();
        scheduleBroker = eventManager.getSchedulerBroker();
        this.schedulerManager = schedulerManager;
    }

    public void addListener(EventListeners listener){
        listeners.add(listener);
    }

    public void broadcast(OrderBook orderBook) throws InterruptedException{
        for(EventListeners listener : listeners){
            listener.handleEvent(orderBook);
        }
    }
    public void broadcast(ScheduleEvent timer) throws InterruptedException{
        for(EventListeners listener : listeners){
            listener.handleEvent(timer);
        }
    }

    public void handleEvent(OrderBook orderBook) throws InterruptedException{
        broadcast(orderBook);
    }

    public void handleEvent(ScheduleEvent timer) throws InterruptedException{
        broadcast(timer);
    }
    public void initialize(){
        try{
            schedulerManager.periodicCallBack(500,"sma1");
            schedulerManager.periodicCallBack(500,"sma2");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        initialize();
        while(true){
            try{
                handleEvent((OrderBook) orderBookBroker.get());
                handleEvent((ScheduleEvent) scheduleBroker.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }




