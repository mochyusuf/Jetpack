package mocha.yusuf.jetpack3.UI.Movie;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import mocha.yusuf.jetpack3.Model.MovieModel;
import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.ViewModel.ViewModelFactory;

public class MovieFragment extends Fragment {
    private MovieAdapter adapter;
    RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.recyler_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            MovieViewModel movieViewModel = obtainViewModel(getActivity());
            adapter = new MovieAdapter(getActivity());

            movieViewModel.setMovieAction("load");
            movieViewModel.movies.observe(this, movie -> {
                if (movie != null) {
                    switch (movie.status) {
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.setMovies(movie.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

            });

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }

    private static MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }
}
