package com.example.animacija;

import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    public AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv =(ImageView) this.findViewById(R.id.pazarJava);
        iv.setBackgroundResource(R.drawable.anim);
        anim = (AnimationDrawable)iv.getBackground();
    }


    public void kontrola(View v)
    {
        TextView tv = new TextView(this);
        tv = findViewById(R.id.tekst);


        Switch s = (Switch)v;

        if (s.isChecked())
        {
            Odbrojavac odb = new Odbrojavac();
            odb.execute();
        } else
            s.setChecked(true);

    }

    private class Odbrojavac extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            for (int i = 5; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Gotov!";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.tekst);
            txt.setText(result);
            anim.stop();
            Switch s = (Switch) findViewById(R.id.switch1);
            s.setChecked(false);
        }

        @Override
        protected void onPreExecute()
        {
            TextView txt = (TextView) findViewById(R.id.tekst);
            txt.setText("5");
            anim.start();

        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            TextView txt = (TextView) findViewById(R.id.tekst);
            txt.setText(String.valueOf(values[0]));
        }
    }
}

