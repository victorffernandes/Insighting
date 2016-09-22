package com.example.facebook.insighting;

import java.util.ArrayList;
import java.lang.String;
import java.util.StringTokenizer;

//DO NOT USE THESE CARACTERS ;|ᔦ + alt255
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
        cards = new ArrayList<InsightCard>();
        String[] tk = p.split("\\|",4);
        projectID = tk[0];
        projectDescription = tk[1];
        projectName = tk[2];
        String ic_cards = tk[3];
            if(!ic_cards.contains(";") && false) {
                String[] ic = ic_cards.split(";");
                for (String i : ic) {
                    cards.add(new InsightCard(i));
                }
            }
        if(!tk[4].contains("ᔦ") && false) {
            String[] categories = tk[4].substring(0, tk[4].length() - 2).split("ᔦ");
            for (String c : categories) {
                categoriesList.add(c);
            }
        }
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
            cl += c+"ᔦ";
        }
        return projectID +"|"+projectDescription+"|"+projectName+"|"+ic+ "|"+cl;
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