package labstuff.gcu.me.org.mdassesment;

import java.io.Serializable;

public class Traffic implements Serializable
{
    private String title;
    private String description;
    private String link;
    private String georss;
    private String author;
    private String comments;
    private String pubDate;

    public Traffic()
    {
        title = "";
        description = "";
        link = "";
        georss = "";
        author = "";
        comments = "";
        pubDate = "";
    }

    public Traffic(String date)
    {
        description = date;
    }

    public Traffic(String atitle , String adescription, String alink, String ageorss, String aauthor, String acomments, String apubDate)
    {
        title = atitle;
        description = adescription;
        link = alink;
        georss = ageorss;
        author = aauthor;
        comments = acomments;
        pubDate = apubDate;
    }

    public Traffic(String atitle , String adescription, String alink)
    {
        title = atitle;
        description = adescription;
        link = alink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }



    public String getLink() {
        return link;
    }



    public String getGeorss() {
        return georss;
    }



    public String getAuthor() {
        return author;
    }


    public String getComments() {
        return comments;
    }

    public String getPubDate() {
        return pubDate;
    }




} // End of class
