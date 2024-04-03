
# Gerenciamento de Pessoas

Este é um projeto desenvolvido como parte de um teste técnico para a função de Back-End. O objetivo do projeto é criar uma API para gerenciar pessoas e seus endereços associados.

## Descrição

O projeto é uma aplicação Spring Boot que utiliza Spring Data JPA para persistência de dados e Spring Web para exposição dos endpoints RESTful. Ele permite criar, editar, consultar e excluir informações de pessoas e seus endereços associados. Além disso, também é possível definir um endereço principal para cada pessoa.

## Requisitos do Sistema

- Java 17
- Maven 3.0 ou superior
- Banco de dados H2 (utilizado para desenvolvimento e testes)
- Spring Boot 3.2.4
- Spring Cloud 2022.0.3
- MapStruct 1.5.3.Final
- Lombok 1.18.24

## Instalação

1. Clone o repositório para sua máquina local.
2. Certifique-se de que possui o Java e o Maven instalados em sua máquina.
3. Execute o comando `mvn spring-boot:run` na raiz do projeto para iniciar a aplicação.

## Uso

Após a execução da aplicação, você pode acessar os endpoints RESTful para interagir com o sistema. Aqui estão alguns exemplos de endpoints disponíveis:

- `GET /pessoas`: Retorna todas as pessoas cadastradas.
- `GET /pessoas/{id}`: Retorna os detalhes de uma pessoa específica.
- `POST /pessoas`: Cria uma nova pessoa.
- `PUT /pessoas/{id}`: Atualiza os dados de uma pessoa existente.
- `PUT /pessoa/{idPessoa}/enderecoPrincipal/{idEnderecoPrincipal}`: Define o endereço principal de uma pessoa pelo ID da pessoa e ID do endereço
- `DELETE /pessoas/{id}`: Exclui uma pessoa existente.
- `POST /endereco`: Cria um novo endereço.
- `PUT /endereco/{id}`: Atualiza os dados de um endereço existente.
- `GET /endereco/{id}`: Retorna os detalhes de um endereço específico.
- `GET /endereco/por-cep/{cep}`: Retorna os detalhes de um endereço com base no CEP.
- `DELETE /endereco/{id}`: Exclui um endereço existente.

Para mais detalhes sobre os endpoints disponíveis, consulte a documentação da API.

## Documentação da API

A documentação da API pode ser acessada através do Swagger UI. Após iniciar a aplicação, visite `http://localhost:8080/swagger-ui.html` para visualizar e interagir com os endpoints disponíveis.

## Testes Realizados
![image](https://github.com/mgvictoriano/Gerenciamento-Pessoas/assets/108932728/bb8499fd-8e47-4c15-88a8-6165077c1028)
![image](https://github.com/mgvictoriano/Gerenciamento-Pessoas/assets/108932728/2441a71d-d89c-4dc1-9112-67c08aec4d71)
![image](https://github.com/mgvictoriano/Gerenciamento-Pessoas/assets/108932728/bcddc8d5-d992-4e23-adfd-419ad7d3cdde)
![image](https://github.com/mgvictoriano/Gerenciamento-Pessoas/assets/108932728/10137287-43ee-468e-bd6c-3c7187451a09)



## Autoria

Criado e Desenvolvido por *Michelle Gaia Victoriano*

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para obter mais detalhes.



