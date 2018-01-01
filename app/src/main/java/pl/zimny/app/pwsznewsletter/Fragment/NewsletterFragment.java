package pl.zimny.app.pwsznewsletter.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elvishew.xlog.XLog;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.zimny.app.pwsznewsletter.R;
import pl.zimny.app.pwsznewsletter.adapter.ArticleAdapter;
import pl.zimny.app.pwsznewsletter.data.ArticlesDownloader;
import pl.zimny.app.pwsznewsletter.model.Article;


public class NewsletterFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ArrayList<Article> articles;
    ArticleAdapter adapter;

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsletter, container, false);
        ButterKnife.bind(this, view);
        articles = new ArrayList<>();
        adapter = new ArticleAdapter(articles);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new ArticleAdapter(articles));
        try {
            articles.addAll(new ArticlesDownloader().execute(current_page).get());
        } catch (Exception e) {
            XLog.d(e.getLocalizedMessage());
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    // Do something
                    current_page++;

                    try {
                        articles.addAll(new ArticlesDownloader().execute(current_page).get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    loading = true;
                }
            }
        });

        return view;
    }

}
