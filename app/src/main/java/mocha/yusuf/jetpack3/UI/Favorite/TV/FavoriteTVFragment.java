package mocha.yusuf.jetpack3.UI.Favorite.TV;

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

import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.ViewModel.ViewModelFactory;

public class FavoriteTVFragment extends Fragment {
    private FavoriteTVAdapter adapter;
    RecyclerView recyclerView;
    private ProgressBar progressBar;

    public FavoriteTVFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_favorite_tv, container, false);
        recyclerView = view.findViewById(R.id.recyler_favorite_tv);
        progressBar = view.findViewById(R.id.progress_bar);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            FavoriteTvViewModel favoriteTvViewModel = obtainViewModel(getActivity());
            adapter = new FavoriteTVAdapter(getActivity());

            favoriteTvViewModel.getFavoriteTv().observe(this, tv -> {
                if (tv != null) {
                    switch (tv.status) {
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.setTvs(tv.data);
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

    private static FavoriteTvViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavoriteTvViewModel.class);
    }
}
