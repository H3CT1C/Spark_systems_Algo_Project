import sun.misc.Signal;

public class SignalGenerator {
    private SimpleMovingAverage sma1;
    private SimpleMovingAverage sma2;
    private int currentPosition;
    private Double movingAvg1;
    private Double movingAvg2;
    private CrossOverManager am;

    public SignalGenerator(CrossOverManager am){
        this.sma1 = am.getSma1();
        this.sma2 = am.getSma2();
        this.am = am;
    }

public void generateSignal(Double threshold){
        movingAvg1 = sma1.getSimpleMovingAverage();
        movingAvg2 = sma2.getSimpleMovingAverage();
        System.out.println(currentPosition);
        if(movingAvg2 ==0 || movingAvg1 == 0) {
            currentPosition = 0;
        }else if(currentPosition ==0){
            currentPosition = 1;
        }else if(movingAvg2 > movingAvg1 + threshold){
            currentPosition = -1;
    }   else if(currentPosition == 1){
            if(movingAvg2 > movingAvg1 + threshold){
                System.out.println("Sell!");
            }
        }
        else{
            if(movingAvg1> movingAvg2+ threshold){
                System.out.println("Buy!");
            }
        }
        }
        protected SimpleMovingAverage getSma1(){
        return sma1;
        }
         protected SimpleMovingAverage getSma2(){
        return sma2;
    }
    protected int getCurrentPosition(){
        return currentPosition;
    }
}

