import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ2632 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] sum_a = new int[(m * 2) + 1];
        int[] sum_b = new int[(n * 2) + 1];
        for (int i = 1; i <= m; i++) {
            int a = Integer.parseInt(br.readLine());
            sum_a[i] = sum_a[i - 1] + a;
        }
        for (int i = 1; i <= m; i++) {
            sum_a[m + i] += sum_a[m] + sum_a[i];
        }
        for (int i = 1; i <= n; i++) {
            int a = Integer.parseInt(br.readLine());
            sum_b[i] = sum_b[i - 1] + a;
        }
        for (int i = 1; i <= n; i++) {
            sum_b[n + i] += sum_b[n] + sum_b[i];
        }
        HashMap<Integer, Integer> a = new HashMap<>();
        HashMap<Integer, Integer> b = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < m - 1; j++) {
                int v = sum_a[i + j] - sum_a[i - 1];
                if (a.containsKey(v)) {
                    a.put(v, a.get(v) + 1);
                } else {
                    a.put(v, 1);
                }
            }
        }
        a.put(sum_a[m], 1);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n - 1; j++) {
                int v = sum_b[i + j] - sum_b[i - 1];
                if (b.containsKey(v)) {
                    b.put(v, b.get(v) + 1);
                } else {
                    b.put(v, 1);
                }
            }
        }
        b.put(sum_b[n], 1);
        int ans = 0;
        for (int key : a.keySet()) {
            if (key == K) {
                ans += a.get(key);
            } else if (key < K) {
                if (b.containsKey(K - key)) {
                    ans += a.get(key) * b.get(K - key);
                }
            }
        }
        if (b.containsKey(K))
            ans += b.get(K);
        System.out.println(ans);
    }
}
