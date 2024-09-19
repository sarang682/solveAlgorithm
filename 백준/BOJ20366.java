import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20366 {

    static int N;
    static int[] diameter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        diameter = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diameter[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(diameter);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 3; j < N; j++) {
                int sum = diameter[i] + diameter[j];
                int lo = i + 1;
                int hi = j - 1;
                while (lo < hi) {
                    int v = diameter[lo] + diameter[hi];
                    if (v == sum) {
                        System.out.println(0);
                        return;
                    }
                    ans = Math.min(ans, Math.abs(sum - v));
                    if (v < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
