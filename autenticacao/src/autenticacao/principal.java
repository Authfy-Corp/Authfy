package autenticacao;

public class principal {

	public static void main(String[] args) {
		
		// Substitua pelo caminho do arquivo de configuração do DBeaver
        String dbeaverConfigFilePath = "C:\\Users\\Caio Bisesti\\eclipse-workspace\\autenticacao\\src\\autenticacao\\dbeaver-config.json";

        DatabaseConfigurator dbConfigurator = DatabaseConfigurator.fromDBeaverConfig(dbeaverConfigFilePath);
        ContaManager contaManager = new ContaManager(dbConfigurator);

        // Criar e adicionar uma nova conta
        Conta novaConta = new Conta("usuario_teste", "senha_teste");
        contaManager.adicionarConta(novaConta);
        
        // Verificar se o login existe
        boolean loginExistente = contaManager.verificarLoginExistente("usuario_teste");
        System.out.println("Login existente: " + loginExistente);

        // Verificar credenciais
        boolean credenciaisValidas = contaManager.verificarCredenciais("usuario_teste", "senha_teste");
        System.out.println("Credenciais válidas: " + credenciaisValidas);

        // Trocar senha
        contaManager.trocarSenha("usuario_teste", "nova_senha_teste");

        // Verificar credenciais com a nova senha
        credenciaisValidas = contaManager.verificarCredenciais("usuario_teste", "nova_senha_teste");
        System.out.println("Credenciais válidas com nova senha: " + credenciaisValidas);

        // Deletar conta
        contaManager.deletarConta("usuario_teste");
        // Verificar se a conta foi deletada
        boolean contaDeletada = contaManager.verificarContaDeletada("usuario_teste");
        System.out.println("Conta deletada: " + contaDeletada);
 
        
	}
}