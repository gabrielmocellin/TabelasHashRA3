# Tabela HASH
Projeto desenvolvido durante o 4º Período do curso de Engenharia de Software na PUCPR na matéria Resolução de Problemas Estruturados em Computação lecionada pela professora [Marina de Lara](https://github.com/akitodr) .

## Autores

- [@diogobonet](https://github.com/diogobonet)
- [@FelipeRed](https://github.com/FelipeRed)
- [@gabrielmocellin](https://github.com/gabrielmocellin)

## O que é uma Tabela HASH?
É uma lista com um tamanho fixo (pré-definido), associando uma chave a um valor. Usamos a *tabela HASH* para uma busca e remoção mais eficaz. Buscando o valor direto pela chave, e não pelo valor.

A principal ideia por trás de uma *tabela HASH* é a função de dispersão (ou *função HASH*), que mapeia uma chave para um índice em uma estrutura de dados, geralmente um array.

## Função HASH
A função HASH é composta por, utilizando os seguintes tratamentos de colisão:
- Fila (Lista)
- Árvore

### A Função HASH possui:

- Inserção
- Busca
- Remoção

# Implementação da Função HASH
> Para a implementação da função hash foi utilizado a linguagem de programação JAVA e orientação a objeto. E realizado no Code With Me


Utilizados 6 classes para o código da função hash funcionar:
- Hash (Fila)
- Aluno (Geral)
- Main (Main)
- Arvore (Arvore)
- No (Arvore)
- Printer (Arvore)

## HASH
Na classe `Hash` temos a definição de todas os atributos e métodos com o tratamento de colisão da aplicação da **Fila** e da **Árvore**.

### Atributos da HASH
`private int maxPosicoes, quantItens; private Aluno[] estruturaAluno; private Arvore[] estruturaABB; private boolean usandoArvore;`

Esses atributos são responsaveis:
- `maxPosicoes`: definir o maximo de posições já que a função HASH é uma array estático
- `quantItens`: definir a quantidade de itens possiveis dentro da função HASH
- `private Aluno[] estruturaAluno`: é um array que pode armazenar objetos da classe Aluno
- `private Arvore[] estruturaABB`: é um array que pode armazenar objetos da classe Arvore
- `private boolean usandoArvore`: é um booleano para selecionar qual tratamento de colisão será utilizada (Fila ou Arvore). Se for `true` será utilizada a árvore.

## Código
`public Hash(int tamanho_vetor, boolean usandoArvore)`

Esse código é o construtor da função HASH é responsavel para definir qual o maximo de posições que será utilizada no "array" e definir se o tratamento de colisão será arvore ou fila.

`private int funcaoHash(int chave){return (chave % this.maxPosicoes);}`

Essa função é responsavel por garantir que a posição calculada esteja dentro dos limites da tabela de hash, distribuindo uniformemente os valores e chaves nas posições da tabela hash.

`public Aluno buscar(int chave)`

- O método buscar tem como finalidade procurar um aluno na tabela de hash com base em uma chave. Se o aluno for encontrado, ele é retornado. Se não, uma mensagem de erro é exibida.
1. Se usandoArvore for igual a `true`: Verifica se a árvore na posição calculada não é `null`. Se for diferente de `null`, chama o método `buscarAluno`(chave) na árvore para tentar encontrar o aluno com a chave especificada. O resultado é atribuído à variável alunoBuscado.
2. Se `usandoArvore` for `false`:
- Verifica se o aluno é diferente de null. Se for diferente de null, isso significa que há pelo menos um aluno naquela posição da tabela.
- Se o RA (Registro Acadêmico) do aluno for igual à chave desejada, significa que o aluno foi encontrado, e ele é retornado.
- Se não o método entra em um loop `while` para verificar se há mais alunos na mesma posição (colisões). Ele verifica o RA de cada aluno na lista até encontrar um aluno com a chave desejada ou até chegar ao final da lista.

`public void inserir(Aluno aluno)`

1. Ao inserir um aluno a hash tem que ser calculada com base na chave (int chave) do aluno;
2. Deve ser verificado se a posição atual está vaga, ou seja, é nula ou apresenta -1 ou -2 como valores do ra;

        2.1 Caso esteja vago, a quantidade de itens deve ser incrementada e o valor armazenado deve ser alterado;
        2.2 Caso não esteja vago, deve ser encadeado com o aluno que já está na posição buscada;

`public Aluno remover(int chave)`

- O método `remover(int chave)` é responsável por remover um aluno da estrutura de dados baseada em uma tabela de hash. A lógica do método depende de se a estrutura subjacente é uma árvore binária de busca ou uma fila de alunos. Ele retorna o aluno removido ou `null` se o aluno não for encontrado. Também exibe uma mensagem de erro quando o aluno não é encontrado.

`public void imprimir()`

- Tem a finalidade de exibir os elementos contidos na tabela de hash, fornecendo informações sobre os alunos armazenados nela. A forma como os elementos são impressos depende de se a estrutura de dados subjacente é uma fila de alunos ou uma árvore binária de busca

`public float fatorCarga()`

- Este método calcula o fator de carga da tabela de hash. Ele é usado para indicar o quanto a tabela de hash está preenchida. O valor retornado é um número de ponto flutuante entre 0 e 1.

`public void verificarTamanho()`

- Este método verifica o fator de carga da tabela de hash chamando o método `fatorCarga` e, em seguida, compara o resultado com um limite que é "0.7". Se o fator de carga for igual ou maior que 0.7, isso indica que a tabela de hash está preenchida.

`public void remanejarArray()`
É responsável por redimensionar a tabela de hash, a fim de acomodar um número maior de elementos
1. Se `usandoArvore` for igual a `true`:
- Cria uma nova matriz de árvores `this.estruturaABB` com o novo tamanho
- Percorre a estrutura anterior `antigaEstruturaABB` e para cada árvore não nula na estrutura antiga
- Entra em um loop enquanto a raiz da árvore não estiver vazia e remove a raiz da árvore e insere o aluno removido na nova estrutura de hash
2. Se `usandoArvore` for igual a `false`:
- Ele cria uma nova lista de Fila de alunos
- Percorre a estrutura anterior `antiga_estrutura` e para cada posição na estrutura antiga
- Verifica se o aluno na posição atual não é `null`. Se for `null`, continua para a próxima posição.
- Enquanto houver alunos na mesma posição, remove o aluno e insere o aluno removido na nova estrutura de hash