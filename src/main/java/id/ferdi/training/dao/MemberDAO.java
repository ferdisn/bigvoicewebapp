package id.ferdi.training.dao;

import id.ferdi.training.model.Member;
import id.ferdi.training.util.DbUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MemberDAO {
    private Connection connection;

    public MemberDAO() {
        connection = DbUtil.getConnection();
    }

    public void create(Member member) {
        try {
            PreparedStatement preparedStatement;

            if ( member.getFile().equals(null) ) {
                preparedStatement = connection.prepareStatement("INSERT INTO members(name) values (?)");

                preparedStatement.setString(1,member.getName());

                preparedStatement.executeUpdate();
            }
            else {

                preparedStatement = connection.prepareStatement("INSERT INTO members(name,data,filename) values (?,?,?)");

                preparedStatement.setString(1,member.getName());
                preparedStatement.setBinaryStream(2,member.getFile());
                preparedStatement.setString(3,member.getFilename());

                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM members WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Member member) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE members set name = ? WHERE id = ?");
            preparedStatement.setString(1,member.getName());

            preparedStatement.setInt(2,member.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member read(int id) {
        Member aMember = new Member();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM members WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                aMember.setId(resultSet.getInt("id"));
                aMember.setName(resultSet.getString("name"));
                aMember.setFile(resultSet.getBinaryStream("data"));
                aMember.setFilename(resultSet.getString("filename"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aMember;
    }

    public List<Member> getAllMembers() {
        List<Member> members =  new ArrayList<Member>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM members ORDER BY id ASC");
            while (resultSet.next()) {
                Member aMember = new Member();
                aMember.setId(resultSet.getInt("id"));
                aMember.setName(resultSet.getString("name"));
                aMember.setFilename(resultSet.getString("fileName"));
                members.add(aMember);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return members;
    }
}
