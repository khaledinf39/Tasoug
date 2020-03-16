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
import com.kh_sof_dev.tasoug.Model.Classes.Term;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Terms {
    public interface Get_Term{
        void onstart();
        void onSuccess(Term term);
        void  onField(String msg);
    }
    RequestQueue queue=null;

    public void Get_All_Term_supp(Context mcontext, final Get_Term listener){
        listener.onstart();
        String url= Store_info.api+"terms/term_supplier/"+Store_info.storeID;
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
                        listener.onSuccess(new Term(response));

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
    public void Post_term_supp(final Context mcontext, final Term term , final Get_Term listener) {
        listener.onstart();
        String url= Store_info.api+"terms/addTerm_condition_supplier" +
                "?storeID="+Store_info.storeID+"&titel="+term.getTitle()+"&body="+term.getBody();
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
                        listener.onSuccess(new Term(jsonObject ));
                    }
                }, new Response.ErrorListener (){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onField(volleyError.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  Params = new HashMap<String, String>();
                Params.put("storeID",Store_info.storeID);
                Params.put("title",term.getTitle());
                Params.put("body",term.getBody());

                return Params;
            }

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
    public void Put_term_supp(final Context mcontext, final Term term , final Get_Term listener) {
        listener.onstart();
        String url= Store_info.api+"terms/term_condition_supplier/"+term.getID()
                +
        "?storeID="+Store_info.storeID+"&titel="+term.getTitle()+"&body="+term.getBody();
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
                        listener.onSuccess(new Term(jsonObject ));
                    }
                }, new Response.ErrorListener (){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onField(volleyError.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  Params = new HashMap<String, String>();
                Params.put("storeID",Store_info.storeID);
                Params.put("title",term.getTitle());
                Params.put("body",term.getBody());

                return Params;
            }

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
    public void Delete_term_supp(final Context mcontext, final Term term , final Get_Term listener) {
        listener.onstart();
        String url= Store_info.api+"terms/term_condition_supplier/"+term.getID();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

// Request a json response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Response",jsonObject.toString());
                        listener.onSuccess(new Term(jsonObject ));
                    }
                }, new Response.ErrorListener (){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onField(volleyError.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  Params = new HashMap<String, String>();
                Params.put("storeID",Store_info.storeID);
                Params.put("title",term.getTitle());
                Params.put("body",term.getBody());

                return Params;
            }

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


    /***********************fore client******************************************************************/
    public void Get_All_Term_client(Context mcontext, final Get_Term listener){
        listener.onstart();
        String url= Store_info.api+"terms/term_client/"+Store_info.storeID;
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
                        listener.onSuccess(new Term(response ));

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
    public void Post_term_client(final Context mcontext, final Term term , final Get_Term listener) {
        listener.onstart();
        String url= Store_info.api+"terms/addTerm_condition_client"+
                "?storeID="+Store_info.storeID+"&titel="+term.getTitle()+"&body="+term.getBody();
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
                        listener.onSuccess(new Term(jsonObject ));
                    }
                }, new Response.ErrorListener (){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onField(volleyError.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  Params = new HashMap<String, String>();
                Params.put("storeID",Store_info.storeID);
                Params.put("title",term.getTitle());
                Params.put("body",term.getBody());

                return Params;
            }

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
    public void Put_term_client(final Context mcontext, final Term term , final Get_Term listener) {
        listener.onstart();
        String url= Store_info.api+"terms/term_condition_client/"+term.getID()+
                "?storeID="+Store_info.storeID+"&titel="+term.getTitle()+"&body="+term.getBody();
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
                        listener.onSuccess(new Term(jsonObject ));
                    }
                }, new Response.ErrorListener (){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onField(volleyError.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  Params = new HashMap<String, String>();
                Params.put("storeID",Store_info.storeID);
                Params.put("title",term.getTitle());
                Params.put("body",term.getBody());

                return Params;
            }

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
    public void Delete_term_client(final Context mcontext, final Term term , final Get_Term listener) {
        listener.onstart();
        String url= Store_info.api+"terms/term_condition_client/"+term.getID();
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

// Request a json response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Response",jsonObject.toString());
                        listener.onSuccess(new Term(jsonObject ));
                    }
                }, new Response.ErrorListener (){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onField(volleyError.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  Params = new HashMap<String, String>();
                Params.put("storeID",Store_info.storeID);
                Params.put("title",term.getTitle());
                Params.put("body",term.getBody());

                return Params;
            }

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
