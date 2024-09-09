import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            String str = br.readLine();
            int s = 0;
            int e = str.length() - 1;
            boolean palindrome = true;
            while (s < e) {
                if (str.charAt(s) != str.charAt(e)) {
                    palindrome = false;
                    break;
                }
                s++;
                e--;
            }
            if (palindrome) {
                sb.append(0).append("\n");
                continue;
            }
            if (isPalindrome(str, s, e - 1) || isPalindrome(str, s + 1, e))
                sb.append(1).append("\n");
            else
                sb.append(2).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isPalindrome(String str, int s, int e) {
        while (s < e) {
            if (str.charAt(s++) != str.charAt(e--))
                return false;
        }
        return true;
    }
}
