package autenticacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import java.io.IOException;

/**
 * Configurador de banco de dados que gerencia as conexões com o banco de dados.
 */
public class DatabaseConfigurator {
    private String url;
    private String username;
    private String password;

    /**
     * Constrói um configurador de banco de dados com os detalhes de conexão fornecidos.
     *
     * @param url      a URL do banco de dados
     * @param username o nome de usuário do banco de dados
     * @param password a senha do banco de dados
     */
    public DatabaseConfigurator(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Obtém uma conexão com o banco de dados usando os detalhes de conexão fornecidos.
     *
     * @return uma conexão com o banco de dados
     * @throws SQLException se ocorrer um erro ao obter a conexão
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Fecha uma conexão com o banco de dados.
     *
     * @param conn a conexão a ser fechada
     */
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Cria um configurador de banco de dados a partir de um arquivo de configuração do DBeaver.
     *
     * @param dbeaverConfigFilePath o caminho do arquivo de configuração do DBeaver
     * @return um configurador de banco de dados configurado
     */
    public static DatabaseConfigurator fromDBeaverConfig(String dbeaverConfigFilePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(dbeaverConfigFilePath)));
            JSONObject json = new JSONObject(content);

            String url = json.getString("url");
            String username = json.getString("username");
            String password = json.getString("password");

            return new DatabaseConfigurator(url, username, password);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the DBeaver configuration file", e);
        }
    }
}
