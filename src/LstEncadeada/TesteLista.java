package LstEncadeada;

import Comparators.ComparadorAlunoPorMatricula;
import Comparators.ComparadorAlunoPorNome;

import java.util.Comparator;

public class TesteLista {

    public static void main(String[] args) {
        // Alunos de exemplo para teste
        Aluno a1 = new Aluno(101, "Lucas");
        Aluno a2 = new Aluno(103, "Ana");
        Aluno a3 = new Aluno(102, "Bruno");

        // Comparadores para os testes
        Comparator<Aluno> comparadorNome = new ComparadorAlunoPorNome();
        Comparator<Aluno> comparadorMatricula = new ComparadorAlunoPorMatricula();

        System.out.println("--- TESTANDO LISTA NÃO ORDENADA ---");
        // Criação de uma lista não ordenada
        ListaEncadeada<Aluno> listaNaoOrdenada = new ListaEncadeada<>(false, comparadorNome);

        // Testando inserção no final
        System.out.println("Inserindo elementos...");
        listaNaoOrdenada.inserirElemento(a1);
        listaNaoOrdenada.inserirElemento(a2);
        listaNaoOrdenada.inserirElemento(a3);
        System.out.println("Lista após inserções: " + listaNaoOrdenada); // Esperado: [Lucas (101), Ana (103), Bruno (102)]

        // Testando busca
        System.out.println("\n--- TESTANDO BUSCA NA LISTA NÃO ORDENADA ---");
        Aluno alunoBusca1 = new Aluno(103, "Ana");
        System.out.println("Buscando 'Ana' (103): " + listaNaoOrdenada.pesquisar(alunoBusca1)); // Esperado: Ana (103)
        Aluno alunoBusca2 = new Aluno(999, "Inexistente");
        System.out.println("Buscando 'Inexistente' (999): " + listaNaoOrdenada.pesquisar(alunoBusca2)); // Esperado: null

        // Testando remoção
        System.out.println("\n--- TESTANDO REMOÇÃO NA LISTA NÃO ORDENADA ---");
        Aluno alunoRemocao1 = new Aluno(103, "Ana");
        System.out.println("Removendo 'Ana' (103): " + listaNaoOrdenada.removeElemento(alunoRemocao1));
        System.out.println("Lista após remoção: " + listaNaoOrdenada); // Esperado: [Lucas (101), Bruno (102)]

        Aluno alunoRemocao2 = new Aluno(101, "Lucas");
        System.out.println("Removendo 'Lucas' (101): " + listaNaoOrdenada.removeElemento(alunoRemocao2));
        System.out.println("Lista após remoção: " + listaNaoOrdenada); // Esperado: [Bruno (102)]

        System.out.println("\n\n--- TESTANDO LISTA ORDENADA POR MATRÍCULA ---");
        // Criação de uma lista ordenada por matrícula
        ListaEncadeada<Aluno> listaOrdenadaMatricula = new ListaEncadeada<>(true, comparadorMatricula);

        // Testando inserção ordenada
        System.out.println("Inserindo elementos fora de ordem...");
        listaOrdenadaMatricula.inserirElemento(a1); // 101
        listaOrdenadaMatricula.inserirElemento(a3); // 102
        listaOrdenadaMatricula.inserirElemento(a2); // 103
        System.out.println("Lista após inserções: " + listaOrdenadaMatricula); // Esperado: [Lucas (101), Bruno (102), Ana (103)]

        // Testando busca
        System.out.println("\n--- TESTANDO BUSCA NA LISTA ORDENADA ---");
        System.out.println("Buscando 'Ana' (103): " + listaOrdenadaMatricula.pesquisar(alunoBusca1)); // Esperado: Ana (103)
        System.out.println("Buscando 'Inexistente' (999): " + listaOrdenadaMatricula.pesquisar(alunoBusca2)); // Esperado: null

        // Testando remoção
        System.out.println("\n--- TESTANDO REMOÇÃO NA LISTA ORDENADA ---");
        System.out.println("Removendo 'Ana' (103): " + listaOrdenadaMatricula.removeElemento(alunoRemocao1));
        System.out.println("Lista após remoção: " + listaOrdenadaMatricula); // Esperado: [Lucas (101), Bruno (102)]

        System.out.println("Removendo 'Lucas' (101): " + listaOrdenadaMatricula.removeElemento(alunoRemocao2));
        System.out.println("Lista após remoção: " + listaOrdenadaMatricula); // Esperado: [Bruno (102)]
    }
}