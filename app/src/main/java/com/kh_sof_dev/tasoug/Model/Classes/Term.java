package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Term {
    private String title,body,ID;
private List<Term> termList;
    public Term(String title, String body,String id) {
        this.title = title;
        this.body = body;

    }

    public Term() {
    }

    public Term(JSONObject term)  {
        if (term==null){
            return;
        }
        termList=new ArrayList<>();
        try{
            JSONArray jsonArray=term.getJSONArray("result");
            for (int i=0;i<jsonArray.length();i++){
                Term term1=new Term();
                term1.title=jsonArray.getJSONObject(i).getString("titel");
               term1. body=jsonArray.getJSONObject(i).getString("body");
                term1.ID=jsonArray.getJSONObject(i).getString("_id");
                termList.add(term1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
