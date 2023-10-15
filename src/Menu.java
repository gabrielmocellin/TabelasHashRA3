import java.util.Scanner;

public class Menu {
    Scanner input;
    Hash tabelaHash;

    public Menu(Hash hash){
        this.input = new Scanner(System.in);
        this.tabelaHash = hash;
    }

    public void print(){
        System.out.println("""
        Selecione uma opção:
        [1] | Adicionar Aluno
        [2] | Buscar Aluno
        [3] | Remover Aluno
        [4] | Sair
        """);
    }

    public int getOption(){
        System.out.println("-> ");
        return this.input.nextInt();
    }

    public void adicionarAluno(){
        System.out.println("Digite o nome do aluno: ");
        String nome = this.input.nextLine();
        System.out.println("Digite o ra do aluno: ");
        int ra = this.input.nextInt();

        this.tabelaHash.inserir(new Aluno(ra, nome));
    }

    public void buscarAluno(){

    }

    public boolean verifyOp(){
        int op = this.getOption();
        switch(op){
            case 1:
                this.adicionarAluno();
                break;
            case 2:
                this.buscarAluno();
                break;
        }
        return true;
    }
}
