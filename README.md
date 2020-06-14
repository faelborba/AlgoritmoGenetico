# Algoritmos Genéticos - Relatório de Implementação

Rafael Borba dos Santos <rafael.borba@rede.ulbra.br><br/>
Maria Adelina Raupp Sganzerla <maria.sganzerla@ulbra.br> – Orientadora<br/>
Universidade Luterana do Brasil (Ulbra) – Curso de Ciência da Computação – Câmpus Gravataí<br/>
Av. Itacolomi, 3.600 – Bairro São Vicente – CEP 94170-240 – Gravataí - RS<br/>
18 de junho de 2020<br/><br/>
##Resumo

Esse relatório visa descrever o desenvolvimento e implementação do algoritmo genético representado no exemplo da aula 8 de Inteligência Artificial. Por fim, o presente relatório discute os resultados obtidos. Para desenvolvimento foi utilizado a IDE Eclipse e linguagem de programação Java.<br/>
Palavras-chave: Algoritmo Genético, Relatório, Inteligência Artificial, Java.<br/><br/>

##1  Relatório
O Algoritmo possui três classes utilizadas em seu funcionamento: A classe principal chamada AlgoritmoGenetico que tem o método main, executa todo o programa e todos os métodos necessários para funcionamento; Classe DecimalBinário utilizada somente para definir no programa um número decimal (inteiro) e seu correspondente em binário que foi definido como String para manipular os dígitos facilmente; e por fim a classe Tabela, utilizada para criar as colunas X, Y, cromossomo, fitnes e ponderação.<br/>
Dentro da classe principal existe um ArrayList nomeado tabela da classe Tabela que é responsável por armazenar os dados gerados e utilizados pelo programa. Outro ArrayList com a classe DecimalBinario, chamado de db, é utilizado para armazenar a tabela com os dados brutos dos números decimais e seus correspondentes em binário.<br/>
O primeiro método é o geraDecBin, gera a lista com os números de 0 a 7 e seus correspondentes em binário. Essa lista é representada pela tabela conforme figura 1.<br/>
Figura 1 - Possíveis Dados de entrada<br/><br/>

Já o segundo método, geraTabela, é responsável por gerar a lista com os números da coluna X e Y de forma aleatória, sendo esses os dados de entrada. Analisando os dados da coluna X e Y é gerado a coluna cromossomo ainda nesse método. Por fim esse método executa a função exemplo, f(x,y)=2–(x–2)²–(y–3)², populando a coluna Fit representada visualmente como F(X,Y). A figura 2 representa como a tabela fica após execução desse método.<br/>




Figura 2 - Tabela inicial de dados da 1ª geração<br/><br/>

Após o programa entra em um loop contendo o restante dos métodos, saindo apenas se chegar a uma de duas condições a seguir, encontrar um um fit igual a 2 que é a solução ótima, ou chegar na geração 500 com a melhor solução possível encontrada. <br/>
O método ordenaTabela utiliza os dados da coluna Fit para organizar os dados da tabela do maior para o menor. Na figura 3 pode ser visto como fica a tabela ordenada. Caso encontre o valor 2 no Fit o programa encerrado já na primeira geração.<br/>
Figura 3 - Tabela ordenada<br/><br/>

O método removePopulação é responsável por descartar os indivíduos mais distantes da solução ideal, nesse caso foi utilizado um corte, onde a da quinta linha adiante são excluídos, ficando apenas as primeiras 4 linhas da tabela. Esse método ainda é responsável por ponderar os pesos, sendo a primeira linha peso 4 e quarta linha peso 1 (figura 4).<br/>
Figura 4 - Tabela com a melhor população e ponderada<br/><br/>

Após é gerada uma nova tabela através do crossover entre a população que permaneceu, ponderando sempre com o indivíduo da primeira linha fazendo crossover 4 vezes, e o último 1 vez gerando assim, 10 novos indivíduos. Após executa-se a mutação dos genes através do método mutacao, basicamente ele sorteia uma linha, e faz alteração de um bit aleatório. Isso é repetido 5 vezes.<br/>
Por fim o método decotificar converte os bits nos números inteiros representados nas colunas X e Y da tabela, conforme figura 5.<br/>
Figura 5 - Tabela decodificada<br/><br/>

Após volta ao início do loop ordena a Tabela e exibe o resultado da próxima geração. Caso encontre o valor 2 na coluna do Fit, já encerra o programa, conforme figura 6 onde pode ser visto que concluiu na segunda geração.<br/>
Figura 6 - Tabela final<br/><br/>

##1.1 Resultados Obtidos
O exemplo utilizado para explicar o funcionamento do algoritmo chegou ao resultado ótimo na segunda geração. Porém em alguns casos se obteve na quarta geração, outros na primeira, oitava e outros o algoritmo chegava a 500 gerações e não conseguia encontrar um Fit igual a 2, porém nmo resultado mais próximo que era 1 (figura 7).<br/>
Figura 7 - Tabela final 500ª geração<br/><br/>

Com isso percebe-se que o algoritmo tenta padronizar todos os indivíduos na solução mais adequada possível durante o crossover e mutação. Como pode ser visto na coluna do F(X,Y) da figura 7.<br/>
Tentou-se remover a trava de 500 gerações, porém nos casos onde a quantidade de gerações fica muito grande a mutação não consegue encontrar uma solução ótima, repetindo os cromossomos que mais chegaram próximos a solução ótima. Algumas ficaram em loop infinito com mais de 70000 gerações.<br/>
Pelo que pode se perceber, isso deve a mutação ocorrer em apenas 5 indivíduos diferentes, permanecendo os outros 5 indivíduos sem mutação e permanecendo no topo. Essa tendência do  algoritmo acaba provavelmente gerando o loop infinito.<br/>

