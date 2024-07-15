import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ2141 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] town = new long[N][2];
        long total = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            town[i][0] = x;
            town[i][1] = a;
            total += a;
        }
        Arrays.sort(town, Comparator.comparingLong(o -> o[0]));
        long left = 0;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            left += town[i][1];
            if ((total + 1) / 2 <= left) {
                ans = town[i][0];
                break;
            }
        }
        System.out.println(ans);
    }
}
