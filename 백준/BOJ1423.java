import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1423 {

    static int N, D;
    static long[] cnt, power;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cnt = new long[N + 1];
        power = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }
        D = Integer.parseInt(br.readLine());
        //
        long v = 0;
        for (int i = 1; i <= N; i++) {
            v += cnt[i] * power[i];
            cnt[i] = Math.min(D, cnt[i]);
        }
        long[] dp = new long[D + 1];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                for (int k = D; k >= 0; k--) {
                    for (int l = i + 1; l <= N; l++) { // l까지 점프
                        if (k + (l - i) <= D) { // l-i 일
                            dp[k + l - i] = Math.max(dp[k + l - i], dp[k] - power[i] + power[l]);
                        }
                    }
                }
            }
        }
        System.out.println(v + dp[D]);
    }
}
