import java.util.Scanner;

public class Problem610A {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int counter = 0;
        int min=0, max=n/2;

        while(min<max)
        {
            max=n/2;
            min++;
            max-=min;
            counter++;
        }counter--;

        System.out.println(counter);


    }
}
