package dao;

import connection.ConnectionFactory;
import model.Challenge;

import java.sql.*;
import java.util.ArrayList;

public class ChallengeDAO {
    private static final String TABLE_CHALLENGE = "Challenge";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_TITLE = "title";

    //Singleton
    private static ChallengeDAO instance;
    public static ChallengeDAO getInstance() {
        if (instance == null) {
            instance = new ChallengeDAO();
        }
        return instance;
    }

    //Connection object
    private Connection connection = null;

    //CRUD
    //Create
    public boolean createChallenge(Challenge challenge){
        boolean ret = false;

        String sql = "INSERT INTO "+TABLE_CHALLENGE+"("+COLUMN_TITLE+","+COLUMN_DESCRIPTION+","+COLUMN_LINK+") VALUES (?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, challenge.getTitle());
            pstm.setString(2, challenge.getDescription());
            pstm.setString(3, challenge.getLink());

            pstm.executeUpdate();
            connection.commit();

            ret = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }

    //Read
    public Challenge getChallenge(long idChallenge){
        Challenge challenge = null;

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_TITLE+","+COLUMN_DESCRIPTION+","+COLUMN_LINK+" FROM "+TABLE_CHALLENGE+" WHERE "+COLUMN_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idChallenge);
            ResultSet result = pstm.executeQuery();

            challenge = new Challenge();
            while (result.next()){
                challenge.setId(result.getLong(COLUMN_ID));
                challenge.setTitle(result.getString(COLUMN_TITLE));
                challenge.setLink(result.getString(COLUMN_LINK));
                challenge.setDescription(result.getString(COLUMN_DESCRIPTION));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return challenge;
    }
    public ArrayList<Challenge> getChallenges(){
        ArrayList<Challenge> challenges = new ArrayList<Challenge>();

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_TITLE+","+COLUMN_DESCRIPTION+","+COLUMN_LINK+" FROM "+TABLE_CHALLENGE;
        connection = ConnectionFactory.getConnection();
        try {
            Statement pstm = connection.createStatement();
            ResultSet result = pstm.executeQuery(sql);


            while (result.next()){
                Challenge challenge = new Challenge();
                challenge.setId(result.getLong(COLUMN_ID));
                challenge.setTitle(result.getString(COLUMN_TITLE));
                challenge.setLink(result.getString(COLUMN_LINK));
                challenge.setDescription(result.getString(COLUMN_DESCRIPTION));

                challenges.add(challenge);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return challenges;
    }

    //Update
    public boolean updateChallenge(Challenge challenge){
        boolean ret = false;
        String sql = "UPDATE "+TABLE_CHALLENGE+" SET "+COLUMN_TITLE+"=?, "+COLUMN_DESCRIPTION+"=?, "+COLUMN_LINK+"=? WHERE "+COLUMN_ID+"=?";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, challenge.getTitle());
            pstm.setString(2, challenge.getDescription());
            pstm.setString(3, challenge.getLink());
            
            pstm.setLong(4, challenge.getId());

            int execute = pstm.executeUpdate();

            connection.commit();
            if(execute>0){
                ret = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }

    //Delete
    public boolean deleteChallenge(long idChallenge){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_CHALLENGE+" WHERE "+COLUMN_ID+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idChallenge);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }
}
