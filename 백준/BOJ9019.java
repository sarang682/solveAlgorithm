import java.io.*;
import java.util.*;

public class BOJ9019 {

    static class Info {
        int number;
        String order;

        public Info(int number, String order) {
            this.number = number;
            this.order = order;
        }
    }

    static int start, end;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            visit = new boolean[10_000];
            Queue<Info> queue = new LinkedList<>();
            queue.offer(new Info(start, ""));
            while (!queue.isEmpty()) {
                Info now = queue.poll();
                if (now.number == end) {
                    sb.append(now.order).append("\n");
                    break;
                }
                // D
                int nxt = (now.number * 2) % 10_000;
                if (!visit[nxt]) {
                    visit[nxt] = true;
                    queue.offer(new Info(nxt, now.order + "D"));
                }
                // S
                nxt = now.number - 1;
                if (nxt <= -1) nxt = 9999;
                if (!visit[nxt]) {
                    visit[nxt] = true;
                    visit[nxt] = true;
                    queue.offer(new Info(nxt, now.order + "S"));
                }
                int one = now.number / 1000;
                int two = (now.number % 1000) / 100;
                int three = (now.number % 100) / 10;
                int four = (now.number % 10);
                // L
                nxt = 0;
                nxt += two * 1000;
                nxt += three * 100;
                nxt += four * 10;
                nxt += one;
                if (!visit[nxt]) {
                    visit[nxt] = true;
                    queue.offer(new Info(nxt, now.order + "L"));
                }
                // R
                nxt = 0;
                nxt += four * 1000;
                nxt += one * 100;
                nxt += two * 10;
                nxt += three;
                if (!visit[nxt]) {
                    visit[nxt] = true;
                    queue.offer(new Info(nxt, now.order + "R"));
                }
            }
        }
        System.out.println(sb);
    }
}
