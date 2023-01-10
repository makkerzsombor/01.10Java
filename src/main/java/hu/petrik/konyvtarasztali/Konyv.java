package hu.petrik.konyvtarasztali;

public class Konyv {
    private int id;
    private String title;
    private String author;
    private int publish_year;
    private int page_count;

    public Konyv(int id, String title, String author, int publish_year, int page_count) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publish_year = publish_year;
        this.page_count = page_count;
    }

    public Konyv(String title, String author, int publish_year, int page_count) {
        this.id = 0;
        this.title = title;
        this.author = author;
        this.publish_year = publish_year;
        this.page_count = page_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    @Override
    public String toString() {
        return  "cím: " + title + '\n' +
                "szerző: " + author + '\n' +
                "kiadás éve: " + publish_year + '\n' +
                "oldalszám: " + page_count;
    }
}
