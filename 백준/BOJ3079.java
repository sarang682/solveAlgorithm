import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3079 {

    static int N, M;
    static long[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new long[N];
        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(time);
        long lo = time[0];
        long hi = time[0] * M;
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if (!chk(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        System.out.println(lo);
    }

    public static boolean chk(long t) {
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt += t / time[i];
            if (cnt >= M)
                return true;
        }
        return false;
    }
}
