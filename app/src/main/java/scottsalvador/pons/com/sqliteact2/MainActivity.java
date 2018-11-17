package scottsalvador.pons.com.sqliteact2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Cursor table;
    EditText eFN,eLN,eGrade;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new Helper(this);
        table = helper.populateTable();
        eFN = findViewById(R.id.eFN);
        eLN = findViewById(R.id.eLN);
        eGrade = findViewById(R.id.eGrade);

    }

    public void addRecord(View v){
        String fname = eFN.getText().toString().trim();
        String lname = eLN.getText().toString().trim();
        int grade = Integer.parseInt(eGrade.getText().toString().trim());
        boolean isInserted = helper.insert(fname,lname,grade);
        if(isInserted)
            Toast.makeText(this, "Record Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Record NOT Inserted",Toast.LENGTH_LONG).show();

    }
}
