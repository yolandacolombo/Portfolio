public class App {
    public static void main(String[] args) {
        int[][] inicial = {
            {5, 1, 3, 4},
            {9, 0, 6, 8},
            {13, 2, 7, 12},
            {14, 10, 11, 15}
        };

        QuebraCabeca jogo = new QuebraCabeca(inicial);

        System.out.println("Estado inicial:");
        System.out.println(jogo);

        BuscaEmLargura.busca(jogo);
        BuscaMelhorEscolha.busca(jogo);
    }
}
