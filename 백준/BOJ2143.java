import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2143 {
    static long T;
    static int N, M;
    static long[] A, B;
    static long[] sumA, sumB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int in = Integer.parseInt(st.nextToken());
            A[i] = A[i - 1] + in;
        }
        M = Integer.parseInt(br.readLine());
        B = new long[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int in = Integer.parseInt(st.nextToken());
            B[i] = B[i - 1] + in;
        }
        sumA = makeArr(N, A);
        sumB = makeArr(M, B);
        Arrays.sort(sumA);
        Arrays.sort(sumB);
        long ans = 0;
        for (int i = 0; i < sumA.length; i++) {
            int hi = upperBound(T - sumA[i]);
            int lo = lowerBound(T - sumA[i]);
            ans += hi - lo;
        }
        System.out.println(ans);
    }
    public static int lowerBound(long goal) {
        int lo = 0;
        int hi = sumB.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (sumB[mid] >= goal) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    public static int upperBound(long goal) {
        int lo = 0;
        int hi = sumB.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (sumB[mid] > goal) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    public static long[] makeArr(int n, long[] arr) {
        long[] sum = new long[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                sum[idx++] = arr[j] - arr[j - i];
            }
        }
        return sum;
    }
}
