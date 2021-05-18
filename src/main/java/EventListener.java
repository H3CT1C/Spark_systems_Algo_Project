import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.market.OrderBook;

public abstract class EventListener {

    void handleEvent(DepthEvent depthEvent){}

    //void handleEvent(ScheduleEvent timer){}
}
