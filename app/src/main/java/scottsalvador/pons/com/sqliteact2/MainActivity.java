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
        table.moveToFirst();
        eFN = findViewById(R.id.eFN);
        eLN = findViewById(R.id.eLN);
        eGrade = findViewById(R.id.eGrade);
        showData();

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

    public void editRecord(View v){
        String fname = eFN.getText().toString().trim();
        String lname = eLN.getText().toString().trim();
        int grade = Integer.parseInt(eGrade.getText().toString().trim());
        String id = table.getString(0);
        boolean isUpdated = helper.update(id,fname,lname,grade);
        if(isUpdated) {
            table = helper.populateTable();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Record NOT Updated",Toast.LENGTH_LONG).show();
    }

    public void deleteRecord(View v){
        String fname = eFN.getText().toString().trim();
        String lname = eLN.getText().toString().trim();
        int grade = Integer.parseInt(eGrade.getText().toString().trim());
        String id = table.getString(0);
        boolean isDeleted = helper.delete(id,fname,lname,grade);
        if(isDeleted) {
            table = helper.populateTable();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Record NOT Updated",Toast.LENGTH_LONG).show();
    }

    public void moveFirst(View v){
        table.moveToFirst();
        showData();
    }

    public void moveNext(View v){
        table.moveToNext();
        showData();
    }

    public void movePrev(View v){
        table.moveToPrevious();
        showData();
    }

    public void moveLast(View v){
        table.moveToLast();
        showData();
    }


    public void showData(){
        eFN.setText(table.getString(1));
        eLN.setText(table.getString(2));
        eGrade.setText(table.getString(3));
    }
}
