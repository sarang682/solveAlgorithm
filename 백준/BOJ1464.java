import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1464 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        char min = str[0];
        for (int i = 1; i < str.length; i++) {
            if (str[i] <= min) {
                min = str[i];
                reverse(str, 0, i - 1);
                reverse(str, 0, i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str) {
            sb.append(c);
        }
        System.out.println(sb);
    }

    public static void reverse(char[] str, int s, int e) {
        while (s < e) {
            char tmp = str[s];
            str[s] = str[e];
            str[e] = tmp;
            s++;
            e--;
        }
    }
}
