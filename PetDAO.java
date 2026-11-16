import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // Método salvar (já previsto na estrutura da clínica)
    public void salvar(Pet pet) {
        String sql = "INSERT INTO pets (nome, especie, raca, idade, nome_tutor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getEspecie());
            stmt.setString(3, pet.getRaca());
            stmt.setInt(4, pet.getIdade());
            stmt.setString(5, pet.getNomeTutor());

            stmt.executeUpdate();
            System.out.println("\n[SUCESSO] Pet cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("\n[ERRO] Falha ao cadastrar pet: " + e.getMessage());
        }
    }

    // Issue #5: Implementar o método listarTodos() no PetDAO (SELECT no banco de dados)
    public List<Pet> listarTodos() {
        List<Pet> listaPets = new ArrayList<>();
        String sql = "SELECT * FROM pets";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));
                pet.setEspecie(rs.getString("especie"));
                pet.setRaca(rs.getString("raca"));
                pet.setIdade(rs.getInt("idade"));
                pet.setNomeTutor(rs.getString("nome_tutor"));

                listaPets.add(pet);
            }

        } catch (SQLException e) {
            System.err.println("\n[ERRO] Falha ao listar pets: " + e.getMessage());
        }

        return listaPets;
    }
}
