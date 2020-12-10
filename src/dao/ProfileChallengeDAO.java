package dao;

import connection.ConnectionFactory;
import model.Challenge;
import model.ProfileChallenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileChallengeDAO {
    private static final String TABLE_PROFILE_CHALLENGE = "ProfileChallenge";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATELAST = "dateLast";
    private static final String COLUMN_DATESTART = "dateStart";
    private static final String COLUMN_STATUS = "status";

    private static final String COLUMN_PROFILE_ID = "idProfile";
    private static final String COLUMN_CHALLENGE_ID = "idChallenge";

    //Singleton
    private static ExerciseDAO instance;
    public static ExerciseDAO getInstance() {
        if (instance == null) {
            instance = new ExerciseDAO();
        }
        return instance;
    }

    //Connection object
    private Connection connection = null;

    //CRUD
    //Create
    public boolean createProfileChallengeDAO(ProfileChallenge profileChallenge, long idUser){
        boolean ret = false;
        String sql = "INSERT INTO "+TABLE_PROFILE_CHALLENGE+"("+COLUMN_DATELAST+","+COLUMN_DATESTART+","+COLUMN_STATUS+","+COLUMN_PROFILE_ID+","+COLUMN_CHALLENGE_ID+") VALUES (?, ?, ?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, profileChallenge.getDateLast());
            pstm.setString(2, profileChallenge.getDateStart());
            pstm.setString(3, profileChallenge.getStatus());
            pstm.setLong(4, idUser);
            pstm.setLong(5, profileChallenge.getChallenge().getId());

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
    public ProfileChallenge getProfileChallenge(long idProfileChallenge){

        ProfileChallenge profileChallenge = null;

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_DATELAST+","+COLUMN_DATESTART+","+COLUMN_STATUS+","+COLUMN_CHALLENGE_ID+" FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idProfileChallenge);
            ResultSet result = pstm.executeQuery();

            profileChallenge = new ProfileChallenge();
            while (result.next()){
                profileChallenge.setId(result.getLong(COLUMN_ID));
                profileChallenge.setDateLast(result.getString(COLUMN_DATELAST));
                profileChallenge.setDateStart(result.getString(COLUMN_DATESTART));
                profileChallenge.setStatus(result.getString(COLUMN_STATUS));
                profileChallenge.setChallenge((new ChallengeDAO()).getChallenge(result.getLong(COLUMN_CHALLENGE_ID)));
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
        return profileChallenge;
    }
    public ProfileChallenge getProfileChallenge(long idProfile, long idChallenge){
        ProfileChallenge profileChallenge = null;

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_DATELAST+","+COLUMN_DATESTART+","+COLUMN_STATUS+","+COLUMN_CHALLENGE_ID+" FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_PROFILE_ID+"=? AND "+COLUMN_CHALLENGE_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idProfile);
            pstm.setLong(2,idChallenge);
            ResultSet result = pstm.executeQuery();

            profileChallenge = new ProfileChallenge();
            while (result.next()){
                profileChallenge.setId(result.getLong(COLUMN_ID));
                profileChallenge.setDateLast(result.getString(COLUMN_DATELAST));
                profileChallenge.setDateStart(result.getString(COLUMN_DATESTART));
                profileChallenge.setStatus(result.getString(COLUMN_STATUS));
                profileChallenge.setChallenge((new ChallengeDAO()).getChallenge(result.getLong(COLUMN_CHALLENGE_ID)));
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
        return profileChallenge;
    }
    public ArrayList<ProfileChallenge> getProfileChallengesFromUser(long idUser){

        ArrayList<ProfileChallenge> profileChallenges = new ArrayList<ProfileChallenge>();

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_DATELAST+","+COLUMN_DATESTART+","+COLUMN_STATUS+","+COLUMN_CHALLENGE_ID+" FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_PROFILE_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idUser);
            ResultSet result = pstm.executeQuery();

            while (result.next()){
                ProfileChallenge profileChallenge = new ProfileChallenge();
                profileChallenge.setId(result.getLong(COLUMN_ID));
                profileChallenge.setDateLast(result.getString(COLUMN_DATELAST));
                profileChallenge.setDateStart(result.getString(COLUMN_DATESTART));
                profileChallenge.setStatus(result.getString(COLUMN_STATUS));
                profileChallenge.setChallenge((new ChallengeDAO()).getChallenge(result.getLong(COLUMN_CHALLENGE_ID)));

                profileChallenges.add(profileChallenge);
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
        return profileChallenges;
    }
    public ArrayList<ProfileChallenge> getProfileChallengesByChallenge(long idChallenge){
        ArrayList<ProfileChallenge> profileChallenges = new ArrayList<ProfileChallenge>();

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_DATELAST+","+COLUMN_DATESTART+","+COLUMN_STATUS+","+COLUMN_CHALLENGE_ID+" FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_CHALLENGE_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idChallenge);
            ResultSet result = pstm.executeQuery();

            while (result.next()){
                ProfileChallenge profileChallenge = new ProfileChallenge();
                profileChallenge.setId(result.getLong(COLUMN_ID));
                profileChallenge.setDateLast(result.getString(COLUMN_DATELAST));
                profileChallenge.setDateStart(result.getString(COLUMN_DATESTART));
                profileChallenge.setStatus(result.getString(COLUMN_STATUS));
                profileChallenge.setChallenge((new ChallengeDAO()).getChallenge(result.getLong(COLUMN_CHALLENGE_ID)));

                profileChallenges.add(profileChallenge);
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
        return profileChallenges;
    }

    //Update
    public boolean updateProfileChallenge(ProfileChallenge profileChallenge){
        boolean ret = false;
        String sql = "UPDATE "+TABLE_PROFILE_CHALLENGE+" SET "+COLUMN_DATELAST+"=?, "+COLUMN_DATESTART+"=?, "+COLUMN_STATUS+"=? WHERE "+COLUMN_ID+"=?";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, profileChallenge.getDateLast());
            pstm.setString(2, profileChallenge.getDateStart());
            pstm.setString(3, profileChallenge.getStatus());

            pstm.setLong(4, profileChallenge.getId());

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
    public boolean updateProfileChallengeByProfileAndChallenge(ProfileChallenge profileChallenge,long idUser){
        boolean ret = false;
        String sql = "UPDATE "+TABLE_PROFILE_CHALLENGE+" SET "+COLUMN_DATELAST+"=?, "+COLUMN_DATESTART+"=?, "+COLUMN_STATUS+"=? WHERE "+COLUMN_CHALLENGE_ID+"=? AND "+COLUMN_PROFILE_ID+"=?";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, profileChallenge.getDateLast());
            pstm.setString(2, profileChallenge.getDateStart());
            pstm.setString(3, profileChallenge.getStatus());

            pstm.setLong(4, profileChallenge.getChallenge().getId());
            pstm.setLong(5, idUser);

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
    public boolean deleteProfileChallenge(long idProfileChallenge){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_ID+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idProfileChallenge);

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
    public boolean deleteProfileChallengeByChallengeAndProfile(long idProfile, long idChallenge){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_CHALLENGE_ID+"=? AND "+COLUMN_PROFILE_ID+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idChallenge);
            statement.setLong(2, idProfile);

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
    public boolean deleteProfilesChallengesByProfile(long idProfile){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_PROFILE_ID+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idProfile);

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
    public boolean deleteProfilesChallengesByChallenge(long idChallenge){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_PROFILE_CHALLENGE+" WHERE "+COLUMN_CHALLENGE_ID+"=?";

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
