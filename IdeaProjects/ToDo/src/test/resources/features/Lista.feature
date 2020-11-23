#language: pt
#encoding: utf8
  Funcionalidade: lista
  Criar e manter itens em uma lista
  Esquema do Cenário: Incluir itens na lista
    Dado que estou na Lista de Compras
    Quando digito <item> e pressiono Enter
    Então exibe o <item> na Lista de Compras
    Exemplos:
      | item              |
      | "macarrao"        |
      | "molho de tomate" |

  Esquema do Cenario: Alterar itens na lista
    Dado que estou na Lista de Compras
    Quando seleciono <item> e no painel da direita clico no <item>
    Então altero o nome do <item> na Lista de Compras
    Exemplos:
      | item              |
      | "macarrao"        |
      | "molho de tomate" |

  Esquema do Cenario: Consultar itens na lista
    Dado que estou no Microsoft To Do
    Quando seleciono Lista de Compras
    Então exibe os nomes de cada <item> da Lista de Compras
    Exemplos:
      | item              |
      | "macarrao"        |
      | "molho de tomate" |

  Esquema do Cenario: Excluir itens na lista
    Dado que estou na Lista de Compras
    Quando clico com o botao direito do mouse no <item> e seleciono Excluir tarefa
    Então a tarefa do <item> na Lista de Compras é excluida
    Exemplos:
      | item              |
      | "macarrao"        |
      | "molho de tomate" |

