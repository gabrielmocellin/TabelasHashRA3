public class Hash{
    private int max_posicoes, quant_itens;
    private Aluno[] estruturaAluno;

    public Hash(int tamanho_vetor){
        this.quant_itens = 0;
        this.max_posicoes = tamanho_vetor;
        this.estruturaAluno = new Aluno[tamanho_vetor];
    }

    private int FuncaoHash(int chave){return (chave % this.max_posicoes);}

    public Aluno buscar(int chave, boolean para_remover){
        int index_calculado = FuncaoHash(chave);
        Aluno aluno_buscado = this.estruturaAluno[index_calculado];

        if(aluno_buscado.getRa() == chave){return aluno_buscado;}
        else{
            if(aluno_buscado != null){
                while(aluno_buscado.temProx()) {
                    if(para_remover && (aluno_buscado.getProx().getRa() == chave)) return aluno_buscado;
                    if(aluno_buscado.getRa() == chave) return aluno_buscado;
                    aluno_buscado = aluno_buscado.getProx();
                }
            }
        }

        if(aluno_buscado != null){
            while(aluno_buscado.temProx()) {
                if(para_remover && (aluno_buscado.getProx().getRa() == chave)) return aluno_buscado;
                if(aluno_buscado.getRa() == chave) return aluno_buscado;
                aluno_buscado = aluno_buscado.getProx();
            }
        }

        return aluno_buscado;
    }

    /*
    1. Ao inserir um aluno a hash tem que ser calculada com base na chave (int ra) do aluno;
    2. Deve ser verificado se a posição atual está vaga, ou seja, é nula ou apresenta -1 ou -2 como valores do ra;
        2.1 Caso esteja vago, a quantidade de itens deve ser incrementada e o valor armazenado deve ser alterado;
        2.2 Caso não esteja vago, deve ser encadeado com o aluno que já está na posição buscada;
    */
    public void inserir(Aluno aluno){
        int index_calculado = FuncaoHash( aluno.getRa() );
        Aluno aluno_no_indice = this.estruturaAluno[index_calculado];

        if(aluno_no_indice == null){
            this.estruturaAluno[index_calculado] = aluno;
            this.quant_itens++;
        } else{
            while(aluno_no_indice.temProx()){
                if(aluno_no_indice.getProx() != null){
                    aluno_no_indice = aluno_no_indice.getProx();
                    continue;
                }
                aluno_no_indice.setProx(aluno); // Caso não tenha um próximo definido, deve ser encadeado o aluno inserido no atual.
            }
            if(!aluno_no_indice.temProx()){
                aluno_no_indice.setProx(aluno);
            }
        }

        this.verificarTamanho();
    }

    public Aluno remover(int chave){
        int index_calculado = FuncaoHash(chave);
        Aluno aluno = this.estruturaAluno[index_calculado];

        if(aluno != null) {
            if(aluno.getRa() == chave) {  //É o primeiro da "lista"
                if(aluno.temProx()){  //Possui links
                    this.estruturaAluno[index_calculado] = aluno.getProx();
                }
                else{  //Não possui links
                    this.estruturaAluno[index_calculado] = null;
                }
                this.quant_itens--;
                return aluno;
            }
            else if(aluno.getProx() != null) { //Não é o primeiro, mas tem próximos
                while(aluno.temProx()){
                    if (aluno.getProx().getRa() == chave) {
                        Aluno tmp = aluno.getProx();
                        aluno.setProx(tmp.getProx());
                        this.quant_itens--;
                        return tmp;
                    }
                    aluno = aluno.getProx();
                }
            }
        }
        return null;
    }

    public void imprimir(){ // Printando todos os elementos contidos na hash até o momemento.
        System.out.printf("%nTamanho [ARRAY] atual : [%d] %n", this.max_posicoes);
        for(int i = 0; i < this.max_posicoes; i++){
            Aluno aluno_no_indice = this.estruturaAluno[i];
            if(aluno_no_indice == null){continue;} // Caso seja nulo (não tenha nenhum elemento contido no espaço da hash), não será feita a verificação do RA para não disparar uma exceção.
            System.out.printf("(%d) %d, %s;   ", i, aluno_no_indice.getRa(), aluno_no_indice.getNome());
            while(aluno_no_indice.temProx()){
                aluno_no_indice = aluno_no_indice.getProx();
                System.out.printf("%d, %s;   ", aluno_no_indice.getRa(), aluno_no_indice.getNome());
            }
        }
        System.out.println(" ");
    }


    public float fatorCarga(){ // AINDA NAO FEITO
        return (float) this.quant_itens/this.max_posicoes;
    }

    public void verificarTamanho(){
        if(this.fatorCarga() >= 0.7){
            this.remanejarArray();
        }
        // System.out.printf("Fator carga: [%f] %n", this.fatorCarga()); // TEMPORARIAMENTE USADO PARA VERIFICAR O VALOR CALCULADO DO FATOR DE CARGA
    }

    public void remanejarArray(){
        int antiga_max_posicoes  = this.max_posicoes;
        Aluno[] antiga_estrutura = this.estruturaAluno;

        this.max_posicoes *= 2;
        this.quant_itens = 0;
        this.estruturaAluno = new Aluno[this.max_posicoes];
        System.out.printf("%nNOVO TAMANHO: %d %n", this.max_posicoes);

        for(int i = 0; i < antiga_max_posicoes; i++){
            Aluno aluno_no_indice = antiga_estrutura[i];
            if(aluno_no_indice == null){continue;} // Caso seja nulo (não tenha nenhum elemento contido no espaço da hash), não será feita a veirifcação do RA para não disparar uma exceção.

            this.inserir(aluno_no_indice);

            while(aluno_no_indice.temProx()){
                aluno_no_indice = aluno_no_indice.getProx();
                Aluno aluno_removido = this.remover(aluno_no_indice.getRa());
                if(aluno_removido != null){
                    this.inserir( aluno_removido );
                }
            }
        }
    }
}
