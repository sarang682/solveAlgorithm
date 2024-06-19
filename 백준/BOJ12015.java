import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12015 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int L = 0;
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n > arr[L]) { // 추가
                arr[++L] = n;
            } else if (n < arr[L]) { // 교체
                int idx = binarySearch(n, L, arr);
                arr[idx] = n;
            }
        }
        System.out.println(L);
    }

    static public int binarySearch(int target, int L, int[] arr) {
        int lo = 1;
        int hi = L;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (target <= arr[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
