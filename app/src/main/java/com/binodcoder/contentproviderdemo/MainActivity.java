package com.binodcoder.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView queryResult;
    private static final String TAG="ContentProviderDemo";

    private String[] mColumnProjection=new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };
    private String mSelectionCluse=ContactsContract.Contacts.DISPLAY_NAME_PRIMARY+"='Binod'";
    private String [] mSelectionArguments=new String[]{
            "Binod"
    };
    private String mOrderBy=ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryResult=(TextView)findViewById(R.id.tv_query_result);


//since data is small i did in main thread but in case of large data we should go loader class
        Cursor cursor=getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,
                null,
                null
                );
        if(cursor!=null && cursor.getCount()>0){
            StringBuilder stringBuilder=new StringBuilder("");
            while(cursor.moveToNext()){
                stringBuilder.append(cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2)+"\n");

            }
            queryResult.setText(stringBuilder.toString());
        }else{
            queryResult.setText("No Contacts in device");
        }

    }
}