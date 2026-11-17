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

                    System.out.print("Nome do Tutor: ");
                    String nomeTutor = scanner.nextLine();
                    System.out.print("Telefone do Tutor: ");
                    String telefoneTutor = scanner.nextLine();
                    System.out.print("CPF do Tutor: ");
                    String cpfTutor = scanner.nextLine();

                    Tutor tutor = new Tutor(nomeTutor, telefoneTutor, cpfTutor);

                    System.out.print("Nome do Pet: ");
                    String nomePet = scanner.nextLine();
                    System.out.print("Espécie: ");
                    String especie = scanner.nextLine();
                    System.out.print("Raça: ");
                    String raca = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();

                    Pet novoPet = new Pet(nomePet, especie, raca, idade, tutor.getNome());
                    petDAO.cadastrar(novoPet);
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PETS CADASTRADOS ---");
                    List<Pet> pets = petDAO.listarTodos();

                    if (pets.isEmpty()) {
                        System.out.println("Nenhum pet cadastrado no momento.");
                    } else {
                        for (Pet p : pets) {
                            System.out.printf("ID: %d | Pet: %s (%s - %s) | Idade: %d anos | Tutor: %s%n",
                                    p.getId(),
                                    p.getNome(),
                                    p.getEspecie(),
                                    p.getRaca(),
                                    p.getIdade(),
                                    p.getNomeTutor());
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
