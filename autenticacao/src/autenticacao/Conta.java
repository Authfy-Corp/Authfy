package autenticacao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


/**
 * Representa uma conta de usuário com login e senha.
 * A senha é armazenada como um hash SHA-256 codificado em Base64,
 * com a adição de um salt para aumentar a segurança.
 */
public class Conta {
    private static final SecureRandom random = new SecureRandom();
    private static final int SALT_LENGTH = 16;

    private long id;
    private String login;
    private String senhaHash;
    private byte[] salt;

    /**
     * Constrói uma nova conta com o login e senha fornecidos.
     * A senha é criptografada usando SHA-256 com um salt aleatório.
     *
     * @param login o login da conta
     * @param senha a senha da conta
     */
    public Conta(String login, String senha) {
        this.id = generateId();
        this.login = login;
        this.salt = generateSalt();
        this.senhaHash = hashPassword(senha, salt);
    }

    /**
     * Obtém o ID da conta.
     *
     * @return o ID da conta
     */
    public long getId() {
        return id;
    }

    /**
     * Obtém o login da conta.
     *
     * @return o login da conta
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o login da conta.
     *
     * @param login o novo login da conta
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Valida se a senha fornecida corresponde à senha da conta.
     *
     * @param senha a senha a ser validada
     * @return true se a senha for válida, false caso contrário
     */
    public boolean validarSenha(String senha) {
        String hashedPassword = hashPassword(senha, salt);
        return senhaHash.equals(hashedPassword);
    }

    // Métodos privados para geração de ID, salt e hash de senha

    private long generateId() {
        return random.nextLong();
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt);
            byte[] hashedBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criar hash da senha", e);
        }
    }
}
