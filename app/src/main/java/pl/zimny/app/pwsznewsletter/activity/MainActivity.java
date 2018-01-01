package pl.zimny.app.pwsznewsletter.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import pl.zimny.app.pwsznewsletter.Fragment.ChatFragment;
import pl.zimny.app.pwsznewsletter.Fragment.NewsletterFragment;
import pl.zimny.app.pwsznewsletter.Fragment.NotificationsFragment;
import pl.zimny.app.pwsznewsletter.R;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_newsletter:
                    NewsletterFragment newsletterFragment = new NewsletterFragment();
                    FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame,newsletterFragment);
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_chat:
                    ChatFragment chatFragment = new ChatFragment();
                    FragmentTransaction fragmentTransaction2 =getFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame,chatFragment);
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_notifications:
                    NotificationsFragment notificationsFragment = new NotificationsFragment();
                    FragmentTransaction fragmentTransaction3 = getFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.frame,notificationsFragment);
                    fragmentTransaction3.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
