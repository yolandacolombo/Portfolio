import java.util.*;

public class QuebraCabeca {
    private int[][] tabuleiro;
    private int linhaVazia, colunaVazia;

    private static final int[][] OBJETIVO = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0}
    };

    public QuebraCabeca(int[][] tab) {
        tabuleiro = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tabuleiro[i][j] = tab[i][j];
                if (tab[i][j] == 0) {
                    linhaVazia = i;
                    colunaVazia = j;
                }
            }
        }
    }

    public List<QuebraCabeca> gerarVizinhos() {
        List<QuebraCabeca> lista = new ArrayList<>();
        int[][] moves = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] m : moves) {
            int novaLinha = linhaVazia + m[0];
            int novaColuna = colunaVazia + m[1];
            if (novaLinha >= 0 && novaLinha < 4 && novaColuna >= 0 && novaColuna < 4) {
                int[][] novoTab = copiar();
                novoTab[linhaVazia][colunaVazia] = novoTab[novaLinha][novaColuna];
                novoTab[novaLinha][novaColuna] = 0;
                lista.add(new QuebraCabeca(novoTab));
            }
        }
        return lista;
    }

    public boolean eObjetivo() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tabuleiro[i][j] != OBJETIVO[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Heurística: distância de Manhattan
    public int heuristica() {
        int dist = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int val = tabuleiro[i][j];
                if (val != 0) {
                    int linhaObjetivo = (val - 1) / 4;
                    int colunaObjetivo = (val - 1) % 4;
                    dist += Math.abs(i - linhaObjetivo) + Math.abs(j - colunaObjetivo);
                }
            }
        }
        return dist;
    }

    private int[][] copiar() {
        int[][] novo = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                novo[i][j] = tabuleiro[i][j];
            }
        }
        return novo;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                if (tabuleiro[i][j]==0) sb.append("   ");
                else sb.append(String.format("%2d ", tabuleiro[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
