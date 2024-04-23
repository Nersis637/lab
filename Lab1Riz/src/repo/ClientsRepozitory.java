package repo;

import entity.Clients;
import entity.Masters;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepozitory implements IRepo<Clients> {
    public Connection connect() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:h2:C:/Users/ПК/test.mv.db");
        if (conn==null){System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        return conn;
    }
    public Statement createStmt(Connection conn)throws SQLException{
        return conn.createStatement();
    }
    @Override
    public void insert(Clients client) throws SQLException {
        String query = "INSERT INTO Clients (surname, name, ID_MASTERS) VALUES (?,?,?)";
        try (PreparedStatement statement = connect().prepareStatement(query)) {
            statement.setString(1, client.getName());

            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getIdMasters());
            statement.executeUpdate();
        }
    }


    @Override
    public void delete(Clients client) throws SQLException {
        String query = "UPDATE Clients SET EXIST = true WHERE ID_CLIENT = ?";
        try (PreparedStatement statement = connect().prepareStatement(query)) {
            statement.setInt(1, client.getId());
            if(statement.executeUpdate()>0){
                System.out.println("Запись успешно обновлена!");
            } else {
                System.out.println("Запись не была найдена или не была обновлена.");
            }
        }
    }

    @Override
    public void update(Clients client) throws SQLException {
        String query = "UPDATE Clients SET name = ?, surname = ? WHERE ID_CLIENT= ?";
        try (PreparedStatement statement = connect().prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getId());
            if(statement.executeUpdate()>0){
                System.out.println("Запись успешно обновлена!");
            } else {
                System.out.println("Запись не была найдена или не была обновлена.");
            }
        }


    }

    @Override
    public List<Clients> getAll() throws SQLException {
        Statement stmt = createStmt(connect());
        ResultSet rs = stmt.executeQuery("SELECT * FROM Clients");
        List<Clients> clients = new ArrayList<>();
        while(rs.next()){
            Clients client = new Clients(rs.getInt("id_client"), rs.getString("name"), rs.getString("surname"),rs.getInt("id_masters"));
            clients.add(client);
        }
        this.closeConnection(stmt);
        return clients;
    }
    public void closeConnection(Statement stmt) throws SQLException {
        stmt.close();
    }

}
