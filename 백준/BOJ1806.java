import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, S;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        int[] num = new int[N + 1];
        int[] sum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + num[i];
        }

        int s = 1, e = 1;
        int ans = Integer.MAX_VALUE;

        while (s <= N && e <= N) {
            if (sum[e] - sum[s - 1] >= S) {
                ans = Math.min(ans, e - s + 1);
                s++;
            } else {
                e++;
            }
        }

        ans = ans == Integer.MAX_VALUE ? 0 : ans;
        System.out.println(ans);
    }
}
