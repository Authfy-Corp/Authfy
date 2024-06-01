# Authfy
Authfy é um componente de autenticação de login e senha que permite adicionar, verificar e gerenciar contas de usuário em um sistema. Ele oferece funcionalidades como adicionar uma nova conta, verificar se um login já está em uso, verificar se as credenciais (login e senha) são válidas, trocar a senha de um usuário, deletar uma conta e verificar se uma conta foi deletada. Essas funcionalidades permitem um controle eficaz sobre as contas de usuário, garantindo a segurança e a integridade do sistema.

# Features
1. Adicionar Conta: Permite adicionar uma nova conta de usuário ao sistema.
2. Verificar Login Existente: Verifica se um determinado nome de usuário já existe no sistema.
3. Verificar Credenciais: Verifica se um par de nome de usuário e senha é válido.
4. Trocar Senha: Permite que um usuário troque sua senha atual por uma nova.
5. Deletar Conta: Permite excluir uma conta de usuário do sistema.
6. Verificar Conta Deletada: Verifica se uma conta de usuário foi corretamente excluída do sistema.

# Metodos
Adicionar Conta:

Método: adicionarConta(Conta conta)
Funcionalidade: Adiciona uma nova conta ao sistema.
Como Funciona: Recebe um objeto Conta como parâmetro e adiciona essa conta ao sistema.
Como Utilizar:
```java
Conta novaConta = new Conta("usuario_teste", "senha_teste");
contaManager.adicionarConta(novaConta);
```

Método: verificarLoginExistente(String login)
Funcionalidade: Verifica se um login já está em uso no sistema.
Como Funciona: Recebe um login como parâmetro e verifica se esse login já existe no sistema.
Como Utilizar:
```java
boolean loginExistente = contaManager.verificarLoginExistente("usuario_teste");
```

Método: verificarCredenciais(String login, String senha)
Funcionalidade: Verifica se as credenciais (login e senha) são válidas.
Como Funciona: Recebe um login e uma senha como parâmetros e verifica se essas credenciais são válidas.
Como Utilizar:
```java
boolean credenciaisValidas = contaManager.verificarCredenciais("usuario_teste", "senha_teste");
```

Método: trocarSenha(String login, String novaSenha)
Funcionalidade: Troca a senha de um usuário.
Como Funciona: Recebe um login e uma nova senha como parâmetros e atualiza a senha do usuário correspondente.
Como Utilizar:
```java
contaManager.trocarSenha("usuario_teste", "nova_senha_teste");
```

Método: deletarConta(String login)
Funcionalidade: Deleta uma conta do sistema.
Como Funciona: Recebe um login como parâmetro e remove a conta correspondente do sistema.
Como Utilizar:
```java
contaManager.deletarConta("usuario_teste");
```

Método: verificarContaDeletada(String login)
Funcionalidade: Verifica se uma conta foi deletada do sistema.
Como Funciona: Recebe um login como parâmetro e verifica se a conta correspondente foi removida do sistema.
Como Utilizar:
```java
boolean contaDeletada = contaManager.verificarContaDeletada("usuario_teste");
```

Esses métodos permitem interagir com as contas de usuário no sistema, desde a adição até a exclusão e verificação de credenciais.

