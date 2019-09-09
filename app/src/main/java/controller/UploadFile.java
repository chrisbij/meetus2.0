package controller;

import android.util.Log;

import org.apache.http.client.methods.HttpPost;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bijou on 06/10/2015.
 */
public class UploadFile {


    HttpURLConnection connection = null;
    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;
    String urlServer = "http://meetus.noip.me/meetus/uploadFile.php";
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boudary = "*****";
    int serveResponseCode;
    String serverResponseMessages;

    int bytesRead, bytesAvaible, bufferSize;
    byte[] buffer;
    int maxBufferSize = 1*1024*1024;

    public UploadFile(){

    }

    public void uploadFileToServer(String pathFile, String idActivite){

        final String activite_id = idActivite;
        final String pathToOurFile = pathFile;//"/storage/emulated/0/images.jpg";

        Log.e("File", "bon");

       new Thread(new Runnable() {
           @Override
           public void run() {
               try{
                   FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile));

                   URL url = new URL(urlServer);
                   connection = (HttpURLConnection) url.openConnection();

                   // Allow Inputs &amp; Outputs.
                   connection.setDoInput(true);
                   connection.setDoOutput(true);
                   connection.setUseCaches(false);

                   // Set HTTP method to POST.
                   connection.setRequestMethod("POST");
                   connection.setRequestProperty("Connection", "Keep-Alive");
                   connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boudary);

                   outputStream = new DataOutputStream(connection.getOutputStream());
                   outputStream.writeBytes(twoHyphens + boudary + lineEnd);
                   outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile + "\"" + lineEnd);
                   outputStream.writeBytes(lineEnd);

                   bytesAvaible = fileInputStream.available();
                   bufferSize = Math.min(bytesAvaible, maxBufferSize);
                   buffer = new byte[bufferSize];

                   // Read file
                   bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                   while (bytesRead > 0){
                       outputStream.write(buffer, 0, bufferSize);
                       bytesAvaible = fileInputStream.available();
                       bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                   }


                   outputStream.writeBytes(lineEnd);
                   outputStream.writeBytes(twoHyphens + boudary + twoHyphens + lineEnd);

                   // Responses from the server (code and message)
                   serveResponseCode = connection.getResponseCode();
                   serverResponseMessages = connection.getResponseMessage();

                   Log.e("upload", serverResponseMessages);

                   fileInputStream.close();
                   outputStream.flush();
                   outputStream.close();

               }catch (Exception ex){
                   Log.e("Error", ex.toString());
               }
           }
       }).start();


    }

}
