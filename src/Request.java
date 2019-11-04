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

  public void toNextArriveTime(){
    double next = Math.log(1. - Math.random()) / - (1./(double)label);
    //exponential distribution , in millie seconds
    time += next * 1000;
  }
}
