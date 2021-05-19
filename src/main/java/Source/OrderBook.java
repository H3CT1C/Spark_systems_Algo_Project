package Source;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;

public class OrderBook {
    private Map<String,NavigableMap<BigDecimal,BigDecimal>> orderBook = new HashMap<>();

    public NavigableMap<BigDecimal,BigDecimal> getAsks(){
        return orderBook.get("ASKS");
    }
    public NavigableMap<BigDecimal,BigDecimal> getBids(){
        return orderBook.get("BIDS");
    }

    public void put (String string, NavigableMap<BigDecimal, BigDecimal> map) {
        orderBook.put(string, map);
    }

    public Map<String,NavigableMap<BigDecimal,BigDecimal>> getOrderBookCache(){
        return orderBook;
    }


}
