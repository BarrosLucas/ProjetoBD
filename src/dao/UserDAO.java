package dao;

import connection.ConnectionFactory;
import model.Training;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private static final String TABLE_USERS = "Profile";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CALORIES = "calories";
    private static final String COLUMN_DAYSOFTRAINING = "daysOfTraining";
    private static final String COLUMN_FULLCHALLENGES = "fullChallenges";

    //Singleton
    private static UserDAO instance;
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    //Connection object
    private Connection connection = null;


    //CRUD
    //Create
    public boolean createUser(User user) {
        boolean ret = false;
        TrainingDAO trainingDAO;

        String sql = "INSERT INTO "+TABLE_USERS+"("+COLUMN_NAME+","+COLUMN_CALORIES+","+COLUMN_DAYSOFTRAINING+","+COLUMN_FULLCHALLENGES+") VALUES (?, ?, ?, ?)";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1,user.getName());
            pstm.setDouble(2,user.getCalories());
            pstm.setLong(3,user.getDaysOfTraining());
            pstm.setLong(4,user.getFullChallenges());

            pstm.executeUpdate();

            connection.commit();

            ret = true;

            trainingDAO = new TrainingDAO();
            long idUser = getUserByName(user.getName()).getId();
            for(Training training: user.getTrainings()){
                trainingDAO.createTraining(training,idUser);
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

    //Read
    public User getUserById(long idUser){
        User user = null;
        TrainingDAO trainingDAO;

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_NAME+","+COLUMN_CALORIES+","+COLUMN_DAYSOFTRAINING+","+COLUMN_FULLCHALLENGES+" FROM "+TABLE_USERS+" WHERE "+COLUMN_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idUser);
            ResultSet result = pstm.executeQuery();

            user = new User();
            trainingDAO = new TrainingDAO();
            while (result.next()){
                user.setId(result.getLong(COLUMN_ID));
                user.setName(result.getString(COLUMN_NAME));
                user.setCalories(result.getDouble(COLUMN_CALORIES));
                user.setDaysOfTraining(result.getInt(COLUMN_DAYSOFTRAINING));
                user.setFullChallenges(result.getLong(COLUMN_FULLCHALLENGES));

                user.setTrainings(trainingDAO.getTrainings(user.getId()));
                return user;
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
        return user;
    }
    public User getUserByName(String name){
        User user = null;

        TrainingDAO trainingDAO;

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_NAME+","+COLUMN_CALORIES+","+COLUMN_DAYSOFTRAINING+","+COLUMN_FULLCHALLENGES+" FROM "+TABLE_USERS+" WHERE "+COLUMN_NAME+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,name);
            ResultSet result = pstm.executeQuery();

            trainingDAO = new TrainingDAO();

            user = new User();
            if (result.next()){
                user.setId(result.getLong(COLUMN_ID));
                user.setName(result.getString(COLUMN_NAME));
                user.setCalories(result.getDouble(COLUMN_CALORIES));
                user.setDaysOfTraining(result.getInt(COLUMN_DAYSOFTRAINING));
                user.setFullChallenges(result.getLong(COLUMN_FULLCHALLENGES));

                user.setTrainings(trainingDAO.getTrainings(user.getId()));
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
        return user;
    }
    
    public ArrayList<User> getUsers(){
        TrainingDAO trainingDAO;
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT "+COLUMN_ID+","+COLUMN_NAME+","+COLUMN_CALORIES+","+COLUMN_DAYSOFTRAINING+","+COLUMN_FULLCHALLENGES+" FROM Users";
        connection = ConnectionFactory.getConnection();
        try {
            Statement pstm = connection.createStatement();
            ResultSet result = pstm.executeQuery(sql);

            trainingDAO = new TrainingDAO();
            User user = new User();
            while (result.next()){
                user.setId(result.getLong(COLUMN_ID));
                user.setName(result.getString(COLUMN_NAME));
                user.setCalories(result.getDouble(COLUMN_CALORIES));
                user.setDaysOfTraining(result.getInt(COLUMN_DAYSOFTRAINING));
                user.setFullChallenges(result.getLong(COLUMN_FULLCHALLENGES));

                user.setTrainings(trainingDAO.getTrainings(user.getId()));
                users.add(user);
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
        return users;
    }

    //Update
    public boolean updateUserById(User user){
        boolean ret = false;
        String sql = "UPDATE "+TABLE_USERS+" SET "+COLUMN_NAME+"=?, "+COLUMN_CALORIES+"=?, "+COLUMN_DAYSOFTRAINING+"=?,"+COLUMN_FULLCHALLENGES+"=? WHERE "+COLUMN_ID+"=?";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, user.getName());
            pstm.setDouble(2,user.getCalories());
            pstm.setLong(3, user.getDaysOfTraining());
            pstm.setLong(4,user.getFullChallenges());
            pstm.setLong(5,user.getId());

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
    
    public boolean deleteUserById(long idUser){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_USERS+" WHERE "+COLUMN_ID+"=?";

        ret = (new TrainingDAO().deleteTrainngs(idUser));
        if(ret) {
            try {
                connection = ConnectionFactory.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, idUser);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
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
        }
        return ret;
    }
    
}
