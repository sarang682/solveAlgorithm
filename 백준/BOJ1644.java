import java.io.*;
import java.util.*;

public class BOJ1644 {

    static int N;
    static boolean[] isPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                for (long j = (long) i * i; j <= N; j += i) isPrime[(int) j] = false;
            }
        }
        ArrayList<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                prime.add(i);
            }
        }
        int l = 0;
        int r = 0;
        int sum = prime.get(0);
        int ans = 0;
        while (true) {
            if(sum==N) ans++;
            if (sum <= N) {
                r++;
                if (r >= prime.size()) break;
                sum += prime.get(r);
            } else {
                sum -= prime.get(l);
                l++;
            }
        }
        System.out.println(ans);
    }
}
