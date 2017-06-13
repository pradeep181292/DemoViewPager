package viewpager.com.demoviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SS71 on 6/13/2017.
 */

class ImagesFragement extends Fragment {
    RecyclerView recyclerview;
    SongAdapter customAdapter;
    List<JSONObject> listData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        albumList();
        customAdapter = new SongAdapter(getContext(), listData);
        recyclerview.setAdapter(customAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    void albumList() {
        for (int i = 0; i < 5; i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("image", R.drawable.download);
                jsonObject.put("time", i + 2 + " HOURS AGO");
                jsonObject.put("title", "Emptiness");
                listData.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
