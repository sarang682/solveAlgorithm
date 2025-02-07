import java.io.*;
import java.util.*;

public class BOJ12886 {

    static int A, B, C;
    static boolean[][][] visit;
    static int[][] pair = {{0, 1}, {0, 2}, {1, 2}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visit = new boolean[750][750][750];
        visit[A][B][C] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{A, B, C});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == now[1] && now[1] == now[2]) {
                System.out.println(1);
                return;
            }
            for (int i = 0; i < 3; i++) {
                int[] copy = exchange(now, pair[i][0], pair[i][1]);
                if (copy[0] >= 750 || copy[1] >= 750 || copy[2] >= 750) continue;
                if (!visit[copy[0]][copy[1]][copy[2]]) {
                    visit[copy[0]][copy[1]][copy[2]] = true;
                    queue.offer(new int[]{copy[0], copy[1], copy[2]});
                }
            }
        }
        System.out.println(0);
    }

    public static int[] exchange(int[] origin, int a, int b) { // a번째 b번째 교환
        int[] copy = new int[3];
        copy[0] = origin[0];
        copy[1] = origin[1];
        copy[2] = origin[2];
        if (copy[a] > copy[b]) {
            copy[a] -= copy[b];
            copy[b] *= 2;
        } else if (origin[b] > origin[a]) {
            copy[b] -= copy[a];
            copy[a] *= 2;
        }
        return copy;
    }
}
