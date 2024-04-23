package book_shop.readers;

public class Readers {
    private int id;
    private String nameReader;

    public Readers() {
    }

    public Readers(int id, String nameReader) {
        super();

        this.id = id;
        this.nameReader = nameReader;

    }

    public void setId(int id) {
        this.id = id;
    }


    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }


    public String getNameReader() {
        return nameReader;
    }

    public int getId() {
        return id;
    }


    public String getHoTen() {
        return nameReader;
    }
}
