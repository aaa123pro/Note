package cpm.gmail.gad1325t.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //ボタン
        Button NewButton=findViewById(R.id.Newbtn);
        NewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try(FileOutputStream fileOutputStream=openFileOutput("filename.txt",MODE_APPEND)) {
                    EditText name=findViewById(R.id.txteria);
                    String str =name.getText().toString()+'\n';
                    fileOutputStream.write(str.getBytes());
                } catch (IOException e){
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
