package cpm.gmail.gad1325t.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //配列アダプター
    ArrayAdapter<String>adapter;

    //EXTRA_TXT
    public static final String EXTRA_TXT="cpm.gmail.gad1325t.memo.EXTRA_TXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FAB
        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SubActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        //リスト
        final ArrayList<String> data=new ArrayList<>();
        String str;

        try(FileInputStream fileInputStream=openFileInput("filename.txt");
            BufferedReader reader=new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))
        ){
            while ((str=reader.readLine())!=null) {
                data.add(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //配列アダプターListview用
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        ListView list=findViewById(R.id.listView);
        list.setAdapter(adapter);

        //listClicked
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String fpname =(String)((TextView)view).getText();
                        Intent intentEditer =new Intent(getApplicationContext(),StringEditerActivity.class);
                        intentEditer.putExtra(EXTRA_TXT,fpname);
                        startActivity(intentEditer);
                    }
                }
        );

        //listClicled(long)
        list.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
                        //検索
                        String targetString=((String)((TextView)view).getText());
                        String txtread=readfile("filename.txt",targetString);
                        //Dialog
                        DialogFragment dialogFragment=new MyDialogFragment();
                        Bundle args=new Bundle();
                        args.putString("targetString",targetString);
                        args.putString("txtread",txtread);
                        dialogFragment.setArguments(args);
                        dialogFragment.show(getSupportFragmentManager(),"dialog_basic");

                        return true;
                    }
                }
        );
    }

    //ファイル読み込み
    public String readfile(String filename,String targetString) {
        StringBuilder stdblt = new StringBuilder();
        String str = null;
        try (FileInputStream fileInputStream = openFileInput(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))) {
            while ((str = reader.readLine()) != null) {
                if(str.equals(targetString)!=true) {
                    stdblt.append(str);
                    stdblt.append("\n");
                    Log.v("str", str);
                }
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

    //ファイル削除
    public void DeleteFile(String filename){
        File file=new File(filename);
        if(file.exists()){
            file.delete();
        }
    }
    //File削除(一覧から)
    public void Delfile(String targetString,String txtread){
        adapter.remove(targetString);
        deleteFile(targetString);
        //上書き
        savefile("filename.txt",txtread);
    }

}
