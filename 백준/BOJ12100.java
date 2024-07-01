import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100 {
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, map, N);
        System.out.println(max);
    }

    static public void dfs(int depth, int[][] map, int N) {
        if (depth >= 5) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > max)
                        max = map[i][j];
                }
            }
            return;
        }
        for (int d = 0; d < 4; d++) {
            int[][] copy = new int[N + 1][N + 1];
            for (int i = 0; i <= N; i++)
                copy[i] = map[i].clone();
            switch (d) {
                case 0:
                    move_up(copy, N);
                    break;
                case 1:
                    move_down(copy, N);
                    break;
                case 2:
                    move_left(copy, N);
                    break;
                case 3:
                    move_right(copy, N);
                    break;
                default:
            }
            dfs(depth + 1, copy, N);
        }
    }

    static public void move_up(int[][] map, int N) {
        for (int i = 1; i <= N; i++) {
            int space = 0;
            int idx = 0;
            for (int j = 1; j <= N; j++) {
                if (map[j][i] == 0) { // 0인 경우
                    space++;
                    continue;
                }
                if (map[j][i] == map[idx][i]) { // 합치는 경우
                    map[idx][i] *= 2;
                    map[j][i] = 0;
                    space++;
                    idx = 0;
                } else { // 합치지 않는 경우
                    if (space > 0) {
                        map[j - space][i] = map[j][i];
                        map[j][i] = 0;
                        idx = j - space;
                    } else {
                        idx = j;
                    }
                }
            }
        }
    }

    static public void move_down(int[][] map, int N) {
        for (int i = 1; i <= N; i++) {
            int space = 0;
            int idx = 0;
            for (int j = N; j >= 1; j--) {
                if (map[j][i] == 0) { // 0인 경우
                    space++;
                    continue;
                }
                if (map[j][i] == map[idx][i]) { // 합치는 경우
                    map[idx][i] *= 2;
                    map[j][i] = 0;
                    space++;
                    idx = 0;
                } else { // 합치지 않는 경우
                    if (space > 0) {
                        map[j + space][i] = map[j][i];
                        map[j][i] = 0;
                        idx = j + space;
                    } else {
                        idx = j;
                    }
                }
            }
        }
    }

    static public void move_left(int[][] map, int N) {
        for (int i = 1; i <= N; i++) {
            int space = 0;
            int idx = 0;
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 0) { // 0인 경우
                    space++;
                    continue;
                }
                if (map[i][j] == map[i][idx]) { // 합치는 경우
                    map[i][idx] *= 2;
                    map[i][j] = 0;
                    space++;
                    idx = 0;
                } else { // 합치지 않는 경우
                    if (space > 0) {
                        map[i][j - space] = map[i][j];
                        map[i][j] = 0;
                        idx = j - space;
                    } else {
                        idx = j;
                    }
                }
            }
        }
    }

    static public void move_right(int[][] map, int N) {
        for (int i = 1; i <= N; i++) {
            int space = 0;
            int idx = 0;
            for (int j = N; j >= 1; j--) {
                if (map[i][j] == 0) { // 0인 경우
                    space++;
                    continue;
                }
                if (map[i][j] == map[i][idx]) { // 합치는 경우
                    map[i][idx] *= 2;
                    map[i][j] = 0;
                    space++;
                    idx = 0;
                } else { // 합치지 않는 경우
                    if (space > 0) {
                        map[i][j + space] = map[i][j];
                        map[i][j] = 0;
                        idx = j + space;
                    } else {
                        idx = j;
                    }
                }
            }
        }
    }
}
