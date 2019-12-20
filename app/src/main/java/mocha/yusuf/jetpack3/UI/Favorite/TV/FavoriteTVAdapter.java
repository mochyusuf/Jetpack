package mocha.yusuf.jetpack3.UI.Favorite.TV;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import mocha.yusuf.jetpack3.BuildConfig;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.UI.TV.DetailTV;

public class FavoriteTVAdapter extends RecyclerView.Adapter<FavoriteTVAdapter.ViewHolder>{
    private Context context;
    private List<TVEntity> tvs;

    public void setTvs(List<TVEntity> tvs) {
        if (tvs == null) return;
        this.tvs.clear();
        this.tvs.addAll(tvs);
    }

    public FavoriteTVAdapter(Context context) {
        this.context = context;
        tvs = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_description;
        private ImageView tv_image;
        private RelativeLayout tv;

        ViewHolder(View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_description = view.findViewById(R.id.tv_description);
            tv_image = view.findViewById(R.id.tv_image);
            tv = view.findViewById(R.id.tv);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_tv, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final TVEntity tv = getTvs().get(position);
        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.API_IMAGE +tv.getPoster_path())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.tv_image);
        holder.tv_title.setText(tv.getName());
        holder.tv_description.setText(tv.getOverview());
        holder.tv.setOnClickListener(v -> {
            Intent moveWithObjectIntent = new Intent(context, DetailTV.class);
            moveWithObjectIntent.putExtra(DetailTV.EXTRA_TV, tvs.get(holder.getAdapterPosition()));
            context.startActivity(moveWithObjectIntent);
        });
    }

    private List<TVEntity> getTvs() {
        return tvs;
    }

    public void _notifyItemRangeRemoved(int i,int size){
        notifyItemRangeRemoved(i,size);
    }

    @Override
    public int getItemCount() {
        return this.tvs.size();
    }
}