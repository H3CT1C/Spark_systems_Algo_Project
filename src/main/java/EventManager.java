
import java.util.ArrayList;
import java.util.List;

import Source.OrderBook;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.market.AggTrade;


public class EventManager{
    private EventBroker<Source.OrderBook> orderBookBroker = new EventBroker<>();
    private EventBroker<ScheduleEvent> scheduleQueue = new EventBroker<>();
    private EventBroker<AggTradeEvent> aggTradesBroker = new EventBroker<>();

    //remove broadcasts later on!
    public void publish(Source.OrderBook orderBook) throws InterruptedException{
        System.out.println(orderBook.getBids());
        orderBookBroker.addEvent(orderBook);

    }

    public void publish(AggTradeEvent aggTradeEvent) throws InterruptedException{
        aggTradesBroker.addEvent(aggTradeEvent);

    }

    public void publish (ScheduleEvent timer) throws InterruptedException{
        scheduleQueue.addEvent(timer);
    }

    public EventBroker getOrderBookBroker() {
        return orderBookBroker;
    }

    public EventBroker getSchedulerBroker(){
        return scheduleQueue;
    }

    public void addListener(EventListener listener){
        orderBookBroker.addListener(listener);
        scheduleQueue.addListener(listener);
    }

}
