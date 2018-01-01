package pl.zimny.app.pwsznewsletter.Fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elvishew.xlog.XLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.zimny.app.pwsznewsletter.R;
import pl.zimny.app.pwsznewsletter.adapter.NotificationAdapter;
import pl.zimny.app.pwsznewsletter.data.NotificationsDownloader;
import pl.zimny.app.pwsznewsletter.model.Notification;


public class NotificationsFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ArrayList<Notification> notifications;
    NotificationAdapter adapter;
    int current_page =1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, view);
        notifications = new ArrayList<>();
        adapter = new NotificationAdapter(notifications);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        try {
           notifications.addAll(new NotificationsDownloader().execute(current_page).get());
        } catch (Exception e) {
            XLog.d(e.getLocalizedMessage());
        }

        return view;
    }
}
