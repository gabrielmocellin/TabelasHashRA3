public class Arvore {
    private No raiz;

    public Arvore(Aluno aluno) {
        this.raiz = new No(aluno);
    }

    public void inserirAluno(Aluno aluno) {
        inserir(aluno, this.raiz);
    }

    public Aluno buscarAluno(int numero) {
        return buscar(numero, this.raiz);
    }

    public Aluno removerAluno(int numero) {
        return remover(numero, this.raiz, null);
    }

    private void inserir(Aluno aluno, No no) {
        if (this.raiz == null) {
            this.raiz = new No(aluno);
        }
        else if (aluno.getRa() < no.getRaAluno()) {
            if (no.getEsquerda() == null) {
                no.setFilhoEsquerdo(new No(aluno));
            }
            else {
                inserir(aluno, no.getEsquerda());
            }
        }
        else {
            if (no.getDireita() == null) {
                no.setFilhoDireito(new No(aluno));
            }
            else {
                inserir(aluno, no.getDireita());
            }
        }
    }

    private Aluno buscar(int numero, No no) {
        if (numero == no.getRaAluno()) {
            return no.getAluno();
        }
        else if (numero < no.getRaAluno()){
            if (no.getEsquerda() == null) {
                return null;
            }
            else {
                return buscar(numero, no.getEsquerda());
            }
        }
        else {
            if (no.getDireita() == null) {
                return null;
            }
            else {
                return buscar(numero, no.getDireita());
            }
        }
    }

    private Aluno remover(int numero, No no, No pai) {
        if (numero == no.getRaAluno()) {
            if (no.isFolha()) {
                apagarNoFolha(no, pai);
            }
            else if (no.filhoEsquerdoEhNull()) {
                subirFilho(no.getDireita(), no, pai);
            }
            else if (no.filhoDireitoEhNull()) {
                subirFilho(no.getEsquerda(), no, pai);
            }
            else if (no.temDoisFilhos()) {
                trocarNoESucessor(no, pai);
            }
            return no.getAluno();
        }
        else {
            if (numero >= no.getRaAluno()) {
                if (no.filhoDireitoEhNull()) {return null;}
                return remover(numero, no.getDireita(), no);
            }
            else {
                if (no.filhoEsquerdoEhNull()) {return null;}
                return remover(numero, no.getEsquerda(), no);
            }
        }
    }

    private void apagarNoFolha(No no, No pai) {
        if (no == this.raiz) {
            this.raiz = null;
        }
        else {
            if (pai.getDireita() == no) {
                pai.setFilhoDireito(null);
            }
            else {
                pai.setFilhoEsquerdo(null);
            }
        }
    }

    private void subirFilho(No filho, No excluido, No pai) {
        if (excluido == this.raiz) {
            this.raiz = filho;
        }
        else {
            if (pai.getDireita() == excluido) {
                pai.setFilhoDireito(filho);
            }
            else {
                pai.setFilhoEsquerdo(filho);
            }
        }
    }

    private void trocarNoESucessor(No no, No pai) {
        No sucessor = no.procurarSucessor(no.getDireita(), no);
        if (no == this.raiz) {
            this.raiz = sucessor;
        }
        else {
            if (pai.getDireita() == no) {
                pai.setFilhoDireito(sucessor);
            }
            else {
                pai.setFilhoEsquerdo(sucessor);
            }
        }
        sucessor.setFilhoEsquerdo(no.getEsquerda());
        if (no.getDireita() != sucessor) {
            sucessor.getUltimoFilhoADireita().setFilhoDireito(no.getDireita());
        }
    }

    public void print() {
        Printer printer = new Printer();
        if (raiz != null) {
            System.out.println(printer.topDown(this.raiz));
        }
        else {
            System.out.println("Vazio");
        }
        System.out.println();
    }

    public No getRaiz() {return this.raiz;}
}
