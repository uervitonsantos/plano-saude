# API REST para Gerenciamento de Beneficiários e Documentos

Este projeto é uma API REST desenvolvida em Java 17 com Spring Boot 3 para gerenciar beneficiários e seus documentos. 
A API oferece recursos para cadastrar, listar, atualizar e remover beneficiários, além de listar todos os documentos 
associados a um beneficiário específico. A documentação da API é gerada automaticamente usando o Swagger 3.

## Tecnologias Utilizadas

- Java 17
- Mavem
- Spring Boot 3
- Lombok
- Swagger 3
- Banco de Dados H2

 ## Estrutura do Projeto

  projeto segue uma arquitetura modularizada e organizada com os seguintes pacotes:

* entidade: Contém as entidades de dados, como Beneficiario e Documento.
* canonico: Define os objetos canônicos para representar as entidades, como BeneficiarioCanonical e DocumentoCanonical.
* builder: Para construir objetos complexos, como BeneficiarioBuilder e DocumentoBuilder.
* dto: Classes para transferir dados entre a API e o cliente, como BeneficiarioDTO e DocumentoDTO.
* factory: Classes para criar instâncias de objetos, como BeneficiarioFactory e DocumentoFactory.
* repository: Implementações de repositórios para interagir com o banco de dados, como BeneficiarioRepository e DocumentoRepository.
* service: Lógica de negócios e manipulação de dados, como BeneficiarioService e DocumentoService.
* controller: Controladores REST que definem os endpoints da API, como BeneficiarioController e DocumentoController.

  ## Endpoints da API

  A API oferece os seguintes endpoints:

* `POST` /beneficiarios: Cadastrar um novo beneficiário junto com seus documentos.
* `GET` /beneficiarios: Listar todos os beneficiários cadastrados.
* `GET` /beneficiarios/{id}/documentos: Listar todos os documentos de um beneficiário específico.
* `PUT` /beneficiarios/{id}: Atualizar os dados cadastrais de um beneficiário.
* `DELETE` /beneficiarios/{id}: Remover um beneficiário.

## Documentação da API

A documentação da API é gerada automaticamente usando o Swagger 3. Para acessá-la, inicie a aplicação e abra a seguinte URL no seu navegador:

 ```bash
http://localhost:8080/swagger-ui/index.html
 ```

## Configuração e Execução

1. Clone este repositório:
   ```bash
   git clone https://github.com/uervitonsantos/plano-saude.git
   ```
   
2. Importe o projeto na sua IDE preferida.

3. Execute a aplicação e ela irá criar 3 beneficiários automaticamente.

4. Acesse a documentação da API conforme descrito acima para obter detalhes sobre os endpoints e como usá-los.

## Exemplo de json

```json
[
    {
        "beneficiarioID": 1,
        "nomeBeneficiario": "Uerviton Silva dos Santos",
        "telefone": "1137326584",
        "datanascimento": "1988-06-15",
        "dataInclusaoBeneficiario": "2024-06-11T14:28:47.798843",
        "dataAtuazacaoBeneficiario": "2024-06-11T14:28:47.798843",
        "documentos": [
            {
                "documentoID": 1,
                "tipoDocumento": "CPF",
                "numeroDocumento": "123.456.789-00",
                "descricao": "Dcumento do beneficiario 1",
                "dataInclusaoDocumento": "2024-06-11T14:28:47.798843",
                "dataAtuazacaoDocumento": "2024-06-11T14:28:47.798843"
            },
            {
                "documentoID": 2,
                "tipoDocumento": "RG",
                "numeroDocumento": "12.345.678-9",
                "descricao": "Dcumento do beneficiario 1",
                "dataInclusaoDocumento": "2024-06-11T14:28:47.798843",
                "dataAtuazacaoDocumento": "2024-06-11T14:28:47.798843"
            }
        ]
    },
    {
        "beneficiarioID": 2,
        "nomeBeneficiario": "João dos Santos",
        "telefone": "1158322483",
        "datanascimento": "1978-02-11",
        "dataInclusaoBeneficiario": "2024-06-11T14:28:47.798843",
        "dataAtuazacaoBeneficiario": "2024-06-11T14:28:47.798843",
        "documentos": [
            {
                "documentoID": 3,
                "tipoDocumento": "CPF",
                "numeroDocumento": "143.486.989-10",
                "descricao": "Dcumento do beneficiario 2",
                "dataInclusaoDocumento": "2024-06-11T14:28:47.798843",
                "dataAtuazacaoDocumento": "2024-06-11T14:28:47.798843"
            },
            {
                "documentoID": 4,
                "tipoDocumento": "RG",
                "numeroDocumento": "15.385.978-10",
                "descricao": "Dcumento do beneficiario 2",
                "dataInclusaoDocumento": "2024-06-11T14:28:47.798843",
                "dataAtuazacaoDocumento": "2024-06-11T14:28:47.798843"
            }
        ]
    },
    {
        "beneficiarioID": 3,
        "nomeBeneficiario": "Marcos dos Santos",
        "telefone": "1136825483",
        "datanascimento": "1998-07-19",
        "dataInclusaoBeneficiario": "2024-06-11T14:28:47.798843",
        "dataAtuazacaoBeneficiario": "2024-06-11T14:28:47.798843",
        "documentos": [
            {
                "documentoID": 5,
                "tipoDocumento": "CPF",
                "numeroDocumento": "383.656.948-10",
                "descricao": "Dcumento do beneficiario 3",
                "dataInclusaoDocumento": "2024-06-11T14:28:47.798843",
                "dataAtuazacaoDocumento": "2024-06-11T14:28:47.798843"
            },
            {
                "documentoID": 6,
                "tipoDocumento": "RG",
                "numeroDocumento": "17.384.288-02",
                "descricao": "Dcumento do beneficiario 2",
                "dataInclusaoDocumento": "2024-06-11T14:28:47.798843",
                "dataAtuazacaoDocumento": "2024-06-11T14:28:47.798843"
            }
        ]
    }
]
```

