import Source.OrderBook;

public interface EventListeners {

void handleEvent(OrderBook orderBook) throws InterruptedException;

void handleEvent(ScheduleEvent timer) throws InterruptedException;
}
