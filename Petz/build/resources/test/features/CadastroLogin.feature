#language: pt


Funcionalidade: Fazer cadastro ou Login
  Como um cliente eventual gostaria de me cadastrar e ter acesso
  como cliente do site da Petz


  Esquema do Cenário: realizar Cadastro
    Dado que acesso a pagina da Petz
    Quando passo o mouse na aba Entre e seleciono Criar "conta"
    Entao exibe a pagina de cadastro relacionado a "Conta"
    Quando Prencho os campos com <NomeCompleto> e <Email> e <DDD> e <Celular> e <Sexo> e <Data_nasc> e <CPF> e <Senha> e <Confsenha> e pressiono Salvar
    Entao exibe a tela de Aviso com o texto "Dados salvos com sucesso"
    E pressiono Entendi para concluir
    Exemplos:

      | NomeCompleto | Email | DDD | Celular | Sexo | Data_nasc| CPF | Senha | Confsenha |
      | "Sueli Larissa Sophia Melo" | "suelimelo@maildrop.cc" | "63" | "995066911" | "F" | "01/02/1990" | "40687866588" | "3oOffAYHr4" | "3oOffAYHr4" |
      | "Benjamin Victor Peixoto" | "bvictorp@maildrop.cc" | "98" | "996117778" | "M" | "14/01/1966" | "60066658837" | "tcgYBaMf7P" | "tcgYBaMf7P" |
      | "Caio Lucas Davi Cavalcanti" | "caiodc@maildrop.cc" | "63" | "982765741" | "M" | "24/05/1978" | "91403281041" | "calu@2021" | "calu@2021" |



  Esquema do Cenário: cenario: login com sucesso
    Dado que acesso a pagina da Petz
    Quando passo o mouse na aba Entre e seleciono "Entrar"
    Entao exibe a pagina de login com "Faça seu Login"
    Quando preencho o email <Email> e digito a senha <Senha> e clico em Entrar
    Entao o site realiza o login e exibe o icone do usuario <Nome>

    Exemplos:
      | Email | Senha | Nome |
      | "suelimelo@maildrop.cc" | "3oOffAYHr4" | "Sueli" |
      | "bvictorp@maildrop.cc" | "tcgYBaMf7P" | "Benjamin" |
      | "caiodc@maildrop.cc" | "calu@2021" | "Caio" |


  Esquema do Cenário: login ou senha incorreto
    Dado que acesso a pagina da Petz
    Quando passo o mouse na aba Entre e seleciono "Entrar"
    Entao exibe a pagina de login com "Faça seu Login"
    Quando preencho o email <Email> e digito a senha <Senha> e clico em Entrar
    Entao o site exibe a mensagem "Dados incorretos!"

    Exemplos:
      | Email | Senha |
      | "suelimel@maildrop.cc" | "3oOffAYHr4" |
      | "bvictorp@maildrop.cc" | "tcg33aMf7P" |
      | "caiodc@maildrop.cc" | "caio@2021" |


