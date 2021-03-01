package cpm.gmail.gad1325t.note;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FilenameForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filename_form);
        final Calendar calendar = Calendar.getInstance();

        //ボタン
        Button NewButton=findViewById(R.id.Newbtn);
        NewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try(FileOutputStream fileOutputStream=openFileOutput("filename.txt",MODE_APPEND)) {
                    EditText name=findViewById(R.id.txteria);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-DD-HH:mm");
                    String str =name.getText().toString()+"     "+sdf.format(calendar.getTime())+"\n";
                    fileOutputStream.write(str.getBytes());
                } catch (IOException e){
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
