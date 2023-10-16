public class Hash{
    private int maxPosicoes, quantItens;
    private Aluno[] estruturaAluno;
    private Arvore[] estruturaABB;
    private boolean usandoArvore;

    public Hash(int tamanho_vetor, boolean usandoArvore){
        this.quantItens = 0;
        this.maxPosicoes = tamanho_vetor;

        if (usandoArvore) {
            this.estruturaABB = new Arvore[tamanho_vetor];
            this.usandoArvore = true;
        } else {
            this.estruturaAluno = new Aluno[tamanho_vetor];
            this.usandoArvore = false;
        }
    }

    private int funcaoHash(int chave) { return (chave % this.maxPosicoes); }

    public Aluno buscar(int chave){
        int indexCalculado = funcaoHash(chave);
        Aluno alunoBuscado = null;
        if (usandoArvore) {
            if (this.estruturaABB[indexCalculado] != null) {
                alunoBuscado = this.estruturaABB[indexCalculado].buscarAluno(chave);
            }
            if (alunoBuscado == null) {
                System.out.println("Aluno não encontrado!");
            }
        }
        else {
            alunoBuscado = this.estruturaAluno[indexCalculado];

            if (alunoBuscado == null) {
                System.out.println("Aluno não encontrado!");
            } else if (alunoBuscado.getRa() == chave) {
                return alunoBuscado;
            } else {
                while (alunoBuscado.temProx()) {
                    if (alunoBuscado.getRa() == chave) {return alunoBuscado;}
                    alunoBuscado = alunoBuscado.getProx();
                }
            }
        }
        return alunoBuscado;
    }

    /*
    1. Ao inserir um aluno a hash tem que ser calculada com base na chave (int ra) do aluno;
    2. Deve ser verificado se a posição atual está vaga, ou seja, é nula ou apresenta -1 ou -2 como valores do ra;
        2.1 Caso esteja vago, a quantidade de itens deve ser incrementada e o valor armazenado deve ser alterado;
        2.2 Caso não esteja vago, deve ser encadeado com o aluno que já está na posição buscada;
    */
    public void inserir(Aluno aluno){
        int indexCalculado = funcaoHash( aluno.getRa() );
        if (usandoArvore) {
            if (this.estruturaABB[indexCalculado] == null) {
                this.estruturaABB[indexCalculado] = new Arvore(aluno);
                this.quantItens++;
            }
            else {
                this.estruturaABB[indexCalculado].inserirAluno(aluno);
            }
        } else {
            Aluno alunoNoIndice = this.estruturaAluno[indexCalculado];
            if (alunoNoIndice == null) {
                this.estruturaAluno[indexCalculado] = aluno;
                this.quantItens++;
            } else {
                while (alunoNoIndice.temProx()) {
                    if (alunoNoIndice.getProx() != null) {
                        alunoNoIndice = alunoNoIndice.getProx();
                        continue;
                    }
                    alunoNoIndice.setProx(aluno); // Caso não tenha um próximo definido, deve ser encadeado o aluno inserido no atual.
                }
                if (!alunoNoIndice.temProx()) {
                    alunoNoIndice.setProx(aluno);
                }
            }
        }
        this.verificarTamanho();
    }


    public Aluno remover(int chave){
        int indexCalculado = funcaoHash(chave);
        Aluno aluno = null;

        if (usandoArvore) {
            if (this.estruturaABB[indexCalculado] == null) {
                System.out.println("Aluno não encontrado!");
            }
            else {
                aluno = this.estruturaABB[indexCalculado].removerAluno(chave);
                if(aluno == null) System.out.println("Aluno não encontrado!");
            }
        }
        else {
            aluno = this.estruturaAluno[indexCalculado];

            if(aluno != null) {
                if(aluno.getRa() == chave) {  //É o primeiro da "lista"
                    if (aluno.temProx()){  //Possui links
                        this.estruturaAluno[indexCalculado] = aluno.getProx();
                    }
                    else {  //Não possui links
                        this.estruturaAluno[indexCalculado] = null;
                    }
                    this.quantItens--;
                    return aluno;
                }
                else if(aluno.getProx() != null) { //Não é o primeiro, mas tem próximos
                    while(aluno.temProx()) {
                        if (aluno.getProx().getRa() == chave) {
                            Aluno tmp = aluno.getProx();
                            aluno.setProx(tmp.getProx());
                            this.quantItens--;
                            return tmp;
                        }
                        aluno = aluno.getProx();
                    }
                }
            }
        }
        return aluno;
    }

    public void imprimir(){ // Printando todos os elementos contidos na hash até o momemento.
        System.out.printf("%nTamanho [ARRAY] atual : [%d] %n", this.maxPosicoes);
        for(int i = 0; i < this.maxPosicoes; i++){
            if(!usandoArvore) {
                Aluno alunoNoIndiceI = this.estruturaAluno[i];
                if (alunoNoIndiceI == null) {
                    continue;
                } // Caso seja nulo (não tenha nenhum elemento contido no espaço da hash), não será feita a verificação do RA para não disparar uma exceção.
                System.out.printf("(%d) %d, %s;   ", i, alunoNoIndiceI.getRa(), alunoNoIndiceI.getNome());
                while (alunoNoIndiceI.temProx()) {
                    alunoNoIndiceI = alunoNoIndiceI.getProx();
                    System.out.printf("%d, %s;   ", alunoNoIndiceI.getRa(), alunoNoIndiceI.getNome());
                }
                System.out.println();
            } else {
                System.out.println("Posição [" + i + "]:");
                if (this.estruturaABB[i] == null) {
                    System.out.println("Vazio\n");
                }
                else {
                    this.estruturaABB[i].print();
                }
            }
        }
        System.out.println(" ");
    }


    public float fatorCarga(){ // AINDA NAO FEITO
        return (float) this.quantItens/this.maxPosicoes;
    }

    public void verificarTamanho(){
        if(this.fatorCarga() >= 0.7){
            this.remanejarArray();
        }
        // System.out.printf("Fator carga: [%f] %n", this.fatorCarga()); // TEMPORARIAMENTE USADO PARA VERIFICAR O VALOR CALCULADO DO FATOR DE CARGA
    }

    public void remanejarArray(){
        int antigaMaxPosicoes  = this.maxPosicoes;
        this.maxPosicoes *= 2;
        this.quantItens = 0;

        if (usandoArvore) {
            Arvore[] antigaEstruturaABB = this.estruturaABB;
            this.estruturaABB = new Arvore[this.maxPosicoes];

            for(int i = 0; i < antigaMaxPosicoes; i++){
                Arvore arvore = antigaEstruturaABB[i];
                if(arvore != null) {
                    while (arvore.getRaiz() != null) {
                        Aluno alunoRaiz = arvore.removerAluno(arvore.getRaiz().getRaAluno());
                        this.inserir(alunoRaiz);
                    }
                }
            }
        }
        else {
            Aluno[] antigaEstruturaAluno = this.estruturaAluno;
            this.estruturaAluno = new Aluno[this.maxPosicoes];

            System.out.printf("%nNOVO TAMANHO: %d %n", this.maxPosicoes);

            for(int i = 0; i < antigaMaxPosicoes; i++){
                Aluno alunoNoIndiceI = antigaEstruturaAluno[i];
                if (alunoNoIndiceI != null) { // Caso seja nulo (não tenha nenhum elemento contido no espaço da hash), não será feita a veirifcação do RA para não disparar uma exceção.
                    this.inserir(alunoNoIndiceI);
                    while(alunoNoIndiceI.temProx()){
                        Aluno proximo = alunoNoIndiceI.getProx();
                        alunoNoIndiceI.setProx(null);
                        inserir(proximo);
                        alunoNoIndiceI = proximo;
                    }
                }
            }
        }
    }
    
}
