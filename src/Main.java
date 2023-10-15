public class Main {
    public static void main(String[] args) {
        Hash g = new Hash(10, 8);
        // Menu menu = new Menu(g);
        // menu.print();
        g.inserir(new Aluno(11, "José"));
        g.inserir(new Aluno(10, "Gabriel"));
        g.inserir(new Aluno(21, "Marcelo"));
        g.inserir(new Aluno(31, "Sheldon"));
        g.inserir(new Aluno(21, "Ana"));
        g.inserir(new Aluno(20, "Carlos"));
        g.imprimir();
        Aluno alunoteste = g.buscar(31, false);
        g.remover(31);
        g.remover(11);
        g.imprimir();
    }
}