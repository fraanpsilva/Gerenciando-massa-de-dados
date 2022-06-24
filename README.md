# Curso gerenciando Massa de dados

### Sobre o curso
Neste curso há 5 estratégias para utilizar para garantir que a suíte de testes poderá ser executada sempre que for 
necessário, sem os testes falharem por erro na massa de dados.
Mostra um pouco sobre o framework DBUnit, que nos permite simplificar o trabalho em algumas estratégias e ter um controle
maior do banco de dados.

Temas abordados:
* Criar testes funcionais mais estáveis;
* Aprender a criar massa de dados através da interface web;
* Gerenciar massa de dados de modo que permite a execução de testes manuais e automatizados
* Utilizar DBUnit
* Definir um estado inicial de informações no banco para executar os testes com mais segurança
* Fazer verificações bem detalhadas sobre todas as informações amazzenadas no Banco de Dados


### ferramentas utilizadas
* Java
* DBUnit
* Maven
* Selenium
* Postegresql
* Faker
* SQL

### Escolhendo a melhor dependência
1 - Depender da interface da aplicação para gerar os dados
2 - Depender do banco de dados diretamente

Ambas estratégias tem seus pós e contras, são passíveis de alteração que podem acabar quebrando os scripts,
seja SQL ou de automação.