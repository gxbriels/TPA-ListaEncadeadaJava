package LstEncadeada;

public class No<T> {

    private T valor;
    private No<T> prox;

    public No(T valor) {
        this.valor = valor;
        this.prox = null;
    }

    // Getters
    public T getValor() {
        return valor;
    }

    public No<T> getProx() {
        return prox;
    }

    // Setters
    public void setValor(T valor) {
        this.valor = valor;
    }

    public void setProx(No<T> prox) {
        this.prox = prox;
    }
}