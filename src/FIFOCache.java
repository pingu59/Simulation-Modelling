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
      int victim = cache.poll();
      cache.addLast(request);
      System.out.println("At " + getTime() + ": Removing " + victim + ", appending " + request);
    }else{
      System.out.println("At " + getTime() + ": Hit " + request);
    }
  }

  public static void main(String[] args) {
    Cache c = new FIFOCache(1000, 10, 1000 * 1000L);
    c.simulate();
    System.out.println(c.getMissRate());
  }
}
