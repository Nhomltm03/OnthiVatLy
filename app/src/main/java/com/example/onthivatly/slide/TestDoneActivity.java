package com.example.onthivatly.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.onthivatly.R;
import com.example.onthivatly.question.Question;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

    ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
    int numNoAns=0;
    int numTrue=0;
    int numFalse=0;
    int totalScore=0;

   // ScoreController scoreController;

    TextView tvTrue, tvFalse, tvNotAns, tvTotalScore;
    Button  btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);

        Intent intent = getIntent();
        arr_QuesBegin= (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");
        begin();
        checkResult();
        totalScore= numTrue*1;
        tvNotAns.setText(""+numNoAns);
        tvFalse.setText(""+numFalse);
        tvTrue.setText(""+numTrue);
        tvTotalScore.setText(""+totalScore);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.alert);
                builder.setTitle("THÔNG BÁO");
                builder.setMessage("Bạn có muốn thoát ?");

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
        });

    }
    public void begin(){
        tvFalse= (TextView)findViewById(R.id.tvFalse);
        tvTrue= (TextView)findViewById(R.id.tvTrue);
        tvNotAns= (TextView)findViewById(R.id.tvNotAns);
        tvTotalScore= (TextView)findViewById(R.id.tvTotalPoint);
        btnExit=(Button)findViewById(R.id.btnExit);
    }
    //PT Check kết quả
    public void checkResult(){
        for(int i=0; i< arr_QuesBegin.size(); i++){
            if(arr_QuesBegin.get(i).getTraloi().equals("")==true){
                numNoAns++;
            }else if(arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getTraloi())==true){
                numTrue++;
            }else numFalse++;
        }
    }
}
