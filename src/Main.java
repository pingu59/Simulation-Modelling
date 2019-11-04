public class Main {
    public static void main(String[] args) {
        Cache random = new RandomCache(20, 10,  1000L * 1000 * 1000);
        random.simulate();
        System.out.println("RANDOM : " + random.getMissRate());
        Cache fifo = new FIFOCache(20, 10,  1000L * 1000 * 1000);
        fifo.simulate();
        System.out.println("FIFO : " + fifo.getMissRate());
    }
}
