import static java.lang.Math.exp;

public class Request {
  private int label;
  private long time;

  public Request(int label){
    this.label = label;
    this.time = 0L;
    toNextArriveTime();
  }

  public int getLabel(){
    return label;
  }

  public long getTime(){
    return time;
  }

  //TODO: change this
  public void toNextArriveTime(){
    long next = (long)((1. - exp(-1./(double)label)) * 1000);
    //exponential distribution , in millie seconds
    time += next;
  }
}
