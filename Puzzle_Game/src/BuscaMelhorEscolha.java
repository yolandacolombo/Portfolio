import java.util.*;

public class BuscaMelhorEscolha {
    public static void busca(QuebraCabeca inicial) {
        // Fila de prioridade. Ordena pelo valor de f(n) = g(n) + h(n) 
        PriorityQueue<Nodo> fila = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Map<QuebraCabeca, QuebraCabeca> pai = new HashMap<>();
        Map<QuebraCabeca, Integer> custoGasto = new HashMap<>();
        Set<QuebraCabeca> visitados = new HashSet<>();
        int estadosExpandidos = 0;

        custoGasto.put(inicial, 0);
        fila.add(new Nodo(inicial, inicial.heuristica()));
        pai.put(inicial, null);

        QuebraCabeca objetivo = null;

        while (!fila.isEmpty()) {
            Nodo nodoAtual = fila.poll();
            QuebraCabeca atual = nodoAtual.estado;

            if (atual.eObjetivo()) {
                objetivo = atual;
                break;
            }

            if (visitados.contains(atual)) continue;
            visitados.add(atual);
            estadosExpandidos++;

            int custoAtual = custoGasto.get(atual);

            for (QuebraCabeca vizinho : atual.gerarVizinhos()) {
                int custoNovo = custoAtual + 1;
                if (!custoGasto.containsKey(vizinho) || custoNovo < custoGasto.get(vizinho)) {
                    custoGasto.put(vizinho, custoNovo);
                    int f = custoNovo + vizinho.heuristica();
                    fila.add(new Nodo(vizinho, f));
                    pai.put(vizinho, atual);
                }
            }
        }

        if (objetivo == null) {
            System.out.println("Não foi possível encontrar solução.");
            return;
        }

        // Reconstrução do caminho
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

        System.out.println("[Busca Melhor Escolha] Solucao encontrada em " + (caminho.size() - 1) + " movimentos e com " + (estadosExpandidos++) + " estados expandidos");
    }

    // Classe auxiliar para armazenar estado + f(n)
    private static class Nodo {
        QuebraCabeca estado;
        int f;
        Nodo(QuebraCabeca e, int f) {
            this.estado = e;
            this.f = f;
        }
    }
}
