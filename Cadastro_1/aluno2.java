import java.util.*;
import java.io.*;

public class aluno2 {

  // Função para cadastrar um aluno
  public static void cadastrarAluno(Scanner scanner) {
    System.out.println("Digite a matrícula do aluno:");
    String matricula = scanner.nextLine();
    System.out.println("Digite o nome do aluno:");
    String nome = scanner.nextLine();
    try {
      // Escreve os dados do aluno em um arquivo
      FileWriter writer = new FileWriter("alunos.txt", true);
      writer.write(matricula + "," + nome + "\n");
      writer.close();
      System.out.println("Aluno cadastrado com sucesso!");
    } catch (IOException e) {
      System.out.println("Ocorreu um erro ao escrever no arquivo. Verifique as permissões do arquivo e o caminho do arquivo.");
      e.printStackTrace();
    }
  }

  // Função para listar todos os registros
  public static void listarRegistros() {
    try (BufferedReader reader = new BufferedReader(new FileReader("alunos.txt"))) {
      String line;
      System.out.println("Listando todos os alunos cadastrados:");
      while ((line = reader.readLine()) != null) {
        if (!line.isEmpty()) {
          System.out.println(line);
        }
      }
    } catch (IOException e) {
      System.out.println("Ocorreu um erro ao ler o arquivo. Verifique o caminho do arquivo.");
      e.printStackTrace();
    }
    
    // Instrução para teclar enter para continuar
    System.out.println("\nPressione ENTER para continuar...");
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

      System.out.println("==================================");
      System.out.println("= Sistema de Cadastro de Alunos  =");
      System.out.println("==================================");
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
