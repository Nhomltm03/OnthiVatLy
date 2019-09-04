package com.example.onthivatly.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class QuestionController {
    private DBHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Question> getQuestion(int num_exam, String subject){
        ArrayList<Question> lsData= new ArrayList<Question>();
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        db.disableWriteAheadLogging();
        Cursor cursor= db.rawQuery("SELECT * FROM cauhoivatly WHERE num_exam = '"+num_exam+"' AND subject='"+subject+"' ORDER BY random()",null);
        cursor.moveToFirst();
        do {
            Question item;
            item= new Question(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),"");
            lsData.add(item);
        }while (cursor.moveToNext());
        db.close();
        return lsData;
    }
}
