package cpm.gmail.gad1325t.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StringEditerActivity extends AppCompatActivity {

    String fpname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_editer);
        //getfpname
        Intent intent=getIntent();
        fpname =intent.getStringExtra(MainActivity.EXTRA_TXT);

        //表示
        String txtread=readfile(fpname);
        final EditText editText=findViewById(R.id.edittext);
        editText.setText(txtread);

        //Buttonlistner
        Button btn=findViewById(R.id.save_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt =editText.getText().toString();
                int txtlength =txt.length();
                if(txt!=null&&txtlength>0){
                    if(txt.substring(txtlength).equals("\0")){
                        txt=txt.substring(0,txt.length()-1);
                    }
                }

                if(txtlength==0){
                    editText.setText("Nothing");
                }
                else{
                    savefile(fpname,txt);
                    Toast toast = Toast.makeText(StringEditerActivity.this,
                            "Save is Succeed", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        });
    }


    //ファイル読み込み
    public String readfile(String filename) {
        StringBuilder stdblt = new StringBuilder();
        String str = null;

        try (FileInputStream fileInputStream = openFileInput(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))) {
            while ((str = reader.readLine()) != null) {
                stdblt.append(str);
                stdblt.append("\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stdblt.toString();

    }

    //ファイル保存
    public void savefile(String filename,String str){
        try(FileOutputStream fileOutputStream=openFileOutput(filename, Context.MODE_PRIVATE);){
            fileOutputStream.write(str.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
