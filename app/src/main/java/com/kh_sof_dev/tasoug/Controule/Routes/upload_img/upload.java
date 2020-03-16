package com.kh_sof_dev.tasoug.Controule.Routes.upload_img;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.Model.Classes.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class upload {

    public interface Uploading{
        void onstart();
        void onSuccess(String url);
        void  onField(String msg);
    }
    RequestQueue queue=null;
    public void uploadImage(final Context mcontext, final Bitmap bitmap, final Uploading listener){

        listener.onstart();
        String url= Store_info.api+"upload";
        RequestQueue queue = Volley.newRequestQueue(mcontext);  // this = context

        if (queue==null){
            queue = Volley.newRequestQueue(mcontext);
        }

        final ProgressDialog dialog=new ProgressDialog(mcontext);
        dialog.setTitle("رفع الصور");
        dialog.setMessage("يتم رفع الصور الأن..!");
        dialog.show();

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST,
                url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Log.d("ressssssoo",new String(response.data));
                        

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(new String(response.data));
                            //JSONObject jsonObjectRequest=jsonObject.getJSONObject("items");
                           listener.onSuccess(jsonObject.getJSONObject("result").getString("url"));
                            Log.d("ressssssoo",jsonObject.getJSONObject("result").getString("url"));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                        dialog.dismiss();




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Toast.makeText(mcontext, "Error  uploading"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("ressssssoo","Error  uploading"+error.getMessage());

                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String>  Params = new HashMap<String, String>();


                return Params;
            }



            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String>  Headers = new HashMap<String, String>();
                Headers.put("token",Store_info.token);

                return Headers;
            }

            @Override
            protected Map<String, VolleyMultipartRequest.DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                if (bitmap!=null) {
                    params.put("photo", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                }
                return params;
            }
        };



        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue = Volley.newRequestQueue(mcontext);
        queue.add(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
