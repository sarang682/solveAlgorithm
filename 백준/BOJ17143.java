import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17143 {

    static class Fish {
        int r;
        int c;
        int s;
        int d;
        int z;

        public Fish(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int R, C, M, ans;
    static Fish[][] map;
    // 위 아래 오른쪽 왼쪽
    static int[] dir = {0, 2, 1, 4, 3}, dr = {0, -1, 1, 0, 0}, dc = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Fish[R][C];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            map[r][c] = new Fish(r, c, s, d, z);
        }
        ans = 0;
        for (int i = 0; i < C; i++) {
            play(i);
        }
        System.out.println(ans);
    }

    public static void play(int col) {
        // 물고기 잡기
        for (int i = 0; i < R; i++) {
            if (map[i][col] != null) {
                ans += map[i][col].z;
                map[i][col] = null;
                break;
            }
        }
        // 물고기 이동
        Queue<Fish> queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != null) {
                    queue.offer(map[i][j]);
                    map[i][j] = null;
                }
            }
        }
        while (!queue.isEmpty()) {
            Fish f = queue.poll();
            int nxt_r = f.r + f.s * dr[f.d];
            int nxt_c = f.c + f.s * dc[f.d];
            if (nxt_r < 0 || nxt_r >= R || nxt_c < 0 || nxt_c >= C) {
                int diff = 0;
                if (nxt_r < 0) {
                    diff = -1 * nxt_r;
                } else if (nxt_c < 0) {
                    diff = -1 * nxt_c;
                } else if (nxt_r >= R) {
                    diff = nxt_r - R + 1;
                } else {
                    diff = nxt_c - C + 1;
                }

                //
                if (nxt_r < 0 || nxt_r >= R) {
                    if ((diff / (R - 1)) % 2 == 0) { // 반대 방향
                        f.d = dir[f.d];
                    }
                } else {
                    if ((diff / (C - 1)) % 2 == 0) { // 반대 방향
                        f.d = dir[f.d];
                    }
                }

                //
                int move = 0;
                if (nxt_r < 0 || nxt_r >= R) {
                    move = diff % (R - 1);
                } else {
                    move = diff % (C - 1);
                }

                //
                if (f.d == 1) { // 상
                    f.r = R - 1 - move;
                } else if (f.d == 2) { // 하
                    f.r = move;
                } else if (f.d == 3) { // 우
                    f.c = move;
                } else if (f.d == 4) { // 좌
                    f.c = C - 1 - move;
                }

            } else {
                f.r = nxt_r;
                f.c = nxt_c;
            }
            //
            if (map[f.r][f.c] == null) {
                map[f.r][f.c] = f;
            } else {
                if (map[f.r][f.c].z < f.z)
                    map[f.r][f.c] = f;
            }
        }

    }
}
