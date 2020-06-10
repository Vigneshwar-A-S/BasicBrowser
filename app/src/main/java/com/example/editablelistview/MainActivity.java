package com.example.editablelistview;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText UrlInput;
    private Button search_btn;

    String Url_Address;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        UrlInput = ( EditText ) findViewById(R.id.input_search_url);
        search_btn = (Button) findViewById(R.id.search);


        search_btn.setOnClickListener(this);

    }
    @Override
        public void onClick(View v) {

        if (v == search_btn)
        {
            OpenWebsite3();

        }



        }

    private void OpenWebsite3()
    {

        Url_Address = UrlInput.getText().toString();

        if(TextUtils.isEmpty(Url_Address))
        {
            Toast empty = Toast.makeText(MainActivity.this, "Please enter URL or Web Address", Toast.LENGTH_LONG);
            empty.show();
        }
        else
        {
           // String url_without_https = Url_Address.replaceAll("https://www.", "");
           // String https = "https://";
           // String www = "www.";

            Intent search = new Intent(MainActivity.this, WebviewActivity.class);
            search.putExtra("url_address", "https://www.google.com.bd/search?q=" + Url_Address);
            startActivity(search);

            UrlInput.setText("");
            UrlInput.requestFocus();



        }
    }


}


