import java.util.*;
import java.io.*;

public class BOJ2661 {

    static int N;
    static int[] ans;
    static boolean flg = false;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = new int[N];
        dfs(0);
    }

    public static void dfs(int depth) {
        if (flg) return;
        if (depth == N) {
            flg = true;
            for(int i=0;i<N;i++) sb.append(ans[i]);
            System.out.println(sb);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            ans[depth] = i;
            if (check(depth)) dfs(depth + 1);
        }
    }

    public static boolean check(int idx) {
        for (int i = 1; i <= (idx + 1) / 2; i++) {
            int first_start = idx - 2 * i + 1;
            int second_start = idx - i + 1;
            if (first_start < 0) continue;
            boolean equal = true;
            for (int j = 0; j < i; j++) {
                if (ans[first_start + j] != ans[second_start + j]) {
                    equal = false;
                    break;
                }
            }
            if (equal) return false;
        }
        return true;
    }
}
