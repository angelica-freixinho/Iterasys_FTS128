#language: pt
#encoding: utf8
  Funcionalidade: Listas
    Criar e manter listas
    Persona: Thais
    Tipo: Analista Financeiro / Usuaria Frequente

  Cenario: Criar uma lista
    Dado que eu acesso o site Microsoft To Do
    Quando clico no icone do usuario
    Então o site realiza o login e exibe a pagina do To Do
    Quando clico em Nova Lista
    E preencho "Lista de Compras" e pressiono Enter
    Então exibe a Lista de Compras vazia

  Cenario: Alterar uma lista
    Dado que eu estou no Microsoft To Do
    Quando clico em "Lista de Compras"
    E Altero o nome para "Compras" e pressiono Enter
    Então exibe a lista Compras vazia

  Cenario: Consultar uma lista
    Dado que eu estou no Microsoft To Do
    Quando clico em "Lista de Compras"
    Então exibe a Lista de Compras vazia

  Cenario: Excluir uma lista
    Dado que eu estou no Microsoft To Do
    Quando clico em "Lista de Compras" com o botão direito do mouse
    E seleciono "Excluir Lista"
    Então exibe uma tela de Confirmação com a mensagem: "Lista de Compras" será definitivamente excluida. Você não poderá desfazer essa ação.

  Cenario Compartilhar uma Lista
    Dado que eu estou no Microsoft To Do
    Quando clico em "Lista de Compras" com o botão direito do mouse
    E seleciono "Compartilhar Lista"
    Então exibe uma tela com a mensagem: "Convide algumas pessoas. Depois que Ingressarem, voce poderá ve-las aqui.
