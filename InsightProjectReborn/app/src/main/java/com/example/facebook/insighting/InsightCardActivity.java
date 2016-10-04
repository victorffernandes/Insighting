package com.example.facebook.insighting;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InsightCardActivity extends AppCompatActivity {

    public Project p;
    String id_project;
    public String formattedDate;
    DatabaseController db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight_card);
        Bundle extras = getIntent().getExtras();
        db = new DatabaseController(getBaseContext());
        if(extras != null) {
            id_project = extras.getString("project");
            Cursor c = db.getSpecificProject(id_project);
            Button b = (Button)findViewById(R.id.title_project);
            //Log.i("MainAcitvity",id_project);
            if (c.moveToFirst()) {
                b.setText( c.getString(1));
            }
            c.close();
        }
        addInsightCard();
    }

    public void addInsightCard(){
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup gridlayout = (ViewGroup) findViewById(R.id.grid);
        Cursor c = db.getInsightCardsFromProjects(id_project);
        c.moveToFirst();
        while (!c.isAfterLast()) {
                inflater.inflate(R.layout.cardview, gridlayout);
                View cv = gridlayout.getChildAt(gridlayout.getChildCount()-1);
                Log.d("MainActivity",c.getCount()+"rada");
                cv.setId(Integer.parseInt(c.getString(c.getColumnIndex("id_card"))));
                ((TextView) cv.findViewById(R.id.card_name)).setText(c.getString(0));
                c.moveToNext();
            }
    }

    public void editCreateInsightCard(View v)
    {
        Intent i = new Intent(this, EditInsightCard.class);
        i.putExtra("new",true);
        i.putExtra("id_project",id_project);
        i.putExtra("id_card", v.getId());
        //i.putExtra("project", p.AsString());
        //i.putExtra("ic_id", p.cards.size());
        //Log.d("InsightCardActivity",p.AsString());
        startActivity(i);
    }

    public void onBackPressed()
    {
        Intent i = new Intent(this,ProjectActivity.class);
        startActivity(i);
    }

    public void editInsightCard(View v){
        Intent i = new Intent(this, EditInsightCard.class);
        i.putExtra("new",false);
        i.putExtra("id_card", v.getId()+"");
        i.putExtra("id_project",id_project);
        startActivity(i);
    }
}