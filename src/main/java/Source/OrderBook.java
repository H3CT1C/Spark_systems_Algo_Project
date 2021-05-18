package Source;
import java.math.BigDecimal;
import java.util.Map;
import java.util.NavigableMap;

public class OrderBook {
    private Map<String,NavigableMap<BigDecimal,BigDecimal>> orderBook;

    public NavigableMap<BigDecimal,BigDecimal> getAsks(){
        return orderBook.get("ASKS");
    }
    public NavigableMap<BigDecimal,BigDecimal> getBids(){
        return orderBook.get("BIDS");
    }

    public void place (String string, NavigableMap<BigDecimal, BigDecimal> map) {
        orderBook.put(string, map);
    }


}
