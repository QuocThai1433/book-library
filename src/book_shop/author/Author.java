package book_shop.author;

public class Author {
    private int id;
    private String author_name;
    private int book_id;

  public  Author ()
  {}

    public Author(int id, String author_name, int book_id) {
        super();
        this.id = id;
        this.author_name = author_name;
        this.book_id = book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBook_id() {
        return book_id;
    }

    @Override
    public String toString() {
        return "author{" + "id=" + id + ", author_name='" + author_name + '\'' + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getId() {
        return id;
    }

    public String getAuthor_name() {
        return author_name;
    }
}
