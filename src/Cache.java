import java.util.PriorityQueue;

public abstract class Cache {
  private int dataSize; // n
  private int cacheSize; //m
  private long time; //clock
  private int miss;
  private int count;
  private long end;
  private PriorityQueue<Request> requests;

  public int getCacheSize() {
    return cacheSize;
  }

  public Cache(int dataSize, int cacheSize, long end){
    this.dataSize = dataSize;
    this.cacheSize = cacheSize;
    this.end = end;
    time = 0L;
    miss = 0;
    requests = new PriorityQueue<Request>((a, b) -> (int)(a.getTime() - b.getTime()));
    for(int i = 1; i <= dataSize; i++){
      requests.add(new Request(i));
    }
  }

  public void incrementMiss(){
    miss++;
  }

  public double getMissRate(){ return ((double) miss/count) ; }

  //get the most recent request, find out next time and add it back to pq
  //updates the clock
  public int nextRequest(){
    Request request = requests.poll();
    setTime(request.getTime());
    request.toNextArriveTime();
    requests.add(request);
    return request.getLabel();
  }

  public long getTime(){
    return time;
  }

  public void setTime(long newTime){
    time = newTime;
  }

  public void simulate(){
    while(end > time){
      simulateOne();
      count++;
    }
  }

  public abstract void simulateOne();
}
