import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {

    static boolean[][] adj;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        adj = new boolean[N + 1][N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (st.nextToken().equals("1"))
                    adj[i][j] = true;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (adj[i][j])
                    if (find(i) != find(j))
                        union(i, j);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int goal = find(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < M; i++) {
            if (find(Integer.parseInt(st.nextToken())) != goal) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static public int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

    static public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y)
            parent[y] = x;
        else
            parent[x] = y;
    }
}
