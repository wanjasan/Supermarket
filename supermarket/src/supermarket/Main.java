package supermarket;

public class Main {
    public static void main(String[] args) {
        IDandPasswords idandPasswords = new IDandPasswords();
        HomePage homepage = new HomePage(idandPasswords);
    }
}