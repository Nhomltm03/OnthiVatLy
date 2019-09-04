package com.example.onthivatly.cauhoitracnghiem;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onthivatly.MainActivity;
import com.example.onthivatly.R;
import com.example.onthivatly.slide.ScreenSlideActivity;
import com.flaviofaria.kenburnsview.KenBurnsView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    ListView lvTieuDe;
    ArrayList<String> arrayTitle, arrayLink;
    ArrayAdapter adapter;
    TitleAdapter titleAdapter ;
    ArrayList<ListTitle> arrayListTitle;
    KenBurnsView kbv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        new ReadRSS().execute("https://tin.tuyensinh247.com/de-thi-thu-thpt-quoc-gia-mon-ly-e483.rss");

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Thông tin mới");
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        lvTieuDe=(ListView) getActivity().findViewById(R.id.lvTieuDe);
        arrayTitle = new ArrayList<>();
        arrayLink  = new ArrayList<>();
        arrayListTitle = new ArrayList<>();
        kbv = (KenBurnsView)getActivity().findViewById(R.id.top_image);
        adapter = new ArrayAdapter((MainActivity)getActivity(),android.R.layout.simple_list_item_1,arrayTitle);
        lvTieuDe.setAdapter(adapter);
        lvTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent((MainActivity)getActivity(),NewsActivity.class);
                intent.putExtra("linkTintuc", arrayLink.get(position));
                startActivity(intent);
            }
        });
    }
    private  class ReadRSS extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {

                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line ="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String tieude="";
            for(int i=0;i< nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);
                tieude = parser.getValue(element,"title");
                arrayTitle.add(tieude);
                arrayLink.add(parser.getValue(element,"link"));
                arrayListTitle.add(new ListTitle(tieude,parser.getValue(element,"link") ));
            }
            titleAdapter = new TitleAdapter((MainActivity)getActivity(),android.R.layout.simple_list_item_1,arrayListTitle);
            lvTieuDe.setAdapter(titleAdapter);
            adapter.notifyDataSetChanged();
        }
    }


}
