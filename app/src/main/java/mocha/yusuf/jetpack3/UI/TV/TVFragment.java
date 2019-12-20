package mocha.yusuf.jetpack3.UI.TV;

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

import mocha.yusuf.jetpack3.Model.TVModel;
import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.ViewModel.ViewModelFactory;

public class TVFragment extends Fragment {
    private TVAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    public TVFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_tv, container, false);
        recyclerView = view.findViewById(R.id.recyler_tv);
        progressBar = view.findViewById(R.id.progress_bar);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            TvViewModel tvViewModel = obtainViewModel(getActivity());
            adapter = new TVAdapter(getActivity());
            progressBar.setVisibility(View.VISIBLE);

            tvViewModel.setTvAction("load");
            tvViewModel.tvShows.observe(this, tvShow -> {
                if (tvShow != null) {
                    switch (tvShow.status) {
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.setTvs(tvShow.data);
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

    private static TvViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvViewModel.class);
    }
}
