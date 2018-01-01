package pl.zimny.app.pwsznewsletter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.zimny.app.pwsznewsletter.R;
import pl.zimny.app.pwsznewsletter.model.Article;

/**
 * Created by ZimnY on 30.12.2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    ArrayList<Article> articles = new ArrayList<>();

    public ArticleAdapter(ArrayList<Article> articles) {
        this.articles = articles;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_article, parent, false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.content.setText(article.getContent());
        Glide.with(holder.itemView).load(article.getImgLink()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subtitle)
        TextView content;

        public ArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

