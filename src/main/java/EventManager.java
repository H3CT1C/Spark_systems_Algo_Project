
import java.util.ArrayList;
import java.util.List;

import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.OrderBook;


public class EventManager{
    private EventBroker<Source.OrderBook> orderBookBroker = new EventBroker<>();
    private EventBroker<ScheduleEvent> scheduleQueue = new EventBroker<>();
    private EventBroker<AggTradeEvent> aggTradesBroker = new EventBroker<>();

    public void publish(Source.OrderBook orderBook) throws InterruptedException{
        orderBookBroker.addEvent(orderBook);
        orderBookBroker.broadcast();
    }

    public void publish(AggTradeEvent aggTradeEvent) throws InterruptedException{
        aggTradesBroker.addEvent(aggTradeEvent);
        aggTradesBroker.broadcast();
    }

    public void publish (ScheduleEvent timer) throws InterruptedException{
        scheduleQueue.addEvent(timer);
    }
    public void addListener(EventListener listener){
        orderBookBroker.addListener(listener);
        scheduleQueue.addListener(listener);
    }

}
