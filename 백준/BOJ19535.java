import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ19535 {

    static int N;
    static ArrayList<int[]> edge;
    static long[] cnt;
    static long D = 0, G = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edge = new ArrayList<>();
        cnt = new long[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cnt[a]++;
            cnt[b]++;
            edge.add(new int[]{a, b});
        }
        // ㅈ 개수
        for (int i = 1; i <= N; i++) {
            if (cnt[i] >= 3)
                G += cnt[i] * (cnt[i] - 1) * (cnt[i] - 2) / 6;
        }
        // ㄷ 개수
        for (int[] e : edge) {
            D += (cnt[e[0]] - 1) * (cnt[e[1]] - 1);
        }
        if (D > G * 3) {
            System.out.println("D");
        } else if (D < G * 3) {
            System.out.println("G");
        } else {
            System.out.println("DUDUDUNGA");
        }
    }
}
