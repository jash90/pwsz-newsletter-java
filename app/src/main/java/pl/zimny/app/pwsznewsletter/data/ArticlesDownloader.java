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

/**
 * Created by ZimnY on 30.12.2017.
 */

public class ArticlesDownloader extends AsyncTask<Integer,Void,ArrayList<Article>> {
    @Override
    protected ArrayList<Article> doInBackground(Integer... params) {
        ArrayList<Article> articles = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://www.pwsz.krosno.pl/uczelnia/aktualnosci/page,"+params[0]+".html").get();
            Elements elements = doc.select("div.aktualnosci-margines");
            for(Element element : elements){
                Element e = element.select("a[href]").get(0);
                String title = element.select("h3").text();
                String date = element.select("div.data").text();
                String imgLink = e.select("img[src]").attr("src").toString();
                articles.add(new Article(title,date,"http://www.pwsz.krosno.pl/"+imgLink));
            }
        } catch (IOException e) {
            Log.d("JSOUP",e.getLocalizedMessage());
        }
        return articles;
    }
}
