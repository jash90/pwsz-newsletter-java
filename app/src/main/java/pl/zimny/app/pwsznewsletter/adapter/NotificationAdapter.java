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
import pl.zimny.app.pwsznewsletter.model.Notification;

/**
 * Created by ZimnY on 30.12.2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {
    ArrayList<Notification> notifications = new ArrayList<>();

    public NotificationAdapter(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notification, parent, false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        Notification Notification = notifications.get(position);
        holder.title.setText(Notification.getTitle());
        holder.content.setText(Notification.getContent());
        Glide.with(holder.itemView).load(Notification.getImgLink()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subtitle)
        TextView content;

        public NotificationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
