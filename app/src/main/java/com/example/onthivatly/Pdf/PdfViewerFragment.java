package com.example.onthivatly.Pdf;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.onthivatly.MainActivity;
import com.example.onthivatly.R;
import com.example.onthivatly.cauhoitracnghiem.Exam;
import com.example.onthivatly.cauhoitracnghiem.ExamAdapter;
import com.example.onthivatly.cauhoitracnghiem.NewsActivity;
import com.example.onthivatly.slide.ScreenSlideActivity;
import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PdfViewerFragment extends Fragment {

    PDFView pdfView;
    public PdfViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Tổng hợp kiến thức");


        return inflater.inflate(R.layout.fragment_pdf_viewer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        pdfView= (PDFView)getActivity().findViewById(R.id.pdfView);
        pdfView.fromAsset("book1.pdf")// all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .invalidPageColor(Color.WHITE) // color of page that is invalid and cannot be loaded
                .load();





    }

}
