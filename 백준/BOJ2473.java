import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] num = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        long min = Long.MAX_VALUE;
        long[] ans = new long[3];
        Arrays.sort(num);

        for (int i = 0; i < N - 2; i++) {
            int lo = i + 1;
            int hi = N - 1;
            while (lo < hi) {
                long sum = num[i] + num[lo] + num[hi];
                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    ans[0] = num[i];
                    ans[1] = num[lo];
                    ans[2] = num[hi];
                }
                if (sum == 0) break;
                if (sum > 0) hi--;
                else lo++;
            }
        }
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}
