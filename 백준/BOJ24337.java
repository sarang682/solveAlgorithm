import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ24337 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // 불가능 한 경우
        if (a > N || b > N || a + b - 1 > N) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (a == 1 && b > a) {
            sb.append(b).append(" ");
            for (int i = 0; i < N - b; i++) {
                sb.append(1).append(" ");
            }
            for (int i = b - 1; i > 0; i--) {
                sb.append(i).append(" ");
            }
        } else if (a >= b) {
            for (int i = 0; i < N - a - b + 1; i++) {
                sb.append(1).append(" ");
            }
            for (int i = 1; i <= a; i++) {
                sb.append(i).append(" ");
            }
            for (int i = b - 1; i > 0; i--) {
                sb.append(i).append(" ");
            }
        } else {
            for (int i = 0; i < N - a - b + 1; i++) {
                sb.append(1).append(" ");
            }
            for (int i = 1; i < a; i++) {
                sb.append(i).append(" ");
            }
            for (int i = b; i > 0; i--) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }
}
