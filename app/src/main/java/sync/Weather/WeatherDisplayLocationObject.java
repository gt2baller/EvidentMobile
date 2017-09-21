package sync.Weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rbrow_000 on 9/20/2017.
 */
public class WeatherDisplayLocationObject {

    private String _fullname;
    private String _city;
    private String _state;
    private String _stateName;
    private String _country;
    private String _zip;
    private double _latitude;
    private double _longitude;
    private double _elevation;

    public WeatherDisplayLocationObject(){

    }
    public WeatherDisplayLocationObject(JSONObject _object){
        try {
            _fullname = _object.getString("full");
            _city = _object.getString("city");
            _state = _object.getString("state");
            _stateName = _object.getString("state_name");
            _country = _object.getString("country");
            _zip = _object.getString("zip");
            _latitude = _object.getDouble("latitude");
            _longitude = _object.getDouble("longitude");
            _elevation = _object.getDouble("elevation");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String get_fullname(){
        return _fullname;
    }
    public void set_fullname(String _fullname) {
        this._fullname = _fullname;
    }

    public String get_city() {
        return _city;
    }
    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_state() {
        return _state;
    }
    public void set_state(String _state) {
        this._state = _state;
    }

    public String get_stateName() {
        return _stateName;
    }
    public void set_stateName(String _stateName) {
        this._stateName = _stateName;
    }

    public String get_country() {
        return _country;
    }
    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_zip() {
        return _zip;
    }
    public void set_zip(String _zip) {
        this._zip = _zip;
    }

    public double get_elevation() {
        return _elevation;
    }
    public void set_elevation(double _elevation) {
        this._elevation = _elevation;
    }

    public double get_latitude() {
        return _latitude;
    }
    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }
    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }
}
