#language: pt
#encoding: utf8
 Funcionalidade: Usuario
   Criar, manter e autenticar usuarios
   Cenario: Login com Sucesso
     Dado que estou em Microsoft To Do e deslogado
     Quando clico no icone para realizar o login
     Então exibe a pagina de login
     Quando preencho o email "correia@iterasys.com.br" e clico em Proximo
     E seleciona a conta pessoal
     E digita a senha "teste123" e clico em Entrar
     Então o site realiza o login e exibe a pagina do To Do

   Cenario: Login Incorreto
     Dado que estou em Microsoft To Do e deslogado
     Quando clico no icone para realizar o login
     Então exibe a pagina de login
     Quando preencho o email "correia@iterasys.com.br" e clico em Proximo
     E seleciona a conta pessoal
     E digita a senha "Atena2020" e clico em Entrar
     Então o site exibe a mensagem de erro: Sua conta ou senha esta incorreta. Se você não se lembra de sua senha, redefina-a agora.

   Cenario: Esqueci a senha do Usuario
     Dado que estou em Microsoft To Do e deslogado
     Quando clico no icone para realizar o login
     Então exibe a pagina de login
     Quando preencho o email "correia@iterasys.com.br" e clico em Proximo
     E seleciona a conta pessoal e clico em Esqueceu a senha
     Então o site exibe a mensagem: Precisamos verificar a sua identidade
     Quando seleciona o email e clico em Obter codigo
     Então o site exibe a mensage: Acabamos de enviar um código para correia@iterasys.com.br Verifique em seu email se recebeu uma mensagem da equipe de contas da Microsoft e digite o código aqui.
     Quando digito o codigo e clico em Proximo
     Então exibe a pagina de redefinir senha
     Quando preencho os campos: nova senha e redigitar senha e clico em Proximo
     Então o site exibe a mensagem: Sua senha foi alterada
     Quando clico em Enter
     Então o site vai para a tela de login
     Quando preencho o email "correia@iterasys.com.br" e clico em Proximo
     E seleciona a conta pessoal
     E digita a senha "teste123" e clico em Entrar
     Então o site realiza o login e exibe a pagina do To Do

   Cenario: Alterar a senha do usuario
     Dado que estou logado em Microsoft To Do
     Quando clico no icone "Minha conta" no canto superior direito e seleciono Meu perfil
     Então exibe a pagina do Perfil
     Quando clico em alterar sua senha e preencho as informações: senha atual e nova senha
     E clico no botao salvar
     Então o site retorna para a página do perfil

