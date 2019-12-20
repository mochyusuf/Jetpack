package mocha.yusuf.jetpack3.UI.Movie;

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
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    public static final String TAG = "MovieAdapter_TAG";
    private final List<MovieEntity> movies;

    public void setMovies(List<MovieEntity> movies) {
        if (movies == null) return;
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movie_title;
        private TextView movie_description;
        private ImageView movie_image;
        private RelativeLayout movie;

        ViewHolder(View view) {
            super(view);
            movie_title = view.findViewById(R.id.movie_title);
            movie_description = view.findViewById(R.id.movie_description);
            movie_image = view.findViewById(R.id.movie_image);
            movie = view.findViewById(R.id.movie);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MovieEntity movie = getMovie().get(position);
        Glide.with(holder.itemView.getContext())
                .load(BuildConfig.API_IMAGE +movie.getPoster_path())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.movie_image);
        holder.movie_title.setText(movie.getTitle());
        holder.movie_description.setText(movie.getOverview());
        holder.movie.setOnClickListener(v -> {
            Intent moveWithObjectIntent = new Intent(context, DetailMovie.class);
            moveWithObjectIntent.putExtra(DetailMovie.EXTRA_MOVIE, movies.get(holder.getAdapterPosition()));
            context.startActivity(moveWithObjectIntent);
        });
    }

    private List<MovieEntity> getMovie() {
        return movies;
    }

    public void _notifyItemRangeRemoved(int i, int size) {
        notifyItemRangeRemoved(i, size);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }
}
