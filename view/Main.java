import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDAO petDAO = new PetDAO();
        int opcao = 0;

        System.out.println("==========================================");
        System.out.println("   CLÍNICA VETERINÁRIA CÃO FELIZ - SISTEMA");
        System.out.println("==========================================");

        while (opcao != 3) {
            System.out.println("\n--- MENU DA RECEPÇÃO ---");
            System.out.println("1 - Cadastrar Pet");
            System.out.println("2 - Listar Pets");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("\n[AVISO] Digite apenas números válidos (1, 2 ou 3).");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO CADASTRO ---");

                    // Dados do Tutor (herdados de Pessoa + específicos de Tutor)
                    System.out.print("Nome do Tutor: ");
                    String nomeTutor = scanner.nextLine();
                    System.out.print("CPF do Tutor: ");
                    String cpfTutor = scanner.nextLine();
                    System.out.print("Data de Nascimento do Tutor: ");
                    String dataNascTutor = scanner.nextLine();
                    System.out.print("Telefone do Tutor: ");
                    String telefoneTutor = scanner.nextLine();
                    System.out.print("Endereço do Tutor: ");
                    String enderecoTutor = scanner.nextLine();
                    System.out.print("Tipo de Consulta: ");
                    String tipoDeConsulta = scanner.nextLine();

                    Tutor tutor = new Tutor();
                    tutor.cadastrar(nomeTutor, cpfTutor, dataNascTutor);
                    tutor.setTelefone(telefoneTutor);
                    tutor.setEndereco(enderecoTutor);
                    tutor.setTipoDeConsulta(tipoDeConsulta);

                    // Dados do Pet conforme o diagrama
                    System.out.print("Nome do Pet: ");
                    String nomePet = scanner.nextLine();
                    System.out.print("Raça: ");
                    String raca = scanner.nextLine();
                    System.out.print("Data de Nascimento do Pet: ");
                    String dataNascPet = scanner.nextLine();
                    System.out.print("Peso do Pet (kg): ");
                    double peso = scanner.nextDouble();
                    scanner.nextLine();

                    // Instancia o Pet vinculando ao CPF do Tutor
                    Pet novoPet = new Pet();
                    novoPet.setNome(nomePet);
                    novoPet.setRaca(raca);
                    novoPet.setDataDeNascimento(dataNascPet);
                    novoPet.setCpfTutor(tutor.getCpf());
                    novoPet.setPeso(peso);

                    petDAO.salvar(novoPet);
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PETS CADASTRADOS ---");
                    List<Pet> pets = petDAO.listarTodos();

                    if (pets.isEmpty()) {
                        System.out.println("Nenhum pet cadastrado no momento.");
                    } else {
                        for (Pet p : pets) {
                            System.out.printf("ID: %d | Pet: %s (Raça: %s) | Nasc.: %s | Peso: %.2f kg | CPF Tutor: %s%n",
                                    p.getId(),
                                    p.getNome(),
                                    p.getRaca(),
                                    p.getDataDeNascimento(),
                                    p.getPeso(),
                                    p.getCpfTutor());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("\n[AVISO] Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}