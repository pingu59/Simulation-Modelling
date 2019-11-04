import java.util.Random;

public class RandomCache extends Cache {
    int[] cache;
    Random rand = new Random(System.currentTimeMillis());

    public RandomCache(int dataSize, int cacheSize, long end){
        super(dataSize, cacheSize, end);
        cache = new int[cacheSize];
        for(int i = 0; i < cacheSize; i++){
            cache[i] = i + 1;
        }
    }

    public void simulateOne(){
        int request = nextRequest();
        boolean found = false;
        for(int i = 0 ; i < getCacheSize(); i++){
            if(cache[i] == request) {
                found = true;
                break;
            }
        }
        if(!found){
            incrementMiss();
            int slot = rand.nextInt(getCacheSize());
            cache[slot] = request;
        }
    }

}
