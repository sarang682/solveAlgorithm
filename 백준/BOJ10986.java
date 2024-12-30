import java.io.*;
import java.util.*;

public class BOJ10986 {

    static int N, M;
    static int[] nums, remain;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];
        remain = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = (nums[i - 1] + Integer.parseInt(st.nextToken())) % M;
            remain[nums[i]]++;
        }
        long ans = remain[0];
        for (int i = 0; i < M; i++) {
            int cnt = remain[i];
            ans += (long) cnt * (cnt - 1) / 2;
        }
        System.out.println(ans);
    }
}
