public class StringRegexTest {
    public static void main(String[] args) {
        String s = "hel  o";
        if(s.matches("\\s+")) {
            System.out.println("T");
        } else {
            System.out.println("F");
        }
        System.out.println("\\+\\s");
    }
}
