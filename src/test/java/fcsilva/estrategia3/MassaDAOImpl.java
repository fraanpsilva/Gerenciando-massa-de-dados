package fcsilva.estrategia3;

import br.ce.wcaquino.dao.utils.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MassaDAOImpl {

    public void inserirMassa(String tipo, String valor) throws SQLException, ClassNotFoundException {
        PreparedStatement psm = ConnectionFactory.getConnection().prepareStatement(
                "INSERT INTO massas (tipo, valor) VALUES ( ?, ? )"
        );

        psm.setString(1, tipo);
        psm.setString(2, valor);
        psm.executeUpdate();
        psm.close();
    }

    public String obterMassa(String tipo) throws SQLException, ClassNotFoundException {

        Long id;
        String valor;

        PreparedStatement psm = ConnectionFactory.getConnection().prepareStatement(
                "SELECT id, valor FROM massas WHERE tipo = ? AND usada = false ORDER BY id LIMIT 1");

        psm.setString(1, tipo);
        ResultSet rs = psm.executeQuery();
        if(!rs.next()) return null;
        id = rs.getLong("id");
        valor = rs.getString("valor");
        psm.close();

        psm = ConnectionFactory.getConnection().prepareStatement(
                "UPDATE massas SET usada = true Where id = ?");

        psm.setLong(1, id);
        psm.executeUpdate();
        psm.close();

        return valor;


    }

}
