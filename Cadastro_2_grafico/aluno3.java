import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class aluno3 {

  // Função para cadastrar um aluno
  public static void cadastrarAluno() {
    String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno:");
    String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
    try {
      // Escreve os dados do aluno em um arquivo
      FileWriter writer = new FileWriter("alunos.txt", true);
      writer.write(matricula + "," + nome + "\n");
      writer.close();
      JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Ocorreu um erro ao escrever no arquivo. Verifique as permissões do arquivo e o caminho do arquivo.");
      e.printStackTrace();
    }
  }

  // Função para listar todos os registros
  public static void listarRegistros() {
    try (BufferedReader reader = new BufferedReader(new FileReader("alunos.txt"))) {
      String line;
      StringBuilder sb = new StringBuilder();
      sb.append("Listando todos os alunos cadastrados:\n");
      while ((line = reader.readLine()) != null) {
        if (!line.isEmpty()) {
          sb.append(line).append("\n");
        }
      }
      JOptionPane.showMessageDialog(null, sb.toString());
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Ocorreu um erro ao ler o arquivo. Verifique o caminho do arquivo.");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    boolean continuar = true;
    while (continuar) {
      String[] options = {"Cadastrar aluno", "Listar todos registros", "Finalizar programa"};
      int option = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Sistema de Cadastro de Alunos", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

      if (option == 0) {
        cadastrarAluno();
      } else if (option == 1) {
        listarRegistros();
      } else if (option == 2) {
        JOptionPane.showMessageDialog(null, "Finalizando o programa...");
        continuar = false;
      } else {
        JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
      }
    }
  }
}
