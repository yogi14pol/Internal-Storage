package com.clicks.yogi.internal_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edt1;
    Button btn1,btn2;
    TextView txt1;
    String file_name = "MyHp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        edt1 = findViewById(R.id.edt1);
        txt1 = findViewById(R.id.txt1);

        // Write text...
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(file_name,MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String save = "\n"+ edt1.getText().toString();

                try {
                    fos.write(save.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Data is saved",
                        Toast.LENGTH_SHORT).show();
                edt1.setText("");
            }
        });

        // Read text...
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fis = openFileInput(file_name);
                    int i;
                    String display="";

                    while ((i = fis.read())!=-1)
                    {
                        display = display+(char)i;
                    }
                    txt1.setText(display);
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
