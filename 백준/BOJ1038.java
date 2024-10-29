import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ1038 {

    static int N;
    static List<Long> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();
        nums.add(0L);
        for (int i = 1; i <= 9; i++) {
            List<Long> list = new ArrayList<>();
            for (long n : nums) {
                list.add(Long.parseLong("" + i + "" + n));
            }
            nums.add((long) i);
            nums.addAll(list);
        }
        Collections.sort(nums);
        if (N >= nums.size()) {
            System.out.println(-1);
        } else {
            System.out.println(nums.get(N));
        }
    }
}
