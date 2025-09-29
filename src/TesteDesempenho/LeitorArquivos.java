package TesteDesempenho;

import LstEncadeada.Aluno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeitorArquivos {
    private static final String NOME_ARQUIVO = "alunosBalanceados.txt";

    public static void main(String[] args) {
        List<Aluno> arrayList = new ArrayList<>();
        List<Aluno> linkedList = new LinkedList<>();

        long startTime, endTime;

        System.out.println("--- Teste de Desempenho de Listas ---");
        System.out.println("Lendo arquivo e populando as listas...");

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            int numRegistros = Integer.parseInt(reader.readLine().trim());
            String linha;

            startTime = System.currentTimeMillis();
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];

                Aluno aluno = new Aluno(id, nome);
                arrayList.add(aluno);
                linkedList.add(aluno);
            }
            endTime = System.currentTimeMillis();
            System.out.println("Tempo total para popular as listas: " + (endTime - startTime) + " ms\n");

        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler ou processar o arquivo: " + e.getMessage());
            return;
        }

        // --- Variáveis para os testes de Inserção e Busca ---
        Aluno novoAluno = new Aluno(99999999, "Chico Bento");
        int tamanho = arrayList.size();
        int posicaoMeio = tamanho / 2;

        // --- Variáveis para armazenar os resultados dos testes ---
        long arrayListInsertFim, linkedListInsertFim;
        long arrayListInsertInicio, linkedListInsertInicio;
        long arrayListInsertMeio, linkedListInsertMeio;
        long arrayListBuscaUltimo, linkedListBuscaUltimo;
        long arrayListBuscaPenultimo, linkedListBuscaPenultimo;
        long arrayListBuscaMeio, linkedListBuscaMeio;

        // --- TESTES DE INSERÇÃO ---
        System.out.println("Executando testes de inserção e busca...");

        // Inserção no Fim
        startTime = System.currentTimeMillis();
        arrayList.add(novoAluno);
        endTime = System.currentTimeMillis();
        arrayListInsertFim = endTime - startTime;

        startTime = System.currentTimeMillis();
        linkedList.add(novoAluno);
        endTime = System.currentTimeMillis();
        linkedListInsertFim = endTime - startTime;

        // Inserção no Início (posição 0)
        startTime = System.currentTimeMillis();
        arrayList.add(0, novoAluno);
        endTime = System.currentTimeMillis();
        arrayListInsertInicio = endTime - startTime;

        startTime = System.currentTimeMillis();
        linkedList.add(0, novoAluno);
        endTime = System.currentTimeMillis();
        linkedListInsertInicio = endTime - startTime;

        // Inserção no Meio
        startTime = System.currentTimeMillis();
        arrayList.add(posicaoMeio, novoAluno);
        endTime = System.currentTimeMillis();
        arrayListInsertMeio = endTime - startTime;

        startTime = System.currentTimeMillis();
        linkedList.add(posicaoMeio, novoAluno);
        endTime = System.currentTimeMillis();
        linkedListInsertMeio = endTime - startTime;


        // --- TESTES DE BUSCA ---
        // Busca do Último Elemento
        startTime = System.nanoTime();
        arrayList.get(tamanho - 1);
        endTime = System.nanoTime();
        arrayListBuscaUltimo = endTime - startTime;

        startTime = System.nanoTime();
        linkedList.get(tamanho - 1);
        endTime = System.nanoTime();
        linkedListBuscaUltimo = endTime - startTime;

        // Busca do Penúltimo Elemento
        startTime = System.nanoTime();
        arrayList.get(tamanho - 2);
        endTime = System.nanoTime();
        arrayListBuscaPenultimo = endTime - startTime;

        startTime = System.nanoTime();
        linkedList.get(tamanho - 2);
        endTime = System.nanoTime();
        linkedListBuscaPenultimo = endTime - startTime;

        // Busca do Elemento do Meio
        startTime = System.nanoTime();
        arrayList.get(posicaoMeio);
        endTime = System.nanoTime();
        arrayListBuscaMeio = endTime - startTime;

        startTime = System.nanoTime();
        linkedList.get(posicaoMeio);
        endTime = System.nanoTime();
        linkedListBuscaMeio = endTime - startTime;

        System.out.println("Testes concluídos.\n");
        System.out.println("Tamanho da lista: \n" + tamanho);

        // --- Exibição dos resultados em formato de tabela ---
        String linhaSeparadora = "+-------------------------+---------------+--------------+";
        String cabecalho = "| Operação                | ArrayList (ns)| LinkedList (ns)|";

        System.out.println(linhaSeparadora);
        System.out.println(cabecalho);
        System.out.println(linhaSeparadora);

        // Formato da linha: %-25s para uma string de 25 caracteres alinhada à esquerda
        //                   %15d para um número de 15 caracteres alinhado à direita
        //                   %n para uma nova linha
        String formatoLinha = "| %-23s | %13d | %12d |%n";

        // Inserções
        System.out.printf(formatoLinha, "Inserção no Fim", arrayListInsertFim, linkedListInsertFim);
        System.out.printf(formatoLinha, "Inserção no Início", arrayListInsertInicio, linkedListInsertInicio);
        System.out.printf(formatoLinha, "Inserção no Meio", arrayListInsertMeio, linkedListInsertMeio);

        System.out.println(linhaSeparadora);

        // Buscas
        System.out.printf(formatoLinha, "Busca do Último", arrayListBuscaUltimo, linkedListBuscaUltimo);
        System.out.printf(formatoLinha, "Busca do Penúltimo", arrayListBuscaPenultimo, linkedListBuscaPenultimo);
        System.out.printf(formatoLinha, "Busca do Meio", arrayListBuscaMeio, linkedListBuscaMeio);

        System.out.println(linhaSeparadora);
    }
}