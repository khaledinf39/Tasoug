package com.kh_sof_dev.tasoug.Model.Classes;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.kh_sof_dev.tasoug.Controule.Info.Store_info;
import com.kh_sof_dev.tasoug.Model.LocalDatabase.DBManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Expense {

        private  String date,ID,StoreID,desc,date_time;
        private Double price;
private List<Expense> expenseList;
        @RequiresApi(api = Build.VERSION_CODES.O)
        public Expense(Double price, String desc) {
            this.date = getToday_date();
            StoreID = Store_info.storeID;
            this.price = price;
            this.desc=desc;
        }

        public Expense() {
        }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getToday_date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return  dtf.format(now);
    }
    public Expense(JSONObject expense) {
        if (expense==null){
            return;
        }
        expenseList=new ArrayList<>();
        try{
            JSONArray jsonArray=expense.getJSONArray("result");
            for (int i=0;i<jsonArray.length();i++){
                Expense expense1=new Expense();
                expense1.desc=jsonArray.getJSONObject(i).getString("desc");
                expense1.price= jsonArray.getJSONObject(i).getDouble("price");
                expense1.ID= jsonArray.getJSONObject(i).getString("_id");
                expense1.date= jsonArray.getJSONObject(i).getString("date_time");
              expenseList.add(expense1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static List<String> fech_expenses(Context context){
        List<String> expenseList=new ArrayList<>();

        List<Expense> expenseList_=new ArrayList<>();
        DBManager dbManager = new DBManager(context);
        dbManager.open();
        Cursor cursor = dbManager.fetch_Bond();

        if (cursor != null){
            if (cursor.moveToFirst()){
//
                do {
//
//                    Expense h=new Expense();
//                    h.setPrice(cursor.getDouble(0));
//                    h.setDesc(cursor.getString(1));

                    expenseList.add(cursor.getString(1));


                } while (cursor.moveToNext());
//
            }
            dbManager.close();
        }
        return expenseList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getStoreID() {
            return StoreID;
        }

        public void setStoreID(String storeID) {
            StoreID = storeID;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }


}
