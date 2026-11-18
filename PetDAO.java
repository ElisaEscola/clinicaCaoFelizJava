import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // Salva um novo pet usando os atributos exatos do diagrama
    public void salvar(Pet pet) {
        String sql = "INSERT INTO pets (nome, raca, data_de_nascimento, cpf_tutor, peso) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getRaca());
            stmt.setString(3, pet.getDataDeNascimento());
            stmt.setString(4, pet.getCpfTutor());
            stmt.setDouble(5, pet.getPeso());

            stmt.executeUpdate();
            System.out.println("\n[SUCESSO] Pet cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("\n[ERRO] Falha ao cadastrar pet: " + e.getMessage());
        }
    }

    // Lista todos os pets mapeando as colunas correspondentes ao diagrama
    public List<Pet> listarTodos() {
        List<Pet> listaPets = new ArrayList<>();
        String sql = "SELECT * FROM pets";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pet pet = new Pet();
                // Mantido caso sua tabela no banco possua coluna 'id'
                pet.setId(rs.getInt("id")); 
                pet.setNome(rs.getString("nome"));
                pet.setRaca(rs.getString("raca"));
                pet.setDataDeNascimento(rs.getString("data_de_nascimento"));
                pet.setCpfTutor(rs.getString("cpf_tutor"));
                pet.setPeso(rs.getDouble("peso"));

                listaPets.add(pet);
            }

        } catch (SQLException e) {
            System.err.println("\n[ERRO] Falha ao listar pets: " + e.getMessage());
        }

        return listaPets;
    }

    // Método previsto no diagrama: + atualizarCadastro(pet): String
    public String atualizarCadastro(Pet pet) {
        String sql = "UPDATE pets SET nome = ?, raca = ?, data_de_nascimento = ?, peso = ? WHERE cpf_tutor = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getRaca());
            stmt.setString(3, pet.getDataDeNascimento());
            stmt.setDouble(4, pet.getPeso());
            stmt.setString(5, pet.getCpfTutor());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return "[SUCESSO] Cadastro do pet atualizado!";
            } else {
                return "[AVISO] Nenhum pet encontrado para o CPF informado.";
            }

        } catch (SQLException e) {
            return "[ERRO] Falha ao atualizar cadastro: " + e.getMessage();
        }
    }
}
