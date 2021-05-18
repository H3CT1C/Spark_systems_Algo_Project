
import java.util.ArrayList;
import java.util.List;

import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.OrderBook;


public class EventManager{
    private EventBroker<Source.OrderBook> orderBookBroker = new EventBroker<>();
    private EventBroker<SchedulerManager> scheduleQueue = new EventBroker<>();

    public void publish(Source.OrderBook orderBook) throws InterruptedException{
        orderBookBroker.addEvent(orderBook);
        orderBookBroker.broadcast();
    }
    public void addListener(EventListener listener){
        orderBookBroker.addListener(listener);
        scheduleQueue.addListener(listener);
    }
}
