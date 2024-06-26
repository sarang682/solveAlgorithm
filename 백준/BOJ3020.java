import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] odd = new int[N / 2 + 1];
        int[] even = new int[N / 2 + 1];

        for (int i = 1; i <= N; i++) {
            int in = Integer.parseInt(br.readLine());
            if (i % 2 == 1)
                odd[i / 2 + 1] = in;
            else
                even[i / 2] = in;
        }
        Arrays.sort(odd);
        Arrays.sort(even);
        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for (int i = 1; i <= H; i++) {
            int val = binarySearch(H + 1 - i, N / 2, even) + binarySearch(i, N / 2, odd);
            if (val == min) {
                cnt++;
            } else if (val < min) {
                min = val;
                cnt = 1;
            }
        }
        System.out.println(min + " " + cnt);
    }

    static int binarySearch(int h, int n, int[] arr) {
        if (arr[n] < h) return 0;
        int lo = 1;
        int hi = n;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < h) { // 가능한 경우
                lo = mid + 1;
            } else { // 불가능한 경우
                hi = mid;
            }
        }
        return n - lo + 1;
    }
}
