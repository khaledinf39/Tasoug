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
import com.kh_sof_dev.tasoug.Model.Classes.Box;
import com.kh_sof_dev.tasoug.Model.Classes.Expense;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Expenses {
    public interface Get_Expenses{
        void onstart();
        void onSuccess(Expense expense);
        void  onField(String msg);
    }
    RequestQueue queue=null;

    public void Get_All_expenses(Context mcontext, final Get_Expenses listener){
        listener.onstart();
        String url= Store_info.api+"stores/expenses/"+Store_info.storeID;
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
                        listener.onSuccess(new Expense(response));

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
    public void Post_Expenses(final Context mcontext, final Expense expense , final Get_Expenses listener) {
        listener.onstart();
        String url= Store_info.api+"stores/addTOexpenses"+
                "?storeID="+Store_info.storeID+"&price="+expense.getPrice()+"&description="+expense.getDesc();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

// Request a json response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Response",jsonObject.toString());
                        listener.onSuccess(new Expense(jsonObject));
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
