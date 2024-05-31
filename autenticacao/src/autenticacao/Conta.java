package autenticacao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Representa uma conta de usuário com login e senha.
 * A senha é armazenada como um hash SHA-256 codificado em Base64.
 */
public class Conta {
    private static long nextid = 1;
    private long id;
    private String login;
    private String senha;

    /**
     * Constrói uma nova conta com o login e senha fornecidos.
     * A senha é criptografada antes de ser armazenada.
     *
     * @param login o login da conta
     * @param senha a senha da conta
     */
    public Conta(String login, String senha) {
        this.id = nextid++; // Atribui o valor atual de nextid e depois incrementa
        this.login = login;
        this.senha = hashPassword(senha);
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = hashPassword(senha);
    }

    /**
     * Cria um hash SHA-256 da senha fornecida e a codifica em Base64.
     *
     * @param password a senha a ser criptografada
     * @return a senha criptografada em Base64
     */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Cria um hash SHA-256 da senha fornecida e a codifica em Base64.
     * Método estático para ser utilizado fora da instância da classe.
     *
     * @param password a senha a ser criptografada
     * @return a senha criptografada em Base64
     */
    public static String hashPasswordStatic(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
