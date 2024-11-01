import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12919 {

    static String S, T;
    static boolean find = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        dfs(T);
        if (find) System.out.println(1);
        else System.out.println(0);
    }

    public static void dfs(String str) {
        if (str.length() <= S.length()) {
            if (str.equals(S)) find = true;
            return;
        }
        if (str.charAt(0) == 'B') {
            String s = str.substring(1);
            dfs(new StringBuilder(s).reverse().toString());
        }
        if (str.charAt(str.length() - 1) == 'A') {
            String s = str.substring(0, str.length() - 1);
            dfs(s);
        }
    }
}
