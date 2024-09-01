import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7453 {

    static int N;
    static long[] A, B, C, D, E, F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        B = new long[N];
        C = new long[N];
        D = new long[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }
        E = new long[N * N];
        F = new long[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                E[N * i + j] = A[i] + B[j];
                F[N * i + j] = (C[i] + D[j]) * -1;
            }
        }
        Arrays.sort(E);
        Arrays.sort(F);
        long ans = 0;
        int lo = 0;
        for (int i = 0; i < N * N; i++) {
            int lower = lowerBound(lo, E[i]);
            int upper = upperBound(lo, E[i]);
            ans += upper - lower;
            lo = lower;
        }
        System.out.println(ans);
    }

    public static int upperBound(int lo, long target) {
        int hi = N * N;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (F[mid] <= target)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }

    public static int lowerBound(int lo, long target) {
        int hi = N * N;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (F[mid] < target)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }
}
