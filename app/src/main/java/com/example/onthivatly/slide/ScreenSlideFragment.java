package com.example.onthivatly.slide;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onthivatly.R;
import com.example.onthivatly.question.Question;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlideFragment extends Fragment {

    ArrayList<Question> arr_Ques;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    private int mPageNumber; //vi tri trang hien tai
    public int checkAns;   // biến kiểm tra ...
    TextView tvNum, tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;


    public ScreenSlideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide, container, false);
        tvNum = (TextView) rootView.findViewById(R.id.tvNum);
        tvQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);

        return rootView;
    }

    //lay vi tri cua trang
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Ques = new ArrayList<Question>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        arr_Ques = slideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns = getArguments().getInt(ARG_CHECKANSWER);

    }

    public static ScreenSlideFragment creat(int pageNumber, int checkAnswer) {
        ScreenSlideFragment fragment = new ScreenSlideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu" + (mPageNumber + 1));
        tvQuestion.setText(arr_Ques.get(mPageNumber).getQuestion());
        radA.setText(arr_Ques.get(mPageNumber).getAns_a());
        radB.setText(arr_Ques.get(mPageNumber).getAns_b());
        radC.setText(arr_Ques.get(mPageNumber).getAns_c());
        radD.setText(arr_Ques.get(mPageNumber).getAns_d());

        //Khoa chon dap an khi an ket thuc bai thi
        if(checkAns!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);

            getCheckAns(getItem(mPageNumber).getResult().toString());
        }


//In đáp án đã chọn vào diaglog kiểm tra
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                arr_Ques.get(mPageNumber).choiceID = checkedId;
                arr_Ques.get(mPageNumber).setTraloi(getChoiceFromID(checkedId));

               // Toast.makeText(getActivity(), "Bạn chọn đáp án: " + getChoiceFromID(checkedId), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public Question getItem(int posotion) {
        return arr_Ques.get(posotion);
    }

    //Lấy giá trị (vị trí) radiogroup chuyển thành đáp án A/B/C/D
    private String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "a";
        } else if (ID == R.id.radB) {
            return "b";
        } else if (ID == R.id.radC) {
            return "c";
        } else if (ID == R.id.radD) {
            return "d";
        } else return "";
    }

    //Hàm kiểm tra câu đúng, nếu câu đúng thì đổi màu background radiobutton tương ứng
    private void getCheckAns(String ans){
        if(ans.equals("a")==true){
            radA.setBackgroundColor(Color.GREEN);
        }
        else if(ans.equals("b")==true){
            radB.setBackgroundColor(Color.GREEN);
        }else if(ans.equals("c")==true){
            radC.setBackgroundColor(Color.GREEN);
        }else if(ans.equals("d")==true){
            radD.setBackgroundColor(Color.GREEN);
        }else ;
    }
}
