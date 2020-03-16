package com.kh_sof_dev.tasoug.Controule.Routes;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.Model.Classes.Product;
import com.kh_sof_dev.tasoug.Model.Classes.Store;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Stores {
    public Stores() {
    }

    public interface Get_Stores{
        void onstart();
        void onSuccess(Store store);
        void  onField(String msg);
    }
    RequestQueue queue=null;

    public void Login(Context mcontext, final String phone, final String pass, final Get_Stores listener){
        listener.onstart();
//        String url= Store_info.api+"stores/login";
        String url="https://tsouag.herokuapp.com/stores/login?password=656464&phone=18886545465";//Store_info.api+"stores/login";
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        listener.onSuccess(new Store(response));

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Error.Response", error.getMessage());
                        listener.onField(error.getMessage());
                    }
                }
        ){


        };

        queue.getCache().initialize();
        queue.add(getRequest);
        queue.getCache().clear();
    }
    public void Put_store(final Context mcontext, final Store store , final Get_Stores listener) {
        listener.onstart();
        String url= Store_info.api+"stores/"+Store_info.storeID;
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

        JSONObject cart=store.toJsonObject();
        Log.d("cart send",cart.toString());
// Request a json response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT, url, cart,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Response",jsonObject.toString());
                        listener.onSuccess(new Store(jsonObject));
                    }
                }, new Response.ErrorListener (){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onField(volleyError.toString());
            }
        }
        ){
            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String>  Headers = new HashMap<String, String>();
                Headers.put("token",Store_info.token);
                Headers.put("Accept","application/json");
                Headers.put("Content-Type","application/json");
                return Headers;
            }
        };


// Add the request to the RequestQueue.
        queue.getCache().initialize();
        queue.add(jsonObjectRequest);
        queue.getCache().clear();
        // prepare the Request

    }

}
