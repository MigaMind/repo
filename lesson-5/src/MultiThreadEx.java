import java.util.Arrays;

public class MultiThreadEx {
    static final int size = 10000000;
    static final int half = size / 2;
    static float[] arr = new float[size];
    static long a;
    static boolean finished = false;

    public static void main(String[] args) {
        simpleArrMethod();
        halvedArrMethod();
    }

    public static void simpleArrMethod() {
        Arrays.fill(arr, 1);

        a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            doCalculations(i);
        }

        System.out.println(String.format("Simple Array Method is done. Spent time: %d ms", System.currentTimeMillis() - a));
    }

    public static void halvedArrMethod() {
        Arrays.fill(arr, 1);

        float[] firstHalf = new float[half];
        float[] secondHalf = new float[half];

        a = System.currentTimeMillis();

        System.arraycopy(arr, 0, firstHalf, 0, half);
        System.arraycopy(arr, half, secondHalf, 0, half);

        new Thread(() -> halfOfMethod(firstHalf, 1)).start();
        new Thread(() -> halfOfMethod(secondHalf, 2)).start();
    }

    public static void halfOfMethod(float[] halfOfArr, int firstOrSecond) {
        for (int i = 0; i < half; i++) {
            doCalculations(i);
        }
        System.arraycopy(halfOfArr, 0, arr, half * (firstOrSecond - 1), half);

        //System.out.println(String.format("Part #%d is ready. Spent time: %d ms", firstOrSecond, System.currentTimeMillis() - a));

        if (finished) {
            System.out.println(String.format("Halved Array Method is done. Spent time: %d ms", System.currentTimeMillis() - a));
        }
        finished = true;
    }

    private static void doCalculations(int i) {
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
    }
}