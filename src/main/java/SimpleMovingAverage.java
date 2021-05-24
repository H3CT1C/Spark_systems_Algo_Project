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
        DescriptiveStatistics stats = new DescriptiveStatistics(window);
    }
    public Double getMid(OrderBook orderBook){
        Double bestBidPrice= orderBook.getBestBid().getKey().doubleValue();
        Double bestAskPrice = orderBook.getBestAsk().getKey().doubleValue();
        Double bestBidQuantity =  orderBook.getBestBid().getValue().doubleValue();
        Double bestAskQuantity = orderBook.getBestAsk().getValue().doubleValue();
        Double Mid = (bestBidPrice * bestBidQuantity  + bestAskPrice * bestAskQuantity )/(bestAskQuantity+bestBidQuantity);
        return Mid;
    }
   public void updateRecentPrices(NavigableMap<Long,OrderBook> orderBookCache) throws InterruptedException{
        OrderBook latestOrderBook = orderBookCache.lastEntry().getValue();
        recentPrices[pointer] = getMid(latestOrderBook);
        if(pointer >= window -1){
            pointer =0;}
        else{
            pointer++;
        }

   }

   public Double getSimpleMovingAverage(){
       Double totalPrice = 0.0;
       int count = 0;
       for(Double price: recentPrices){
           if(price == null){
               System.out.println("Not enough data!");
               return 0.0;

           }
           totalPrice+= price;
           count++;
       }
       Double movingAvg = totalPrice/count;
       System.out.println("SMA" + window + ": " + movingAvg);
       return movingAvg;

   }


}
