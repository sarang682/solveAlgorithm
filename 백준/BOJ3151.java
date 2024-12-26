import java.io.*;
import java.util.*;

public class BOJ3151 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = -1 * (arr[i] + arr[j]);
                int l = lower_bind(j + 1, sum);
                int r = upper_bind(j + 1, sum);
                ans += r - l;
            }
        }
        System.out.println(ans);
    }

    public static int lower_bind(int lo, int t) {
        int hi = N;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < t) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static int upper_bind(int lo, int t) {
        int hi = N;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= t) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
