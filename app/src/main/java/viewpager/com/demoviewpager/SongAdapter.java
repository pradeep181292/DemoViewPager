package viewpager.com.demoviewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


class SongAdapter extends RecyclerView.Adapter<SongAdapter.ItemHolder> {
    List<JSONObject> listData = new ArrayList<>();
    Context context;

    public SongAdapter(Context context, List<JSONObject> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_list_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        try {
            holder.time.setText(listData.get(position).getString("time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView time;

        public ItemHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);
        }
    }
}
