import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16974 {

    static int N;
    static long X;
    static long cnt = 0;
    static long[] patty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());
        patty = new long[N + 1];
        long len = 1; // 총 햄버거 길이
        patty[0] = 1; // 단계 마다 패티 개수
        for (int i = 1; i <= N; i++) {
            len = len * 2 + 3;
            patty[i] = patty[i - 1] * 2 + 1;
        }
        ham(len, X, N);
        System.out.println(cnt);
    }

    public static void ham(long len, long x, int level) {
        long mid = len / 2 + 1;
        if (x < mid) { // 가운데 보다 작다
            if (x == 0) {
                return;
            }
            ham((len - 3) / 2, x - 1, level - 1);
        } else if (x == mid) { // 가운데
            cnt += patty[level] / 2 + 1;
        } else { // 가운데 보다 크다
            if (x == len) {
                cnt += patty[level];
                return;
            }
            cnt += patty[level] / 2 + 1;
            ham((len - 3) / 2, x - mid, level - 1);
        }
    }
}
