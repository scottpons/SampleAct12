package scottsalvador.pons.com.sqliteact2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        if(!helper.isEmptyDB()) {
            table.moveToFirst();
            showData();
        }

    }

    public void addRecord(View v){
        String fname = eFN.getText().toString().trim();
        String lname = eLN.getText().toString().trim();
        int grade = Integer.parseInt(eGrade.getText().toString().trim());
        boolean isInserted = helper.insert(fname,lname,grade);
        if(isInserted) {
            table = helper.populateTable();
            table.moveToFirst();
            Toast.makeText(this, "Record Inserted", Toast.LENGTH_LONG).show();
        }
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
            table.moveToFirst();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Record NOT Updated",Toast.LENGTH_LONG).show();
    }

    public void deleteRecord(View v){
        String id = table.getString(0);
        boolean isDeleted = helper.delete(id);
        if(isDeleted) {
            if(!helper.isEmptyDB()){
                table = helper.populateTable();
                table.moveToFirst();
                showData();
            }
            else{
                setEmpty();
                Toast.makeText(this, "No more records", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Record NOT Deleted",Toast.LENGTH_LONG).show();
    }

    public void moveFirst(View v){
        if(!helper.isEmptyDB()) {
            table.moveToFirst();
            showData();
        }
        else{
            Toast.makeText(this, "Empty Database", Toast.LENGTH_LONG).show();
        }
    }

    public void moveNext(View v){
        if(!helper.isEmptyDB()){
            if(table.isLast()){
                table.moveToFirst();
                showData();
            }
            else{
                table.moveToNext();
                showData();
            }
        }
        else{
            Toast.makeText(this, "Empty Database", Toast.LENGTH_LONG).show();
        }

    }

    public void movePrev(View v){
        if(!helper.isEmptyDB()){
            if(table.isFirst()){
                table.moveToLast();
                showData();
            }
            else{
                table.moveToPrevious();
                showData();
            }
        }
        else{
            Toast.makeText(this, "Empty Database", Toast.LENGTH_LONG).show();
        }

    }

    public void moveLast(View v){
        if(!helper.isEmptyDB()){
            table.moveToLast();
            showData();
        }
        else{
            Toast.makeText(this, "Empty Database", Toast.LENGTH_LONG).show();
        }

    }

    public void showData(){
        eFN.setText(table.getString(1));
        eLN.setText(table.getString(2));
        eGrade.setText(table.getString(3));
    }

    public void setEmpty(){
        eFN.setText("");
        eLN.setText("");
        eGrade.setText("");
    }
}
