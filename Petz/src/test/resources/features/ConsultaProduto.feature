#language: pt


  Funcionalidade: Consultar Produto

    como um cliente eventual,  gostaria de consultar a disponibilidade e
    preço de alguns produtos que tenho interesse em adquirir

    Cenario: Consulta Produto Fixo com Enter
      Dado que acesso o site da Petz
      Quando procuro por "Ração" e pressiono Enter
      Entao exibe a lista de produtos relacionados a "Ração"
      Quando seleciono o primeiro produto da lista
      Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

    Cenario: Consulta Produto Fixo com Clique na Lupa
      Dado que acesso o site da Petz
      Quando procuro por "Ração" e clico na Lupa
      Entao exibe a lista de produtos relacionados a "Ração"
      Quando seleciono o primeiro produto da lista
      Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

    Cenario: Consulta Produto Fixo que Não Existe com Enter
      Dado que acesso o site da Petz
      Quando procuro por "sadfsdfgsdfsdsd" e pressiono Enter
      Entao exibe uma lista de produtos aproximados e a mensagem de que nao encontrou "sadfsdfgsdfsdsd"

    Cenario: Consulta Produto Fixo Com Menos de 3 Letras com Enter com Enter
      Dado que acesso o site da Petz
      Quando procuro por "ab" e pressiono Enter
      Entao exibe uma caixa de alerta dizendo que precisa preencher pelo menos 3 letras no termo

