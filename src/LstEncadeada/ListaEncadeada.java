package LstEncadeada;

import java.util.Comparator;

public class ListaEncadeada<T> {
    private No<T> prim, ult;
    private int quantidade;
    private final boolean ordenada;
    private Comparator<T> comparador;

    // Construtor
    public ListaEncadeada(boolean ehOrdenada, Comparator<T> comparador) {
        this.prim = this.ult = null;
        this.quantidade = 0;
        this.ordenada = ehOrdenada;
        this.comparador = comparador;
    }

    // Getters para a raiz e quantidade (útil para testes)
    public No<T> getPrim() {
        return prim;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Método principal de inserção
    public void inserirElemento(T elem) {
        if (!this.ordenada) {
            inserirElementoNaoOrd(elem);
        } else {
            inserirElementoOrd(elem);
        }
    }

    // Método para inserir em lista não ordenada (sempre no final)
    private void inserirElementoNaoOrd(T elem) {
        No<T> novo = new No<>(elem);
        if (this.prim == null) {
            this.prim = novo;
            this.ult = novo;
        } else {
            this.ult.setProx(novo);
            this.ult = novo;
        }
        this.quantidade++;
    }

    // Método para inserir em lista ordenada
    private void inserirElementoOrd(T elem) {
        No<T> novo = new No<>(elem);
        No<T> atual, ant;
        atual = this.prim;
        ant = null;

        if (this.prim == null) {
            this.prim = this.ult = novo;
        } else {
            // Percorre a lista para encontrar a posição de inserção
            while (atual != null && this.comparador.compare(atual.getValor(), elem) < 0) {
                ant = atual;
                atual = atual.getProx();
            }

            if (ant == null) { // Insere no início
                novo.setProx(this.prim);
                this.prim = novo;
            } else if (atual == null) { // Insere no final
                this.ult.setProx(novo);
                this.ult = novo;
            } else { // Insere no meio
                ant.setProx(novo);
                novo.setProx(atual);
            }
        }
        this.quantidade++;
    }

    // Método para buscar um elemento (retorna boolean)
    public boolean contemElemento(T elem) {
        return pesquisar(elem) != null;
    }

    // Método para buscar um elemento (retorna o elemento)
    public T pesquisar(T elem) {
        No<T> aux = this.prim;
        while (aux != null) {
            int comparacao = this.comparador.compare(aux.getValor(), elem);
            if (comparacao == 0) {
                return aux.getValor(); // Encontrou o elemento
            }
            if (this.ordenada && comparacao > 0) {
                return null; // Otimização para lista ordenada: valor é maior, não há mais chance de encontrar
            }
            aux = aux.getProx();
        }
        return null; // Não encontrou
    }

    // Método para remover um elemento 
    public T removeElemento(T elem) {
        No<T> aux = this.prim;
        No<T> ant = null;

        while (aux != null) {
            int comparacao = this.comparador.compare(aux.getValor(), elem);
            if (comparacao == 0) {
                T valorRemovido = aux.getValor();
                if (ant == null) { // Remove o primeiro elemento
                    this.prim = this.prim.getProx();
                    if (this.prim == null) {
                        this.ult = null; // Era o único elemento na lista
                    }
                } else { // Remove um elemento no meio ou no final
                    ant.setProx(aux.getProx());
                    if (aux == this.ult) {
                        this.ult = ant; // Removeu o último elemento
                    }
                }
                this.quantidade--;
                return valorRemovido;
            }
            if (this.ordenada && comparacao > 0) {
                return null; // Otimização para lista ordenada: valor é maior, não há mais chance de encontrar
            }
            ant = aux;
            aux = aux.getProx();
        }
        return null; // Elemento não encontrado
    }

    // Método toString para exibir o conteúdo da lista
    @Override
    public String toString() {
        No<T> aux = this.prim;
        String s = "[";
        while (aux != null) {
            s += aux.getValor();
            if (aux != this.ult) {
                s += ", ";
            }
            aux = aux.getProx();
        }
        return (s + "]");
    }
}
