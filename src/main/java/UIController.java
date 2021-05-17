import com.binance.api.client.exception.BinanceApiException;

public class UIController {
    private final UiUtil ui;

    public UIController(){
        this.ui = new UiUtil();
    }

public void run(){
        ui.print("Enter currency symbol:");
        try{
            String symbol = ui.readInput();
            ui.print("Enter 1 for trades, 2 for orderbooks");
            String choice = ui.readInput();
            int choice1 = Integer.parseInt(choice);
            if(choice1 == 1){
                MarketDataManager mdm = new MarketDataManager();
                mdm.subscribeTrades(symbol);}

            else if (choice1 == 2){
                MarketDataManager mdm = new MarketDataManager();
                mdm.subscribeOrderBook(symbol);
            }


        }catch (BinanceApiException e){
            ui.print(e.getMessage());
            ui.close();
        }

}
}
