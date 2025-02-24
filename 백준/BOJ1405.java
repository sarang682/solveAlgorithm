import java.util.*;
import java.io.*;

public class BOJ1405 {

    static int N;
    static double[] probability;
    static double total = 0;
    static int[] dy = {0, 0, 1, -1}, dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        probability = new double[4];
        probability[0] = Integer.parseInt(st.nextToken()) / 100.0;
        probability[1] = Integer.parseInt(st.nextToken()) / 100.0;
        probability[2] = Integer.parseInt(st.nextToken()) / 100.0;
        probability[3] = Integer.parseInt(st.nextToken()) / 100.0;
        boolean[][] visit = new boolean[100][100];
        visit[50][50]= true;
        dfs(0, 1, 50, 50, visit);
        System.out.printf("%.9f", total);
    }

    public static void dfs(int depth, double pro, int y, int x, boolean[][] visit) {
        if (depth >= N) {
            total += pro;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (visit[ny][nx]) continue;
            visit[ny][nx] = true;
            dfs(depth + 1, pro * probability[i], ny, nx, visit);
            visit[ny][nx] = false;
        }
    }
}
