import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4256 {

    static int N;
    static int[] pre, in;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            pre = new int[N + 1];
            in = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pre[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                in[i] = Integer.parseInt(st.nextToken());
            }
            find(0, 0, N);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void find(int root, int start, int end) {
        int r = pre[root];
        for (int i = start; i < end; i++) {
            if (in[i] == r) {
                find(root + 1, start, i); // 왼쪽
                find(root + (i - start + 1), i + 1, end); // 오른쪽
                sb.append(r).append(" ");
                break;
            }
        }
    }
}
