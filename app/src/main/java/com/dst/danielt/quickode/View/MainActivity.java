package com.dst.danielt.quickode.View;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dst.danielt.quickode.Adapters.RecyclerViewAdapter;
import com.dst.danielt.quickode.Model.RecyclerViewData;
import com.dst.danielt.quickode.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerViewAdapter rcvA;
    Context context;
        List<RecyclerViewData> data;
        RecyclerView recycleV;
        String url = "https://jsonplaceholder.typicode.com/photos/";
        List<RecyclerViewData> rvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        recycleV = (RecyclerView) findViewById(R.id.recylceV);
        rvData = new ArrayList<>();



    }


    @Override
    protected void onResume() {
        super.onResume();
        GetHttpReqAsyncTask getData = new GetHttpReqAsyncTask();
        getData.execute();
        Log.i("define recycleView", " ");
        recycleV.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this, 1,false);
        recycleV.setLayoutManager(llm);
        Log.i("set up view adapter ",  " for recyclerview ");
        rcvA = new RecyclerViewAdapter(rvData, context);
        recycleV.setAdapter(rcvA);
        Log.i("recycler view completed", " ");
    }


    public class GetHttpReqAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            String data = "";
            try {
                HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
                urlBuilder.addQueryParameter("albumId","1");

                String urlFinal = urlBuilder.build().toString();
                Request request = new Request.Builder().url(urlFinal).build();
                Log.i("gethttp Log 2 url query", urlFinal);
                Response response = client.newCall(request).execute();
                Log.i("response code ", String.valueOf(response.code()));
                if(response.code() == 200){
                    String d3 =  response.body().string();

                    Log.i(" JSOn string", d3);
                    return d3;
                }
            }
            catch (Exception e){
                Log.e("JSON exception ", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            int num = s.length();
            Log.i("get ok http", "on post execute " + num + " "+s );
            rvData.clear();

     //       Gson gson = new Gson();
   //         Type collectionType = new TypeToken<Collection<GsonData>>(){}.getType();
 //           Collection<GsonData> dataFinal = gson.fromJson(s, collectionType);

      //      GsonData gsonData = gson.fromJson(s,GsonData.class);
            try {
                JSONArray jsonArray = new JSONArray(s);
                int len = jsonArray.length();
                Log.i("jsonArray.length()",String.valueOf(len) );
                for (int i=0; i<jsonArray.length(); i++){

                    String imgURL = jsonArray.getJSONObject(i).getString("url");
                    String imgTitle = jsonArray.getJSONObject(i).getString("title");
                    int imgId = jsonArray.getJSONObject(i).getInt("id");
                    int albumId = jsonArray.getJSONObject(i).getInt("albumId");
                    String thumbURL = jsonArray.getJSONObject(i).getString("thumbnailUrl");

                    RecyclerViewData rvd = new RecyclerViewData();
                    rvd.setAlbumId(albumId);
                    rvd.setImage(imgURL);
                    rvd.setImageTitle(imgTitle);
                    rvd.setImageID(imgId);

                    rvData.add(rvd);
                }
            }
            catch(JSONException e){
                Log.d("JSON exception ", e.toString());

            }
/*
            for (int i =0; i< 10; i++) {

                String imgURL = gsonData.getUrl();
                String imgTitle = gsonData.getTitle();
                int imgId = gsonData.getId();
                int albumId = gsonData.getAlbumId();

                RecyclerViewData rvd = new RecyclerViewData();
                rvd.setAlbumId(albumId);
                rvd.setImage(imgURL);
                rvd.setImageTitle(imgTitle);
                rvd.setImageID(imgId);

                rvData.add(rvd);

            }
*/
            Log.i("Data Array object", rvData.size() + " ");
            rcvA.notifyDataSetChanged();
        }
    }



}