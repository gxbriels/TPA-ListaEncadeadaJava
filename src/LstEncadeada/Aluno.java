package LstEncadeada;

public class Aluno {
    private int matricula;
    private String nome;

    public Aluno(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " (" + matricula + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluno aluno = (Aluno) obj;
        return matricula == aluno.matricula;
    }
}
