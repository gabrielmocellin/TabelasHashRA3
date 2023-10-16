public class Main {
    public static void main(String[] args) {
        /*
        [CRIAÇÃO]
        Para instanciar uma tabela hash:
            1° parâmetro: define o tamanho do array
            2° parâmetro: (true)  define que usará estrutura de árvore para as colisões
                          (false) define que usará estrutura depara as colisões

        [INSERÇÃO]
        Para inserir um aluno na tabela hash:
            Comando: nomeHash.inserir(new Aluno(int ra, String nome))
        Pode ser criado um aluno no próprio parâmetro da função de inserção ou passar um objeto direto

        [BUSCA]
        Para realizar uma busca, deve ser utilizado o comando nameHash.buscar( int chave )
            0.1 Caso o aluno não seja encontrado:
                O valor retornado é nulo caso o aluno não seja encontrado e uma mensagem é apresentada
            0.2 Caso seja encontrado:
                o aluno é retornado

        [REMOVER]
        Para realizar uma remoção, deve ser utilizado o comando nameHash.remover( int chave )
            0.1 Caso o aluno não seja encontrado:
                O valor retornado é nulo caso o aluno não seja encontrado e uma mensagem é apresentada
            0.2 Caso seja encontrado:
                o aluno é retornado
        */



        Hash tHash = new Hash(4, false);
        tHash.inserir(new Aluno(5, "Arnaldo"));
        tHash.inserir(new Aluno(9, "Amanda"));
        tHash.inserir(new Aluno(13, "Gabriel"));
        tHash.inserir(new Aluno(17, "Felipe"));

        tHash.inserir(new Aluno(21, "Azevedo"));
        tHash.inserir(new Aluno(25, "Jonas"));
        tHash.inserir(new Aluno(27, "Joana"));
        tHash.inserir(new Aluno(33, "Malu"));
        tHash.inserir(new Aluno(37, "Ronaldo"));
        tHash.inserir(new Aluno(40, "Marquin"));

        tHash.inserir(new Aluno(45, "Tadeu"));
        tHash.inserir(new Aluno(49, "Osvaldo"));
        tHash.inserir(new Aluno(53, "Creuza"));
        tHash.inserir(new Aluno(6, "Cintia"));
        tHash.inserir(new Aluno(61, "Sandra"));
        tHash.inserir(new Aluno(65, "Alice"));
        tHash.inserir(new Aluno(69, "Bob"));
        tHash.inserir(new Aluno(73, "Sergio"));

        tHash.imprimir();

        tHash.inserir(new Aluno(2, "Maria"));
        tHash.inserir(new Aluno(1, "Alessandro"));

        tHash.imprimir();

        long tempoInicial = System.currentTimeMillis();

        long tempoFinal = System.currentTimeMillis();
        System.out.printf("Tempo final: [%d] ms%n", (tempoFinal - tempoInicial));
    }

    public static void print(String msg){
        System.out.println(msg);
    }
}