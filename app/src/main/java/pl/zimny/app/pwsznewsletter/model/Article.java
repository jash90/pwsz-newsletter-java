package pl.zimny.app.pwsznewsletter.model;

/**
 * Created by ZimnY on 30.12.2017.
 */

public class Article {
    private String title;
    private String content;
    private String imgLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Article() {
    }

    public Article(String title, String content, String imgLink) {
        this.title = title;
        this.content = content;
        this.imgLink = imgLink;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgLink='" + imgLink + '\'' +
                '}';
    }
}
