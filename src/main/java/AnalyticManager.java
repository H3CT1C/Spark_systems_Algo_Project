import java.util.NavigableMap;
import java.util.TreeMap;

import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.OrderBook;

public class AnalyticManager extends EventListener {
    protected NavigableMap<Long, OrderBook> orderBookCache = new TreeMap<>();
    private long orderBookId = 0l;
    protected NavigableMap<Long, DepthEvent> depthMapCache = new TreeMap<>();
    private long depthId = 0l;

    public void handleEvent(DepthEvent depthEvent){
        depthMapCache.put(depthId++, depthEvent);
    }

    public void handleEvent(OrderBook orderBook){
        orderBookCache.put(orderBookId++, orderBook);
    }
}
