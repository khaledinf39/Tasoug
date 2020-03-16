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
import com.kh_sof_dev.tasoug.Model.Classes.Supplier;
import com.kh_sof_dev.tasoug.Model.Classes.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Users {
    public interface Get_users{
        void onstart();
        void onSuccess(User user);
        void  onField(String msg);
    }
    RequestQueue queue=null;
    public void Get_All_clients(Context mcontext, final Get_users listener){
        listener.onstart();
        String url= Store_info.api+"users/bystore/"+Store_info.storeID;
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        listener.onSuccess(new User(response));

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                    Log.d("Error.Response", error.getMessage());
                    listener.onField(error.getMessage());
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  Headers = new HashMap<String, String>();
                Headers.put("token", Store_info.token);
                return Headers;
            }
        };

        queue.getCache().initialize();
        queue.add(getRequest);
        queue.getCache().clear();
    }
    public void Get_All_supplier(Context mcontext, final Get_users listener){
        listener.onstart();
        String url= Store_info.api+"supplier/bystore/"+Store_info.storeID;
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            listener.onSuccess(new User(response.getJSONObject("user")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                        listener.onField(error.getMessage());
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  Headers = new HashMap<String, String>();
                Headers.put("token",Store_info.token);
                return super.getHeaders();
            }
        };

        queue.getCache().initialize();
        queue.add(getRequest);
        queue.getCache().clear();
    }
    public void Put_statusOFuser(final Context mcontext, final User user , final Get_users listener) {
        listener.onstart();
        String url= Store_info.api+"users/"+user.getID()+"/"+user.getStatus();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

// Request a json response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Response",jsonObject.toString());
                        listener.onSuccess(new User(jsonObject));
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
    public void Put_statusOFsupplier(final Context mcontext, final Supplier supplier, final Get_users listener) {
        listener.onstart();
        String url= Store_info.api+"suppliers/"+supplier.getID()+"/"+supplier.getStatus();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

// Request a json response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Log.d("Response",jsonObject.toString());
                            listener.onSuccess(new User(jsonObject.getJSONObject("user")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
