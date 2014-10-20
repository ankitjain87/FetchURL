import java.util.Vector;

import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class FetchURL extends Thread{
  private Vector<Long> responseTime;
  private String LINK = "http://www.apple.com/index.html";

  public FetchURL(Vector<Long> array) {
    responseTime = array;
  }

  public void run() {
    try {
      URL url = new URL(LINK);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      long start = System.currentTimeMillis();
      connection.connect();
      if (connection.getResponseCode() == 200) {
        long end = System.currentTimeMillis();
        responseTime.add(end-start);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
