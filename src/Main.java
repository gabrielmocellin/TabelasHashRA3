public class Main {
    public static void main(String[] args) {
        Hash g = new Hash(10);
        g.inserir(new Aluno(10, "Gabriel"));
        g.inserir(new Aluno(41, "Amanda"));
        g.inserir(new Aluno(42, "Joana"));
        g.inserir(new Aluno(13, "Jonas"));
        g.inserir(new Aluno(4, "Fabio"));
        g.inserir(new Aluno(75, "Alves"));
        g.inserir(new Aluno(66, "Bradley"));
        g.inserir(new Aluno(57, "Malu"));
        g.inserir(new Aluno(58, "Ronaldo"));
        g.imprimir();
    }
}