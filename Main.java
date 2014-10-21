import java.util.Arrays;
import java.util.ArrayList;
import java.util.Vector;

class Main {

  public static int NO_OF_TIMES = 1000;

  // Returns the kth percentile for the responses.
  public int getPercentile(int k) {
    double index = NO_OF_TIMES * k /100;
    index = Math.abs(index);
    return (int)index - 1;
  }

  // Returns the mean for the responses.
  public long getMean(Long[] responses) {
    long sum = 0;
    for (int i=0; i<responses.length; i++) {
      sum += responses[i];
    }
    return sum / NO_OF_TIMES;
  }

  // Returns the standar deviation for the responses.
  public double getStandarDeviation(Long[] responses, long mean) {
    double sumOfDiff = 0;
    for (int i=0; i<responses.length; i++) {
      double diff = responses[i] - mean;
      sumOfDiff += diff * diff;
    }
    return Math.sqrt(sumOfDiff / NO_OF_TIMES);
  }

  public static void main(String[] args) throws InterruptedException {
    Main m = new Main();
    Long[] responses = new Long[NO_OF_TIMES];
    Vector<Long> responseTime = new Vector<Long>();
    ArrayList<FetchURL> threads = new ArrayList<FetchURL>();
    for (int i=0; i<NO_OF_TIMES; i++) {
      FetchURL f = new FetchURL(responseTime);
      f.start();
      threads.add(f);
    }
    for (FetchURL f : threads) {
      f.join();
    }
    responseTime.toArray(responses);
    Arrays.sort(responses);


    // Print.
    System.out.println("==10th Percentile==" + responses[m.getPercentile(10)]);
    System.out.println("==50th Percentile==" + responses[m.getPercentile(50)]);
    System.out.println("==90th Percentile==" + responses[m.getPercentile(90)]);
    System.out.println("==95th Percentile==" + responses[m.getPercentile(95)]);
    System.out.println("==99th Percentile==" + responses[m.getPercentile(99)]);
    
    long mean = m.getMean(responses);
    System.out.println("==Mean==" + mean);

    System.out.println("==Standard Deviation==" + m.getStandarDeviation(responses, mean));
  }
}
