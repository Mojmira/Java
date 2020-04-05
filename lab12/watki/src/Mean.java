import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    private static double Mean=0;

    static void initArray(int size){
        new Random().setSeed(123);
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
        double m=0;
        for(int i=0;i<size;i++)m+=array[i];
        System.out.println(m/size);
    }


    static void parallelMean(int cnt) throws InterruptedException {
        MeanCalc threads[]=new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)

        for(int i=0; i<cnt ;i++)
        {
            int partLenght=array.length/cnt;
            threads[i]=new MeanCalc(i*partLenght,(i+1)*partLenght);
        }
        double t1 = System.nanoTime()/1e6;

        for(int i=0; i<cnt ;i++)
        {
            threads[i].start();
        }

        double t2 = System.nanoTime()/1e6;
        // czekaj na ich zakończenie używając metody ''join''
//        for(MeanCalc mc:threads) {
//            mc.join();
//        }

        /*for(MeanCalc mc:threads) {
            Mean+=mc.mean;
        }
            Mean/=cnt;*/

        for(int i=0; i<cnt ;i++)
        {
            Mean+=results.take();
        }
        Mean/=cnt;

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                Mean);
    }

    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);


    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            for(int i=start;i<end;i++)
            {
                mean+=array[i];
            }
            mean/=(end-start);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }






    public static void main(String[] args) throws InterruptedException {
        initArray(128_000_000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
//        for(int cnt:new int[]{4,8}){
            parallelMean(cnt);
        }
    }
}