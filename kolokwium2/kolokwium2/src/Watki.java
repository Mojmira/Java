import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Watki {

    int tablica[];
    int NowaTablica[];

    Watki() {
        tablica = new int[100000000];
        NowaTablica=new int[100000000];
        for (int i = 0; i < 100000000; i++)
            tablica[i] = i;
    }


    void OdwracanieTablicy(int cnt) throws InterruptedException {
        Odwroc threads[]=new Odwroc[cnt];
        int partLenght=tablica.length/cnt;
        for(int i=0; i<cnt ;i++)
        {

            threads[i]=new Odwroc(i*partLenght,(i+1)*partLenght);
        }
        double t1 = System.nanoTime()/1e6;
        for(int i=0; i<cnt ;i++)
        {
            threads[i].start();

        }
        double t2 = System.nanoTime()/1e6;
        for(int i=cnt-1; i>=0 ;i--)
        {
            int[] tmp=results.take();
            for(int j=0;j<partLenght;j++)
                NowaTablica[j+i*partLenght]=tmp[j];


        }
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US," t2-t1=%f t3-t1=%f \n", t2-t1, t3-t1);

    }

    static BlockingQueue<int[]> results = new ArrayBlockingQueue<int[]>(100);

    class Odwroc extends Thread {
        int start;
        int end;
        int [] subTablica;

        Odwroc(int start, int end) {
            this.start = start;
            this.end = end;
            subTablica=new int[end-start];
        }

        public void run(){
            for(int i=start;i<end;i++)
            {
                subTablica[subTablica.length-(i-start)-1]=tablica[i];

            }
            try {
                results.put(subTablica);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Watki x =new Watki();
        x.OdwracanieTablicy(100);
        for(int i=0;i<100;i++)
        {
            System.out.println(x.NowaTablica[i]);
        }
    }
}