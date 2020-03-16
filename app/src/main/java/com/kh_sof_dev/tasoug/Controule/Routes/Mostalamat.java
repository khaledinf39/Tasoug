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
import com.kh_sof_dev.tasoug.Model.Classes.Order;
import com.kh_sof_dev.tasoug.Model.Classes.ProductOFmost;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Mostalamat {


    public interface Get_Order{
        void onstart();
        void onSuccess(Order order);
        void  onField(String msg);
    }

    public interface Get_Most{
        void onstart();
        void onSuccess(ProductOFmost productOFmost);
        void  onField(String msg);
    }
    RequestQueue queue=null;
    public void Get_All_order(Context mcontext,int status, final Get_Order listener){
        listener.onstart();
        String url= Store_info.api+"mostalamats/"+status;
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
                            listener.onSuccess(new Order(response.getJSONObject("orders")));
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
                Headers.put("token", Store_info.token);
                return super.getHeaders();
            }
        };

        queue.getCache().initialize();
        queue.add(getRequest);
        queue.getCache().clear();
    }
    public void Get_All_orderBystore_status(Context mcontext,int status, final Get_Most listener){
        listener.onstart();
        String url= Store_info.api+"mostalamats/by_store_And_status/"+Store_info.storeID+"/"+status+"/1";
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
                        listener.onSuccess(new ProductOFmost(response));

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
                Headers.put("token", Store_info.token);
                return Headers;
            }
        };

        queue.getCache().initialize();
        queue.add(getRequest);
        queue.getCache().clear();
    }
    public void Get_All_orderBystore(Context mcontext, final Get_Most listener){
        listener.onstart();
        String url= Store_info.api+"mostalamats/bystore/"+Store_info.storeID+"/1";
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
                        listener.onSuccess(new ProductOFmost(response));

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Log.d("Error.Response", error.getMessage());
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
    public void Get_All_orderByuser(Context mcontext,String userID,int status, final Get_Order listener){
        listener.onstart();
        String url= Store_info.api+"mostalamats/by_user_And_status/"+userID+"/"+status;
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
                        listener.onSuccess(new Order(response));

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   Log.d("Error.Response", error.getMessage());
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


    public void Post_Order(final Context mcontext, final Order order , final Get_Order listener) {
        listener.onstart();
        String url= Store_info.api+"mostalamats";
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }
JSONObject order_=order.toJsonObject();
        Log.d("order",order_.toString());
// Request a json response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, order_,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Response",jsonObject.toString());
                        listener.onSuccess(new Order());
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
    public void Put_Order_status(final Context mcontext,String orderID, final int status , final Get_Order listener) {
        listener.onstart();
        String url= Store_info.api+"mostalamats/"+orderID+"/"+status;
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
                        listener.onSuccess(new Order(null));
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
