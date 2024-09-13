import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1765 {

    static int N, M;
    static char[][] relation;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        relation = new char[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char sign = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a][b] = sign;
            relation[b][a] = sign;
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (relation[i][k] == 'F' && relation[k][j] == 'F')
                        relation[i][j] = 'F';
                    if (relation[i][k] == 'E' && relation[k][j] == 'E')
                        relation[i][j] = 'F';
                }
            }
        }
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (relation[i][j] == 'F') {
                    if (find(i) != find(j))
                        union(i, j);
                }
            }
        }
        int idx = 0;
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (parent[i] > idx) {
                idx = parent[i];
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}
