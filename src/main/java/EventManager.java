
import java.util.ArrayList;
import java.util.List;

import Source.OrderBook;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.market.AggTrade;


public class EventManager{
    private EventBroker<Source.OrderBook> orderBookBroker = new EventBroker<>();
    private EventBroker<ScheduleEvent> scheduleQueue = new EventBroker<>();


    public void publish(Source.OrderBook orderBook) throws InterruptedException{
        orderBookBroker.addEvent(orderBook);

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



}
