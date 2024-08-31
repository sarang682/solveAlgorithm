import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2879 {

    static int N;
    static int[] a, b, diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N + 1];
        b = new int[N + 1];
        diff = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
            diff[i] = b[i] - a[i];
        }
        int idx = 1;
        int ans = 0;
        while (idx <= N) {
            while (diff[idx] > 0) {
                ans++;
                for (int i = idx; i <= N; i++) {
                    if (diff[i] > 0) diff[i]--;
                    else break;
                }
            }
            while (diff[idx] < 0) {
                ans++;
                for (int i = idx; i <= N; i++) {
                    if (diff[i] < 0) diff[i]++;
                    else break;
                }
            }
            idx++;
        }
        System.out.println(ans);
    }
}
