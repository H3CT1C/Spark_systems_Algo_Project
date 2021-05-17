import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.OrderBook;

public abstract class EventListener {

    void handleEvent(OrderBook orderBook){}

    //void handleEvent(ScheduleEvent timer){}
}
