import java.io.*;
import java.util.*;

public class BOJ1484 {

    static int G;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        int x = 1, y = 1;
        while (true) {
            int diff = x * x - y * y;
            if (x - y == 1 && diff > G) break;
            if (diff == G) ans.append(x).append("\n");
            if (diff <= G) x++;
            else y++;
        }
        if (ans.length() <= 0) ans.append(-1);
        System.out.println(ans);
    }
}
