import com.binance.api.client.domain.event.AllMarketTickersEvent;

import java.util.HashMap;
import java.util.Map;
public class MarketDataManager implements Runnable{
    private String symbol;
    private BinanceConnector binanceConnector;
    EventManager eventManager = new EventManager();

    public MarketDataManager (String symbol, EventManager eventManager) {
        this.symbol = symbol;
        this.eventManager = eventManager;
        binanceConnector = new BinanceConnector(symbol);
    }
    public void subscribeOrderBook(){
        binanceConnector.startOrderBookEventStreaming(symbol, eventManager);
    }



    @Override
    public void run() {
        subscribeOrderBook();
    }

}
