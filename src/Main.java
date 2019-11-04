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
        int dataSize = 1000;
        int cacheSize = 100;
        System.out.println("M = " + dataSize + " N = " + cacheSize);
        Cache fifo = new FIFOCache(dataSize, cacheSize,  1000L * 1000 * 1000);
        Cache random = new RandomCache(dataSize, cacheSize,  1000L * 1000 * 1000);
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

    //RESULTS
    /**
     * M = 1000 N = 10
     * FIFO :
     * mean : 0.8176683678352712
     * CI : 0.8173329347302327 - 0.8180038009403097
     * RANDOM :
     * mean : 0.8177016408261839
     * CI : 0.8173607260155625 - 0.8180425556368053
     *
     * M = 1000 N = 50
     * FIFO :
     * mean : 0.5942907044154555
     * CI : 0.5938394776825688 - 0.5947419311483422
     * RANDOM :
     * mean : 0.5942677563992559
     * CI : 0.5938145796858779 - 0.5947209331126339
     *
     * M = 1000 N = 100
     * FIFO :
     * mean : 0.47900155146115325
     * CI : 0.4785317632596019 - 0.4794713396627046
     * RANDOM :
     * mean : 0.47914904642106276
     * CI : 0.4786072388161022 - 0.47969085402602335
     */
}
