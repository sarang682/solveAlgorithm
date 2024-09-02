import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ19566 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long[] num = new long[N + 1];
        long[] sum = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Long.parseLong(st.nextToken());
            sum[i] = sum[i - 1] + num[i];
        }
        long ans = 0;
        Map<Long, Integer> diff = new TreeMap<>();
        for (int i = 1; i <= N; i++) {
            long d = sum[i] - K * i;
            if (!diff.containsKey(d)) {
                diff.put(d, 1);
            } else {
                int cnt = diff.get(d);
                ans += cnt;
                diff.put(d, cnt + 1);
            }
        }
        if (diff.containsKey((long) 0))
            ans += diff.get((long) 0);
        System.out.println(ans);
    }
}
