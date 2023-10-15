public class Hash{
    private int max_itens, max_posicoes, quant_itens;
    private Aluno[] estrutura;

    public Hash(int tamanho_vetor, int max_itens){
        this.quant_itens = 0;
        this.max_itens = max_itens;
        this.max_posicoes = tamanho_vetor;
        this.estrutura = new Aluno[tamanho_vetor];
    }

    private int FuncaoHash(int chave){return (chave % this.max_posicoes);}

    public Aluno buscar(int chave, boolean para_remover){
        int index_calculado = FuncaoHash(chave);
        Aluno aluno_buscado = this.estrutura[index_calculado];

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
        Aluno aluno_no_indice = this.estrutura[index_calculado];

        if(aluno_no_indice == null){
            this.estrutura[index_calculado] = aluno;
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

        this.quant_itens++;
    }

    public Aluno remover(int chave){
        int index_calculado = FuncaoHash(chave);
        Aluno aluno = this.estrutura[index_calculado];

        if(aluno != null && aluno.getRa() == chave){ // É o primeiro da "lista"
            if(aluno.temProx()){ // Possui links
                this.estrutura[index_calculado] = aluno.getProx();
            } else{ // Não possui links
                this.estrutura[index_calculado] = null;
            }
        } else{ // TEM PRÓXIMO e NÃO É O PRIMEIRO
            while(aluno.temProx()){
                if(aluno.getProx().getRa() == chave){
                    Aluno tmp = aluno.getProx();
                    aluno.setProx(aluno.getProx().getProx());
                    return tmp;
                }
                aluno = aluno.getProx();
            }
        }

        if(aluno != null){this.quant_itens--;}
        return aluno;
    }

    public void imprimir(){ // Printando todos os elementos contidos na hash até o momemento.
        for(int i = 0; i < this.max_posicoes; i++){
            Aluno aluno_no_indice = this.estrutura[i];
            if(aluno_no_indice == null){continue;} // Caso seja nulo (não tenha nenhum elemento contido no espaço da hash), não será feita a veirifcação do RA para não disparar uma exceção.
            System.out.printf("(%d) %d, %s;   ", i, aluno_no_indice.getRa(), aluno_no_indice.getNome());
            while(aluno_no_indice.temProx()){
                aluno_no_indice = aluno_no_indice.getProx();
                System.out.printf("%d, %s;   ", aluno_no_indice.getRa(), aluno_no_indice.getNome());
            }
        }
        System.out.println(" ");
    }


    public float FatorCarga(){ // AINDA NAO FEITO
        return max_itens;
    }
}
