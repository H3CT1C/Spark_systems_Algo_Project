import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import Source.OrderBook;
import com.binance.api.client.domain.account.Order;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import Source.OrderBook;
public class SimpleMovingAverage {
    private int window;
    private Double[] recentPrices;
    private int pointer = 0;
    DescriptiveStatistics stats = new DescriptiveStatistics(3);


    public SimpleMovingAverage(int window){
        this.window = window;
        recentPrices = new Double[window];
    }
    public Double getWeightedMid(OrderBook orderBook){
        Double bestBidPrice= orderBook.getBestBid().getKey().doubleValue();
        Double bestBidQuantity= orderBook.getBestBid().getValue().doubleValue();
        Double bestAskPrice = orderBook.getBestAsk().getKey().doubleValue();
        Double bestAskQuantity = orderBook.getBestAsk().getValue().doubleValue();
        Double weightedMid = (bestBidPrice * bestBidQuantity + bestAskPrice *bestAskQuantity)/(bestBidQuantity+bestAskQuantity);
        return weightedMid;
    }

   public Double getSimpleMovingAverage(NavigableMap< Long, OrderBook> orderBookCache) throws InterruptedException{
        OrderBook latestOrderBookPrice = orderBookCache.lastEntry().getValue();
        Double weightedPrice = getWeightedMid(latestOrderBookPrice);
        stats.addValue(weightedPrice);
        return stats.getMean();
   }


}
