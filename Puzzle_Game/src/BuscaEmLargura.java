import java.util.*;

public class BuscaEmLargura {
    public static void busca(QuebraCabeca inicial) {
        Queue<QuebraCabeca> fila = new LinkedList<>();
        Map<QuebraCabeca, QuebraCabeca> pai = new HashMap<>(); // mapeia filho → pai
        Set<QuebraCabeca> visitados = new HashSet<>();
        int estadosExpandidos = 0;

        fila.add(inicial);
        visitados.add(inicial);
        pai.put(inicial, null);

        QuebraCabeca objetivo = null;

        while (!fila.isEmpty()) {
            QuebraCabeca atual = fila.poll();
            estadosExpandidos++;

            if (atual.eObjetivo()) {
                objetivo = atual;
                break;
            }

            for (QuebraCabeca vizinho : atual.gerarVizinhos()) {
                if (!visitados.contains(vizinho)) {
                    fila.add(vizinho);
                    visitados.add(vizinho);
                    pai.put(vizinho, atual); 
                }
            }
        }

        if (objetivo == null) {
            System.out.println("Não foi possível encontrar solução.");
            return;
        }

        List<QuebraCabeca> caminho = new ArrayList<>();
        QuebraCabeca atual = objetivo;
        while (atual != null) {
            caminho.add(atual);
            atual = pai.get(atual);
        }
        Collections.reverse(caminho);

        int passo = 0;
        for (QuebraCabeca estado : caminho) {
            System.out.println("Passo " + passo + ":");
            System.out.println(estado);
            passo++;
        }

        System.out.println("[Busca em Largura] Solucao encontrada em " + (caminho.size() - 1) + " movimentos e com " + (estadosExpandidos++) + " estados expandidos");
    }
}
