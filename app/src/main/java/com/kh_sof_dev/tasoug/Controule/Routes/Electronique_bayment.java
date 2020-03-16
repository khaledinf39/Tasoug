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
import com.kh_sof_dev.tasoug.Model.Classes.Electronique_payment;
import com.kh_sof_dev.tasoug.Model.Classes.Expense;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Electronique_bayment {
    public interface Get_Electronique_bayment{
        void onstart();
        void onSuccess(Electronique_payment Electronique_bayment);
        void  onField(String msg);
    }
    RequestQueue queue=null;

    public void Get_All_expenses(Context mcontext, final Get_Electronique_bayment listener){
        listener.onstart();
        String url= Store_info.api+"electronique_bayments/"+Store_info.storeID+"/1";
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
                        listener.onSuccess(new Electronique_payment(response));

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
}
