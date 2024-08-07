import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1508 {

    static int N, M, K;
    static int[] X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        int lo = Integer.MAX_VALUE;
        for (int i = 1; i < K; i++) {
            if (X[i] - X[i - 1] < lo)
                lo = X[i] - X[i - 1];
        }
        int hi = (X[K - 1] - X[0]) / (M - 1) + 1;
        int L = binarySearch(lo, hi);

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        int cnt = 1;
        sb.append(1);
        for (int i = 1; i < K; i++) {
            if (cnt < M && X[i] - X[idx] >= L) {
                cnt++;
                idx = i;
                sb.append(1);
            } else sb.append(0);
        }
        System.out.println(sb);
    }

    public static int binarySearch(int lo, int hi) {
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (chk(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo - 1;
    }

    public static boolean chk(int L) {
        int cnt = 1;
        int idx = 0;
        for (int i = 0; i < K; i++) {
            if (X[i] - X[idx] >= L) {
                idx = i;
                if (++cnt >= M)
                    return true;
            }
        }
        return false;
    }
}
