public class Aluno {
    private final int ra;
    private final String nome;
    private Aluno prox = null;

    public Aluno(){
        this.ra = -1;
        this.nome = " ";
    }

    public Aluno(int ra, String nome) {
        this.ra = ra;
        this.nome = nome;
    }

    public int getRa(){
        return this.ra;
    }
    public String getNome(){
        return this.nome;
    }
    public boolean temProx() { return this.prox != null; }
    public void setProx(Aluno prox_aluno) { this.prox = prox_aluno; }
    public Aluno getProx(){ return this.prox; }
    // public boolean isVazio(){ return (this.ra == -1 || this.ra == -2); }

}
