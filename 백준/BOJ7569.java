import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7569 {

    static int M, N, H, total, cnt;
    static int[][][] tomato;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);
        tomato = new int[H][N][M];
        total = N * M * H; // 총 토마토 개수
        cnt = 0; // 익은 토마토 개수
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) { // 높이
            for (int j = 0; j < N; j++) { // 세로
                input = br.readLine().split(" ");
                for (int k = 0; k < M; k++) { // 가로
                    tomato[i][j][k] = Integer.parseInt(input[k]);
                    if (tomato[i][j][k] == -1) {
                        total--;
                    } else if (tomato[i][j][k] == 1) {
                        queue.offer(new int[]{i, j, k, 0});
                        cnt++;
                    }
                }
            }
        }
        if (cnt == total) {
            System.out.println(0);
            return;
        }
        // bfs
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 6; i++) { // 6방향 탐색
                int next_z = now[0] + dz[i];
                int next_y = now[1] + dy[i];
                int next_x = now[2] + dx[i];
                if (next_z >= H || next_y >= N || next_x >= M || next_z < 0 || next_y < 0 || next_x < 0) continue;
                if (tomato[next_z][next_y][next_x] == 0) { // 토마도 안익음!
                    queue.offer(new int[]{next_z, next_y, next_x, now[3] + 1});
                    tomato[next_z][next_y][next_x] = 1;
                    cnt++;
                    if (cnt >= total) {
                        System.out.println(now[3] + 1);
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
