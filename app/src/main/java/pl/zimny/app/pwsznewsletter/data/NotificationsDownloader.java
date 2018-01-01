package pl.zimny.app.pwsznewsletter.data;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import pl.zimny.app.pwsznewsletter.model.Article;
import pl.zimny.app.pwsznewsletter.model.Notification;

/**
 * Created by ZimnY on 31.12.2017.
 */

public class NotificationsDownloader extends AsyncTask<Integer,Void,ArrayList<Notification>> {
    @Override
    protected ArrayList<Notification> doInBackground(Integer... params) {
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://www.pwsz.krosno.pl/uczelnia/aktualnosci/page,"+params[0]+".html").get();
            Elements elements = doc.select("div.aktualnosci-margines");
            for(Element element : elements){
                Element e = element.select("a[href]").get(0);
                String title = element.select("h3").text();
                String date = element.select("div.data").text();
                String imgLink = e.select("img[src]").attr("src").toString();
                notifications.add(new Notification(title,date,"http://www.pwsz.krosno.pl/"+imgLink));
            }
        } catch (IOException e) {
            Log.d("JSOUP",e.getLocalizedMessage());
        }
        return notifications;
    }
}
