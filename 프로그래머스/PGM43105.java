class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int L = triangle.length;
        int[][] dp = new int[L + 1][L + 1];
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = triangle[i - 1][j - 1] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                if (dp[i][j] > answer)
                    answer = dp[i][j];
            }
        }
        return answer;
    }
}
