package Comparators;

import java.util.Comparator;
import LstEncadeada.Aluno;

public class ComparadorAlunoPorNome implements Comparator<Aluno> {
    @Override
    public int compare(Aluno o1, Aluno o2){
        return o1.getNome().compareTo(o2.getNome());
    }
}
