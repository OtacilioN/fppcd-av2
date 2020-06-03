# fppcd-av2

Repositório para o projeto de avaliação 2 unidade da disciplina de Fundamentos de Programação Paralela, Concorrente e Distribuída do Cesar School

## Projeto

POC de arquitetura de microserviços com Java Spring Boot e api Restfull.
Contém 4 microserviços:

- Carrinho: Cliente que dispara requisições para os outros serviços para a transação de compra.
- Estoque: Responsável por conferir se a lista de SKU's requisitada está disponível em estoque, disponível na porta 8082.
- Cobranca: Responsável por mockar o serviço de pagamento, recebe uma lista de SKU's, confere o preço de cada item e retorna o total pago, disponível na parta 8083.
- Expedicao: Responsável por mockar o serviço de entregas, recebe um email, um cep e a listra de SKU's dos items a serem enviados, disponível na parta 8084.
