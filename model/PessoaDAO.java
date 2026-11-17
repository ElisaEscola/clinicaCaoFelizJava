import java.sql.*;

public class PessoaDAO {

    public atualizar(){
        try{

            PreparedStatement statement;
            statement.executeUpdate("UPDATE INTO pessoa VALUES(?,?,?)");
        } catch(SQLException e){
            e.getMessage();
        }

    }
    
}
