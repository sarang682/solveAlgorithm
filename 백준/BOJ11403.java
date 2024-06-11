import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11403 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        boolean[][] adj;
        N = Integer.parseInt(br.readLine());
        adj = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                adj[i][j]= input[j].equals("1");
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(adj[i][k] && adj[k][j])
                        adj[i][j]=true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(adj[i][j]) sb.append("1 ");
                else sb.append("0 ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
