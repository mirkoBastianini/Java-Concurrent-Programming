package main;

/**
 *
 * @author mirko
 */
public class HWJ {

    public static void main(String[] args) throws InterruptedException {
        warmUpAll();
        System.out.println("------------ START HWJ1 ------------");
        HWJ1.main(null);
        System.out.println();
        System.out.println("------------ START HWJ2 ------------");
        HWJ2.main(null);
        System.out.println();
         System.out.println("------------ START HWJ3 ------------");
        HWJ3.main(null);
        System.out.println();
    }
    
     public static void warmUpAll() throws InterruptedException {
        System.out.println("--------------------------------------------------------------");
        System.out.println("WARM UP ALL");

        for (int i = 0; i <= 10; i++) {
            System.out.println("Loading: " + i*10 + "% ");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Loading: Done!");
        System.out.println("--------------------------------------------------------------");

    }
}
