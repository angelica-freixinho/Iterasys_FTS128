#language: pt

  Funcionalidade: Consultar Produto

    como um cliente eventual,  gostaria de consultar a disponibilidade e
    preço de alguns produtos que tenho interesse em adquirir

    Cenario: Consulta Produto Fixo com Enter
      Dado que acesso o site da Petz "1"
      Quando procuro por "Ração" e pressiono Enter
      Entao exibe a lista de produtos relacionados a "Ração"
      Quando seleciono o primeiro produto da lista
      Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

    Cenario: Consulta Produto Fixo com Clique na Lupa
      Dado que acesso o site da Petz "2"
      Quando procuro por "Ração" e clico na Lupa
      Entao exibe a lista de produtos relacionados a "Ração"
      Quando seleciono o primeiro produto da lista
      Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

    Cenario: Consulta Produto Fixo que Não Existe com Enter
      Dado que acesso o site da Petz "3"
      Quando procuro por "sadfsdfgsdfsdsd" e pressiono Enter
      Entao exibe uma lista de produtos aproximados e a mensagem de que nao encontrou "sadfsdfgsdfsdsd"

    Cenario: Consulta Produto Fixo Com Menos de 3 Letras com Enter com Enter
      Dado que acesso o site da Petz "4"
      Quando procuro por "ab" e pressiono Enter
      Entao exibe uma caixa de alerta dizendo que precisa preencher pelo menos tres letras no termo

    Esquema do Cenario:
      Dado que acesso o site da Petz <id>
      Quando procuro por <produto> e pressiono Enter
      Entao exibe a lista de produtos relacionados a <produto>
      Quando seleciono o <produtoDescricao> da lista
      Entao verifico a <marca> com o <precoNormal> e <precoAssinante>

      Exemplos:
      | id | produto | produtoDescricao | marca | precoNormal | precoAssinante |
      | "5" | "Ração" | "Ração Royal Canin Maxi - Cães Adultos - 15kg" | "Royal Canin" | "R$ 232,69" | "R$ 209,42" |
      | "6" | "Petisco" | "Snack Petz Cuidado Oral para Cães de Pequeno Porte" | "Petz" | "R$ 3,99" | "R$ 3,59" |
