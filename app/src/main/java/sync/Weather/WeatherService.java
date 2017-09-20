package sync.Weather;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import sync.evidentmobile.R;

/**
 * Created by rbrow_000 on 9/19/2017.
 */
public class WeatherService {

    Context mContext;
    LayoutInflater layoutInflater;

    public WeatherService(Context _context){
        mContext = _context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public void GetWeather(){
        RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask();
        retrieveFeedTask.execute();
    }

    private void UpdateUIWithData(WeatherObject _weatherObject){
        Activity activity = (Activity)mContext;
        if(activity != null){
            if(_weatherObject.get_imageUrl() != null){
                ImageView imageView = (ImageView)activity.findViewById(R.id.imageView);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(_weatherObject.get_imageUrl()).into(imageView);
            }

            TextView textViewFullName = (TextView)activity.findViewById(R.id.textViewFullName);
            TextView textViewCountry = (TextView)activity.findViewById(R.id.textViewCountry);
            TextView textViewZipCode = (TextView)activity.findViewById(R.id.textViewZipCode);
            textViewFullName.setText(_weatherObject.get_displayLocationObject().get_fullname());
            textViewCountry.setText(_weatherObject.get_displayLocationObject().get_country());
            textViewZipCode.setText(_weatherObject.get_displayLocationObject().get_zip());
        }

    }

    private class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            Activity activity = (Activity)mContext;
            if(activity != null){
                ProgressBar progressBar = (ProgressBar)activity.findViewById(R.id.progressBar);
                ImageView imageView = (ImageView)activity.findViewById(R.id.imageView);
                TextView textView = (TextView)activity.findViewById(R.id.textViewResults);
                progressBar.setVisibility(View.VISIBLE);
                textView.setText("");
                imageView.setVisibility(View.GONE);
            }
        }

        protected String doInBackground(Void... urls) {
            String apiUrl = mContext.getString(R.string.api_url);
            String apiKey = mContext.getString(R.string.api_key);
            String apiPath = mContext.getString(R.string.api_path);
            // Do some validation here

            try {

                URL url = new URL(apiUrl + apiKey + apiPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            try {
                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                WeatherObject weatherObject = new WeatherObject(object);
                UpdateUIWithData(weatherObject);

            } catch (JSONException e) {
                // Appropriate error handling code
            }

            Activity activity = (Activity)mContext;
            if(activity != null){
                ProgressBar progressBar = (ProgressBar)activity.findViewById(R.id.progressBar);
                TextView textView = (TextView)activity.findViewById(R.id.textViewResults);
                progressBar.setVisibility(View.GONE);
                Log.i("INFO", response);
//                textView.setText(response);
            }
        }
    }
}
