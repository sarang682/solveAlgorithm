import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] loc = new int[N];
        for (int i = 0; i < N; i++) {
            loc[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(loc);
        int[] dis = new int[N - 1];
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            dis[i] = loc[i + 1] - loc[i];
            sum += dis[i];
        }
        Arrays.sort(dis);
        for (int i = 0; i < K - 1; i++) {
            if (N - 2 - i < 0) break;
            sum -= dis[N - 2 - i];
        }
        System.out.println(sum);
    }
}
