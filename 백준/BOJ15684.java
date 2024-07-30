import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {

    static int N, M, H, LADDER_SIZE;
    static boolean flag = false;
    static boolean[] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        LADDER_SIZE = (N - 1) * H;
        ladder = new boolean[LADDER_SIZE];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            ladder[H * (n - 1) + (h - 1)] = true;
        }
        for (int i = 0; i <= 3; i++) {
            combination(0, i, 0);
            if (flag) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    public static void combination(int cnt, int r, int start) {
        if (flag) return;
        if (cnt == r) {
            if (chk()) flag = true;
            return;
        }
        for (int i = start; i < LADDER_SIZE; i++) {
            if (!ladder[i] && (i - H < 0 || !ladder[i - H])) {
                ladder[i] = true;
                combination(cnt + 1, r, i + 1);
                ladder[i] = false;
            }
        }
    }

    public static boolean chk() {
        for (int i = 0; i < N; i++) {
            int line = i;
            int idx = 0;
            while (idx < H) {
                int left = H * (line - 1) + idx;
                if (left >= 0 && ladder[left])
                    line--;
                else {
                    int right = H * line + idx;
                    if (right < LADDER_SIZE && ladder[right])
                        line++;
                }
                idx++;
            }
            if (i != line)
                return false;
        }
        return true;
    }
}
