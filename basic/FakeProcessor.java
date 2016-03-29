package basic;

import interfaces.OnerousProcessor;

/**
 *
 * @author mirko
 */
public class FakeProcessor implements OnerousProcessor {

    public final static int MIN_COUNT = 1000;
    private int max;
private java.util.concurrent.ThreadLocalRandom random;
    public FakeProcessor(int max) {
        this.max = max;

    }

    public int onerousFunction(int value) {
      this.random = java.util.concurrent.ThreadLocalRandom.current();
      int r = this.random.nextInt(this.max);
        int n = Math.max(MIN_COUNT, r); // non meno di MIN_COUNT
// useless but onerous
        for (int counter = 0; counter < n; counter++) {
            r = this.random.nextInt(this.max); // maschera casuale
            r = r ^ this.random.nextInt(this.max); // inverti i bit
            r = r ^ this.random.nextInt(this.max); // due volte
        }
        return value; // n.b. value risulta invariato
    }
}
