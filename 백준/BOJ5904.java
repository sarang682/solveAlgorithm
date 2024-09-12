import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = 0; // 만족하는 k찾기
        int len = 3;
        while (n > len) {
            k++;
            len = len * 2 + 3 + k;
        }
        moo(len, k, n);
    }

    public static void moo(int len, int k, int n) {
        int prev = (len - 3 - k) / 2; // 이전 단계 길이
        if (n < prev) {
            moo(prev, k - 1, n);
        } else if (n <= prev + 3 + k) {
            if (n == prev + 1)
                System.out.println("m");
            else
                System.out.println("o");
        } else {
            moo(prev, k - 1, n - (prev + 3 + k));
        }
    }
}
