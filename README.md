Trabalho 1: Análise de Complexidade em Estruturas de Listas

Visão Geral do Projeto

Este projeto foi desenvolvido para a disciplina de Técnicas de Programação Avançada e tem como objetivo principal a implementação e análise de estruturas de dados de lista em Java.

O trabalho se divide em quatro partes principais:

    Implementação Prática: Criação de uma biblioteca própria de Lista Encadeada genérica (ListaEncadeada<T>), capaz de operar em modo ordenado e não ordenado.

    Análise Matemática: Análise da complexidade de tempo (Big-O) dos principais métodos da biblioteca implementada.

    Estudo Teórico: Pesquisa sobre o funcionamento interno e a complexidade das classes ArrayList e LinkedList do Java Collections Framework.

    Análise Empírica: Realização de testes de desempenho para comparar os tempos de execução de operações de inserção e busca em ArrayList e LinkedList com grandes volumes de dados.

Todo o código desenvolvido segue os requisitos propostos, utilizando Generics para flexibilidade de tipos e Comparator para definir critérios de ordenação e busca.

2. Estrutura do Projeto

O código está organizado em pacotes para separar as responsabilidades, facilitando a manutenção e o entendimento.

Comparators
      
      ComparadorAlunoPorMatricula.java  # Compara Alunos pela matrícula (int)
      ComparadorAlunoPorNome.java       # Compara Alunos pelo nome (String)

LstEncadeada
      
      Aluno.java                        # Classe de modelo para os dados de um aluno
      ListaEncadeada.java               # Implementação da Lista Encadeada genérica
      No.java                           # Nó da Lista Encadeada
      TesteLista.java                   # Programa para testar a ListaEncadeada

TesteDesempenho
      
      GeradorArquivosBalanceados.java   # Gera arquivo de teste com IDs balanceados
      GeradorArquivosOrdenados.java     # Gera arquivo de teste com IDs sequenciais
      LeitorArquivos.java               # Lê os arquivos e executa os testes de desempenho

3. Explicação Detalhada dos Componentes

A seguir, uma descrição passo a passo de cada classe e sua função no projeto.

3.1. Pacote LstEncadeada (A Biblioteca Principal)

Este pacote contém o núcleo da primeira parte do trabalho: a implementação da lista encadeada.

No.java

Esta é a classe mais fundamental. Cada objeto No representa um elemento (um "elo") da corrente que forma a lista.

    private T valor;: Armazena o dado do nó. O tipo T (genérico) permite que o nó guarde qualquer tipo de objeto (ex: Aluno, Integer, String).

    private No<T> prox;: É a referência (ou "ponteiro") para o próximo nó na lista. Se for null, significa que este é o último nó.

Aluno.java

Esta classe serve como o modelo de dados (model) para os testes, conforme solicitado no trabalho.

    Armazena matricula (inteiro) e nome (String) de um aluno.

    Possui um método toString() para facilitar a impressão da lista de alunos.

    O método equals() foi sobrescrito para comparar alunos pela matrícula.

ListaEncadeada.java

Esta é a classe central do projeto, a implementação da estrutura de dados.

    Atributos Principais:

        prim, ult: Referências para o primeiro e o último nó, respectivamente. Ter uma referência para o último (ult) permite inserções no final em tempo constante O(1) para listas não ordenadas.

        quantidade: Controla o número de elementos na lista.

        ordenada: Um boolean definido no construtor que determina o comportamento da lista.

comparador: Um objeto Comparator<T> que define como os elementos do tipo T devem ser comparados entre si. Essencial para ordenação e busca.

Métodos Principais:

    inserirElemento(T elem): Este é o método público para inserção. Ele atua como um "distribuidor":

        Se ordenada == false, chama inserirElementoNaoOrd(elem).

        Se ordenada == true, chama inserirElementoOrd(elem).

    inserirElementoNaoOrd(T elem): Simplesmente adiciona o novo elemento no final da lista.

    inserirElementoOrd(T elem): Este método é mais complexo. Para manter a ordem, ele precisa encontrar a posição correta para o novo elemento.

        Ele percorre a lista usando dois ponteiros, atual e ant (anterior).

        O while loop avança enquanto o elemento atual for menor que o elemento a ser inserido (elem), de acordo com o comparador.

        Ao final do loop, ele trata os três casos possíveis de inserção: no início (ant == null), no final (atual == null) ou no meio da lista.

        A complexidade no pior caso é O(n), pois pode ser necessário percorrer toda a lista para inserir no final.

    pesquisar(T elem) e contemElemento(T elem):

        Percorre a lista comparando cada elemento com o elem buscado.

        Otimização para listas ordenadas: Se, durante a travessia, o método encontra um elemento que já é "maior" que o buscado (comparacao > 0), ele para a busca imediatamente e retorna null, pois sabe que o elemento não será encontrado dali em diante.

    removeElemento(T elem):

            Funciona de forma similar à busca: percorre a lista com os ponteiros aux e ant para encontrar o elemento a ser removido.

            Trata os casos especiais: remoção do primeiro elemento (atualiza prim), remoção do último (atualiza ult), e remoção do único elemento da lista ( prim e ult se tornam null).

            Também implementa a otimização para listas ordenadas, parando a busca se um elemento maior for encontrado.

        toString(): Um método utilitário que percorre a lista e cria uma representação em String de seu conteúdo, como [Aluno A, Aluno B, Aluno C].

3.2. Pacote Comparators

Este pacote desacopla a lógica de comparação da estrutura de dados, permitindo que a ListaEncadeada ordene e busque Aluno de diferentes maneiras.

    ComparadorAlunoPorNome.java: Implementa a interface Comparator<Aluno>. Seu método compare utiliza o1.getNome().compareTo(o2.getNome()) para comparar dois alunos em ordem alfabética de nome.

    ComparadorAlunoPorMatricula.java: Também implementa Comparator<Aluno> e usa Integer.compare(o1.getMatricula(), o2.getMatricula()) para comparar alunos numericamente por sua matrícula.

3.3. Pacote TesteDesempenho (Análise Empírica)

Este conjunto de classes foi criado para atender à Parte 4 do trabalho, que exige a análise prática do desempenho de ArrayList e LinkedList.

    GeradorArquivosOrdenados.java e GeradorArquivosBalanceados.java:

        São programas utilitários para criar arquivos de texto com milhões de registros de alunos.

        GeradorArquivosOrdenados cria IDs sequenciais (1, 2, 3, ...).

        GeradorArquivosBalanceados cria IDs de forma não sequencial, mas distribuída, simulando uma inserção que poderia ser mais "aleatória". Isso foi inspirado na sugestão do trabalho de usar o gerador fornecido.

LeitorArquivos.java:

    Este é o programa principal para os testes de desempenho.

    Passo 1: Leitura e Povoamento: Lê o arquivo gerado (ex: alunosBalanceados.txt) e insere cada aluno em um ArrayList e em um LinkedList, medindo o tempo total dessa operação inicial.

    Passo 2: Testes de Inserção: Realiza e cronometra as seguintes inserções em ambas as listas:

    No final (add(novoAluno)).

    No início (add(0, novoAluno)).

    No meio (add(posicaoMeio, novoAluno)).

Passo 3: Testes de Busca: Realiza e cronometra as seguintes buscas por índice em ambas as listas:

            Do último elemento (get(tamanho - 1)).

            Do penúltimo elemento (get(tamanho - 2)).

            Do elemento do meio (get(posicaoMeio)).

        Os resultados impressos no console permitem comparar diretamente qual estrutura é mais eficiente para cada cenário.

4. Como Compilar e Executar o Projeto

Você pode compilar e executar os arquivos Java diretamente pelo terminal.

4.1. Compilação

Navegue até o diretório raiz do seu projeto (a pasta que contém Comparators, LstEncadeada e TesteDesempenho) e execute os seguintes comandos para compilar todo o código:
Bash

javac Comparators/*.java
javac LstEncadeada/*.java
javac TesteDesempenho/*.java

4.2. Execução

Existem 4 pontos de entrada (main) no projeto:

    Testar a ListaEncadeada implementada:
    Este programa demonstra as operações de inserção, busca e remoção nas listas ordenadas e não ordenadas com dados de exemplo.
    Bash

java LstEncadeada.TesteLista

Gerar os arquivos de dados para teste:
Execute um dos geradores para criar o arquivo de texto. O arquivo será salvo na raiz do projeto.
Bash

# Para gerar alunosOrdenados.txt
java TesteDesempenho.GeradorArquivosOrdenados

# Ou para gerar alunosBalanceados.txt
java TesteDesempenho.GeradorArquivosBalanceados

Executar a análise de desempenho:
Após gerar um dos arquivos (por padrão, ele lê alunosBalanceados.txt), execute este programa para ver os tempos de execução de ArrayList vs. LinkedList.
Bash

java TesteDesempenho.LeitorArquivos
