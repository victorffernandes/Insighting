package com.example.facebook.insighting;

import java.util.ArrayList;
import java.lang.String;
import java.util.StringTokenizer;

//DO NOT USE THESE CARACTERS ;|#
public class Project {
    String projectID;
    public String projectName;
    public String projectDescription;
    public ArrayList<InsightCard> cards;
    ArrayList<String> categoriesList;

    public Project(String name,String id,String description){
        projectName = name;
        projectID = id;
        projectDescription = description;
        cards = new ArrayList<InsightCard>();
        categoriesList =  new ArrayList<String>();
    }

    public Project (String p){
        StringTokenizer tk = new StringTokenizer(p,"|");
        projectID = tk.nextToken();
        projectDescription = tk.nextToken();
        projectName = tk.nextToken();

        /*String[] categories = info[].substring(0,info.length-2).split("#");
        for(String c : categories){
            categoriesList.add(c);
        }*/
    }

    public void addInsightCard(String title, String data){
        cards.add(new InsightCard(title,data));
    }

    public String AsString(){
        String ic = "";
        String cl = "";
        for(InsightCard i : cards) {
            ic += i.AsString();
        }
        for(String c : categoriesList){
            cl += c+"#";
        }
        return projectID +"|"+projectDescription+"|"+projectName+"|"+ic;
    }

    public ArrayList<InsightCard> findCardsWithCategory(String c){
        ArrayList<InsightCard> result = new ArrayList<InsightCard> ();
        for(InsightCard i : cards){
            for(String j : i.categories) {
                if(c == j){
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }
}