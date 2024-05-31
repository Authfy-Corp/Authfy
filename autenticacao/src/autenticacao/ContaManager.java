package autenticacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Gerenciador de contas que fornece operações para adicionar, verificar,
 * trocar senha e deletar contas de usuários.
 */
public class ContaManager {
    private DatabaseConfigurator databaseConfigurator;

    /**
     * Constrói um gerenciador de contas com o configurador de banco de dados fornecido.
     *
     * @param databaseConfigurator o configurador de banco de dados
     */
    public ContaManager(DatabaseConfigurator databaseConfigurator) {
        this.databaseConfigurator = databaseConfigurator;
    }

    /**
     * Adiciona uma nova conta ao banco de dados.
     *
     * @param conta a conta a ser adicionada
     */
    public void adicionarConta(Conta conta) {
        String sql = "INSERT INTO CONTA (login, senha) VALUES (?, ?)";

        try (Connection conn = databaseConfigurator.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, conta.getLogin());
            pstmt.setString(2, conta.getSenha());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica se um login já existe no banco de dados.
     *
     * @param login o login a ser verificado
     * @return true se o login existir, false caso contrário
     */
    public boolean verificarLoginExistente(String login) {
        String sql = "SELECT COUNT(*) FROM CONTA WHERE login = ?";

        try (Connection conn = databaseConfigurator.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verifica se as credenciais fornecidas são válidas.
     *
     * @param login o login da conta
     * @param senha a senha da conta
     * @return true se as credenciais forem válidas, false caso contrário
     */
    public boolean verificarCredenciais(String login, String senha) {
        String sql = "SELECT senha FROM CONTA WHERE login = ?";

        try (Connection conn = databaseConfigurator.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String senhaHashed = rs.getString("senha");
                return senhaHashed.equals(Conta.hashPasswordStatic(senha));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Troca a senha de uma conta existente.
     *
     * @param login    o login da conta
     * @param novaSenha a nova senha a ser definida
     */
    public void trocarSenha(String login, String novaSenha) {
        String sql = "UPDATE CONTA SET senha = ? WHERE login = ?";

        try (Connection conn = databaseConfigurator.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Conta.hashPasswordStatic(novaSenha));
            pstmt.setString(2, login);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta uma conta do banco de dados.
     *
     * @param login o login da conta a ser deletada
     */
    public void deletarConta(String login) {
        String sql = "DELETE FROM CONTA WHERE login = ?";

        try (Connection conn = databaseConfigurator.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica se uma conta foi deletada do banco de dados.
     *
     * @param login o login da conta a ser verificada
     * @return true se a conta ainda existir, false caso contrário
     */
    public boolean verificarContaDeletada(String login) {
        return !verificarLoginExistente(login);
    }
}
