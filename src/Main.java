public class Main {
    public static void main(String[] args) {
        //test for 10 times each
        int TIME = 10;
        double[] rs = new double[TIME];
        double[] fs = new double[TIME];
        double meanf = 0;
        double meanr = 0;
        double varf = 0;
        double varr = 0;
        Cache fifo = new FIFOCache(20, 10,  1000L * 1000 * 1000);
        Cache random = new RandomCache(20, 10,  1000L * 1000 * 1000);
        for(int i = 0; i < TIME; i++) {
            rs[i] = random.simulate();
            fs[i] = fifo.simulate();
            meanf += fs[i];
            meanr += rs[i];
        }

        meanf /= 10;
        meanr /= 10;

        for(int i = 0; i < TIME; i++){
            varf += Math.pow(fs[i] - meanf, 2);
            varr += Math.pow(fs[i] - meanr, 2);
        }

        //t9, alpha = 0.95
        double tdis = 1.83;
        double halff = tdis * Math.sqrt(varf) / Math.sqrt(TIME);
        double halfr = tdis * Math.sqrt(varr) / Math.sqrt(TIME);

        printCI(meanf, halff, "FIFO");
        printCI(meanr, halfr, "RANDOM");
    }

    private static void printCI(double mean, double half, String name){
        System.out.println(name + " :");
        System.out.println("mean : " + mean);
        System.out.println("CI : " + (mean - half) + " - " + (mean + half));
    }
}
