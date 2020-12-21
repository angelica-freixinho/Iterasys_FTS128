#language: pt
#encoding: utf8

Funcionalidade: Consultar Produto

  como um cliente,  gostaria de consultar a disponibilidade e
  preço de alguns produtos que desejo adquirir

  Esquema do Cenario: Consulta produtos com clique na lupa
    Dado que acesso o site da Cobasi <id>
    Quando procuro por <produto> e clico na lupa
    Entao exibe a lista de produtos relacionados a <produto>
    Quando seleciono o <produtoDescricao> da lista
    Entao verifico a <marca> com o <precoNormal> e o <precoAssinante>

    Exemplos:
     | id | produto | produtoDescricao | marca | precoNormal | precoAssinante |
     | "1" | "Ração"   | "Ração Premier Formula Raças Pequenas Cães Adultos" | "Premier" | "R$ 30,90" | "R$ 27,81" |
     | "2" | "Petisco" | "Petisco Snack Origem Natural Calming Care"         | "Origem Natural"   | "R$ 6,90"  | "R$ 6,21"  |
