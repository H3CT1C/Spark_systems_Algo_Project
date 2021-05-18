import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.binance.api.client.domain.event.DepthEvent;

public class EventBroker<T>{
    private BlockingQueue<T> eventQueue = new ArrayBlockingQueue(1024);
    private List<EventListener> listenerList = new ArrayList<>();

    public void addEvent(T event) throws InterruptedException{
        eventQueue.put(event);
    }

    public void broadcast() throws InterruptedException{
        if(eventQueue.isEmpty()){
            System.out.println("No events to broadcast");
        }
        else{
            while(!eventQueue.isEmpty()){
                T event = eventQueue.remove();
                sendToListeners(event);
            }
        }
    }
    public void sendToListeners(T event) throws InterruptedException{
        for (EventListener listener : listenerList){
            if (event instanceof DepthEvent){
                listener.handleEvent((DepthEvent) event);
            }
        }

    }
    public void addListener(EventListener listener){
        if(!listenerList.contains(listener)){
            listenerList.add(listener);
        }
        else{
            System.out.println("Listener already in list!");
        }
    }
    public void removeListener(EventListener listener){
        if(listenerList.contains(listener)) {
            listenerList.remove(listener);
        }
        else{
            System.out.println("listener not in list");
        }
        }
    }
