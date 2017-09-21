package sync.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rbrow_000 on 9/20/2017.
 */
public class WeatherObject {

    private String _imageUrl;
    private WeatherDisplayLocationObject _WeatherDisplayLocationObject;
    private ArrayList<String> _itemArrayList = new ArrayList<>();

    public WeatherObject(){

    }
    public WeatherObject(JSONObject _object){
        try {
            if(!_object.isNull("current_observation")){
                JSONObject currentObservation = _object.getJSONObject("current_observation");
                if(!currentObservation.isNull("image")){
                    JSONObject image = currentObservation.getJSONObject("image");
                    _imageUrl = image.getString("url");
                    _itemArrayList.add(currentObservation.getString("weather"));
                    _itemArrayList.add(currentObservation.getString("temperature_string"));
                    _itemArrayList.add(currentObservation.getString("relative_humidity"));
                }

                if(!currentObservation.isNull("display_location")){
                    JSONObject display_location = currentObservation.getJSONObject("display_location");
                    _WeatherDisplayLocationObject = new WeatherDisplayLocationObject(display_location);
                }
                else{
                    _WeatherDisplayLocationObject = new WeatherDisplayLocationObject();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String get_imageUrl() {
        return _imageUrl;
    }
    public void set_imageUrl(String _imageUrl) {
        this._imageUrl = _imageUrl;
    }

    public WeatherDisplayLocationObject get_WeatherDisplayLocationObject() {
        return _WeatherDisplayLocationObject;
    }
    public void set_WeatherDisplayLocationObject(WeatherDisplayLocationObject _WeatherDisplayLocationObject) {
        this._WeatherDisplayLocationObject = _WeatherDisplayLocationObject;
    }

    public ArrayList<String> get_itemArrayList() {
        return _itemArrayList;
    }
    public void set_itemArrayList(ArrayList<String> _itemArrayList) {
        this._itemArrayList = _itemArrayList;
    }
}
