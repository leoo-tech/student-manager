import java.util.*;
import java.sql.*;

public class aluno1 {

  // Função para cadastrar um aluno
  public static void cadastrarAluno(Scanner scanner) {
    System.out.println("Digite a matrícula do aluno:");
    String matricula = scanner.nextLine();
    System.out.println("Digite o nome do aluno:");
    String nome = scanner.nextLine();

    try {
      // Carrega o driver JDBC do MySQL
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Conecta ao banco de dados MySQL
      Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_estacio", "root", "numsey");

      // Insere os dados do aluno no banco de dados
      String sql = "INSERT INTO tabela_java (matricula, nome) VALUES (?, ?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, matricula);
      pstmt.setString(2, nome);
      pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println("Ocorreu um erro ao escrever no banco de dados. Verifique as configurações de conexão.");
      e.printStackTrace();
    }
  }

  // Função para listar todos os registros
  public static void listarRegistros() {
    try {
      // Carrega o driver JDBC do MySQL
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Conecta ao banco de dados MySQL
      Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_estacio", "root", "numsey");

      // Lê os dados dos alunos do banco de dados
      String sql = "SELECT * FROM tabela_java";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(rs.getString("matricula") + "," + rs.getString("nome"));
      }
    } catch (Exception e) {
      System.out.println("Ocorreu um erro ao ler o banco de dados. Verifique as configurações de conexão.");
      e.printStackTrace();
	  
    }

    // Instrução para teclar enter para continuar
    System.out.println("Pressione ENTER para continuar...");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean continuar = true;
    while (continuar) {
      // Limpa a tela
      System.out.print("\033[H\033[2J");
      System.out.flush();

      System.out.println("1 = Cadastrar aluno");
      System.out.println("2 = Listar todos registros");
      System.out.println("3 = Finalizar programa");
      System.out.println("Digite a opção desejada:");
      int option = scanner.nextInt();
      scanner.nextLine(); // Consumir sobras de nova linha

      if (option == 1) {
        cadastrarAluno(scanner);
      } else if (option == 2) {
        listarRegistros();
      } else if (option == 3) {
        System.out.println("Finalizando o programa...");
        continuar = false;
      } else {
        System.out.println("Opção inválida. Tente novamente.");
      }
    }
  }
}
