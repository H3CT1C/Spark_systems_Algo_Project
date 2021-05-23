import sun.misc.Signal;

public class SignalGenerator {
    private SimpleMovingAverage sma1;
    private SimpleMovingAverage sma2;
    private int currentPosition;
    private Double movingAvg1;
    private Double movingAvg2;
    private AnalyticManager am;

    public SignalGenerator(AnalyticManager Am){
        this.sma1 = am.getSma1();
        this.sma2 = am.getSma2();
        this.am = Am;
    }


}
