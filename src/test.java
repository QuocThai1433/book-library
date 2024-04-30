public class test implements Itesst {

    @Override
    public String color() {
        return null;
    }

    @Override
    public String format() {
        return null;
    }

    @Override
    public void Bk(int a) {
        Itesst.super.Bk(a);
    }

    public static void main(String[] args) {
        test test = new test();
        int k = 9;
        test.Bk(k);
    }
}
