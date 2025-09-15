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

        // --- TESTES DE INSERÇÃO ---
        System.out.println("--- Testes de Inserção ---");

        // Inserção no Fim
        startTime = System.currentTimeMillis();
        arrayList.add(novoAluno);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList - Inserção no Fim: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.add(novoAluno);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList - Inserção no Fim: " + (endTime - startTime) + " ms\n");

        // Inserção no Início (posição 0)
        startTime = System.currentTimeMillis();
        arrayList.add(0, novoAluno);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList - Inserção no Início: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.add(0, novoAluno);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList - Inserção no Início: " + (endTime - startTime) + " ms\n");

        // Inserção no Meio
        startTime = System.currentTimeMillis();
        arrayList.add(posicaoMeio, novoAluno);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList - Inserção no Meio: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.add(posicaoMeio, novoAluno);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList - Inserção no Meio: " + (endTime - startTime) + " ms\n");


        // --- TESTES DE BUSCA ---
        System.out.println("--- Testes de Busca por Índice ---");

        // Busca do Último Elemento
        startTime = System.currentTimeMillis();
        arrayList.get(tamanho - 1);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList - Busca do Último: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.get(tamanho - 1);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList - Busca do Último: " + (endTime - startTime) + " ms\n");

        // Busca do Penúltimo Elemento
        startTime = System.currentTimeMillis();
        arrayList.get(tamanho - 2);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList - Busca do Penúltimo: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.get(tamanho - 2);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList - Busca do Penúltimo: " + (endTime - startTime) + " ms\n");

        // Busca do Elemento do Meio
        startTime = System.currentTimeMillis();
        arrayList.get(posicaoMeio);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList - Busca do Meio: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        linkedList.get(posicaoMeio);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList - Busca do Meio: " + (endTime - startTime) + " ms\n");
    }
}
