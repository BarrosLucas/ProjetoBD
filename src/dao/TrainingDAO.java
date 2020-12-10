package dao;

import connection.ConnectionFactory;
import model.Exercise;
import model.Modules;
import model.Training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainingDAO {
    private static final String TABLE_TRAINING = "Training";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_IDPROFILE = "idProfile";

    //Singleton
    private static TrainingDAO instance;

    public static TrainingDAO getInstance() {
        if (instance == null) {
            instance = new TrainingDAO();
        }
        return instance;
    }

    //Connection object
    private Connection connection = null;

    //CRUD
    //Create
    public boolean createTraining(Training training,long idUser) {
        boolean ret = false;

        String sql = "INSERT INTO " + TABLE_TRAINING + "(" + COLUMN_DESCRIPTION + "," + COLUMN_TITLE + ","+COLUMN_IDPROFILE+") VALUES (?, ?, ?)";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, training.getDescription());
            pstm.setString(2, training.getTitle());
            pstm.setLong(3, idUser);

            long id = pstm.executeUpdate();

            connection.commit();

            ret = true;

            for(Modules module : training.getModules()){
                boolean ok = (new ModulesDAO()).createModule(module,id);
                if(!ok){
                    ret = false;
                }
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
    public Training getTraining(long idTraining){
        Training training = null;

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_TITLE+","+COLUMN_DESCRIPTION+" FROM "+TABLE_TRAINING+" WHERE "+COLUMN_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idTraining);
            ResultSet result = pstm.executeQuery();

            training = new Training();
            while (result.next()){
                training.setId(result.getLong(COLUMN_ID));
                training.setTitle(result.getString(COLUMN_TITLE));
                training.setDescription(result.getString(COLUMN_DESCRIPTION));

                return training;
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
        return training;
    }
    public ArrayList<Training> getTrainings(long idUser){
        ArrayList<Training> trainings = new ArrayList<Training>();

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_TITLE+","+COLUMN_DESCRIPTION+" FROM "+TABLE_TRAINING+" WHERE "+COLUMN_IDPROFILE+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idUser);
            ResultSet result = pstm.executeQuery();

            while (result.next()){

                Training training = new Training();
                training.setId(result.getLong(COLUMN_ID));
                training.setTitle(result.getString(COLUMN_TITLE));
                training.setDescription(result.getString(COLUMN_DESCRIPTION));

                trainings.add(training);
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
        return trainings;
    }

    //Update
    public boolean updateTraining(Training training){
        boolean ret = false;
        String sql = "UPDATE "+TABLE_TRAINING+" SET "+COLUMN_TITLE+"=?, "+COLUMN_DESCRIPTION+"=? WHERE "+COLUMN_ID+"=?";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1,training.getTitle());
            pstm.setString(2,training.getDescription());
            pstm.setDouble(3,training.getId());

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
    public boolean deleteTrainng(long idTraining){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_TRAINING+" WHERE "+COLUMN_ID+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idTraining);

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
    public boolean deleteTrainngs(long idUser){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_TRAINING+" WHERE "+COLUMN_IDPROFILE+"=?";

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
