import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477 {

    static int N, M, L;
    static int[] loc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        loc = new int[N + 2];
        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                loc[i] = Integer.parseInt(st.nextToken());
            }
        }
        loc[0] = 0;
        loc[N + 1] = L;
        Arrays.sort(loc);
        int hi = 0;
        for (int i = 1; i <= N + 1; i++) {
            int dis = loc[i] - loc[i - 1];
            hi = Math.max(hi, dis);
        }
        int lo = 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (!chk(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        System.out.println(lo);
    }

    public static boolean chk(int L) {
        int cnt = 0;
        for (int i = 0; i <= N; i++) {
            int idx = loc[i];
            while (true) {
                idx += L;
                if (idx >= loc[i + 1])
                    break;
                cnt++;
            }
            if (cnt > M)
                return false;
        }
        return true;
    }
}
