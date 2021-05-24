import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.binance.api.client.domain.event.DepthEvent;

public class EventBroker<T>{
   private BlockingQueue<T> eventQueue = new ArrayBlockingQueue<>(1024);

    public void addEvent(T event) throws InterruptedException {
        eventQueue.put(event);
    }

    public T get() throws InterruptedException{
        return eventQueue.take();
    }

    public BlockingQueue<T> getEventQueue(){
        return eventQueue;
    }
    }

