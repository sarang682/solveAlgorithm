import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1300 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int lo = 1;
        int hi = K;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (check(mid, N, K)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        System.out.println(lo);
    }

    public static boolean check(int num, int n, int k) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            cnt += Math.min(num / i, n);
            if (cnt >= k) return true;
        }
        return false;
    }
}
