import java.util.ArrayDeque;
import java.util.Deque;

public class FIFOCache extends Cache {
  Deque<Integer> cache;

  public FIFOCache(int dataSize, int cacheSize, long end){
    super(dataSize, cacheSize, end);
    cache = new ArrayDeque<>();
    for(int i = 1; i <= cacheSize; i++){
      cache.addLast(i);
    }
  }

  public void simulateOne(){
    int request = nextRequest();
    if(!cache.contains(request)){
      incrementMiss();
      cache.poll();
      cache.addLast(request);
    }
  }
}
