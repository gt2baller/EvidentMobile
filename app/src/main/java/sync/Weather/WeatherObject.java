package sync.Weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rbrow_000 on 9/20/2017.
 */
public class WeatherObject {

    private String _imageUrl;
    private DisplayLocationObject _displayLocationObject;

    public WeatherObject(){

    }
    public WeatherObject(JSONObject _object){
        try {
            if(!_object.isNull("current_observation")){
                JSONObject currentObservation = _object.getJSONObject("current_observation");
                if(!currentObservation.isNull("image")){
                    JSONObject image = currentObservation.getJSONObject("image");
                    _imageUrl = image.getString("url");
                }

                if(!currentObservation.isNull("display_location")){
                    JSONObject display_location = currentObservation.getJSONObject("display_location");
                    _displayLocationObject = new DisplayLocationObject(display_location);
                }
                else{
                    _displayLocationObject = new DisplayLocationObject();
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

    public DisplayLocationObject get_displayLocationObject() {
        return _displayLocationObject;
    }
    public void set_displayLocationObject(DisplayLocationObject _displayLocationObject) {
        this._displayLocationObject = _displayLocationObject;
    }
}
