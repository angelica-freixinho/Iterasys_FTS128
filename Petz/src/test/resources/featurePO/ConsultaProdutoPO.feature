#language: pt
#encoding: utf8

Funcionalidade: Consultar Produto PO
  como um cliente eventual,  gostaria de consultar a disponibilidade e
  preço de alguns produtos que tenho interesse em adquirir

  Cenario: Consulta Produto Fixo com Enter PO
    Dado que acesso o site da Petz "1" PO
    Quando procuro por "Ração" e pressiono Enter PO
    Entao exibe a lista de produtos relacionados a "Ração" PO
    Quando seleciono o produto "Ração Royal Canin Maxi - Cães Adultos - 15kg" da lista PO
    Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes PO

  Esquema do Cenario: Consulta Produto Fixo com Enter - PO
    Dado que acesso o site da Petz <id> PO
    Quando procuro por <produto> e pressiono Enter PO
    Entao exibe a lista de produtos relacionados a <produto> PO
    Quando seleciono o produto <produtoDescricao> da lista PO
    Entao verifico a marca como <marca> com preco normal de <precoNormal> e <precoAssinante> para assinantes PO
    Exemplos:
      | id  | produto   | produtoDescricao                                     |  marca        | precoNormal | precoAssinante |
      | "2" | "Ração"   | "Ração Royal Canin Maxi - Cães Adultos - 15kg"       | "Royal Canin" | "R$ 232,69" | "R$ 209,42"    |
      | "3" | "Petisco" | "Snack Petz Cuidado Oral para Cães de Pequeno Porte" | "Petz"        | "R$ 4,99"   |   "R$ 4,49"    |
