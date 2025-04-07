import java.io.*;
import java.util.*;

public class BOJ19236 {

    static int[] dx = {9999, -1, -1, 0, 1, 1, 1, 0, -1}, dy = {9999, 0, -1, -1, -1, 0, 1, 1, 1};

    static class Fish {
        int no;
        int dir;

        public Fish(int no, int dir) {
            this.no = no;
            this.dir = dir;
        }
    }

    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[][] map = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[i][j] = new Fish(a, b);
            }
        }
        dfs(map, 0, 0, 0);
        System.out.println(ans);
    }

    public static void dfs(Fish[][] map, int shark_x, int shark_y, int feed) {
        // 복사@@@
        Fish[][] copy_map = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy_map[i][j] = new Fish(map[i][j].no, map[i][j].dir);
            }
        }
        // 물고기 먹기@@@
        int shark_d = copy_map[shark_x][shark_y].dir;
        feed += copy_map[shark_x][shark_y].no;
        copy_map[shark_x][shark_y].no = 0;

        // 물고기 이동@@@
        move(copy_map, shark_x, shark_y);
        // 상어 이동@@@
        while (true) {
            shark_x += dx[shark_d];
            shark_y += dy[shark_d];
            if (shark_x < 0 || shark_x >= 4 || shark_y < 0 || shark_y >= 4) break;
            if (copy_map[shark_x][shark_y].no == 0) continue;
            dfs(copy_map, shark_x, shark_y, feed);
        }
        ans = Math.max(ans, feed);
    }

    public static void move(Fish[][] map, int shark_x, int shark_y) {
        for (int i = 1; i <= 16; i++) { // i 번 물고기 이동
            boolean move = false;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (move) break;
                    if (map[j][k].no == i) {
                        move = true;
                        for (int d = 0; d < 8; d++) {
                            int nd = map[j][k].dir + d;
                            if (nd > 8) {
                                nd = nd % 8;
                            }
                            int nx = j + dx[nd];
                            int ny = k + dy[nd];
                            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
                            if (nx == shark_x && ny == shark_y) continue;
                            // swap
                            map[j][k].dir = nd;
                            Fish tmp = new Fish(map[j][k].no, map[j][k].dir);
                            map[j][k] = map[nx][ny];
                            map[nx][ny] = tmp;
                            break;
                        }
                    }
                }
            }
        }
    }
}
