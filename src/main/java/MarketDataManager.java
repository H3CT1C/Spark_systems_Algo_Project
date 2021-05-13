import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;

public class MarketDataManager {

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiRestClient client = factory.newRestClient();
        TickerStatistics tickerStatistics = client.get24HrPriceStatistics("BTCBUSD");
        System.out.println(tickerStatistics);

        TickerPrice price = client.getPrice("BTCBUSD");
        System.out.println(price);

    }
}
