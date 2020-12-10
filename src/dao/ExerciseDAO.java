package dao;

import connection.ConnectionFactory;
import model.Exercise;

import java.sql.*;
import java.util.ArrayList;

public class ExerciseDAO {
    private static final String TABLE_EXERCISE = "Exercise";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_REPETITIONS = "repetitions";
    private static final String COLUMN_CALORIESPERSECOND = "caloriesPerSecond";
    private static final String COLUMN_LINK = "link";

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
    public boolean createExercise(Exercise exercise){
        boolean ret = false;
        String sql = "INSERT INTO "+TABLE_EXERCISE+"("+COLUMN_TITLE+","+COLUMN_REPETITIONS+","+COLUMN_CALORIESPERSECOND+","+COLUMN_LINK+") VALUES (?, ?, ?,?)";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, exercise.getTitle());
            pstm.setInt(2, exercise.getRepetition());
            pstm.setDouble(3,exercise.getCaloriesPerSecond());
            pstm.setString(4,exercise.getLink());

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
    public Exercise getExercise(long idExercise){
        Exercise exercise = null;

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_TITLE+","+COLUMN_REPETITIONS+","+COLUMN_CALORIESPERSECOND+","+COLUMN_LINK+" FROM "+TABLE_EXERCISE+" WHERE "+COLUMN_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idExercise);
            ResultSet result = pstm.executeQuery();

            exercise = new Exercise();
            while (result.next()){
                exercise.setId(result.getLong(COLUMN_ID));
                exercise.setTitle(result.getString(COLUMN_TITLE));
                exercise.setRepetition(result.getInt(COLUMN_REPETITIONS));
                exercise.setCaloriesPerSecond(result.getDouble(COLUMN_CALORIESPERSECOND));
                exercise.setLink(result.getString(COLUMN_LINK));

                return exercise;
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
        return exercise;
    }
    public ArrayList<Exercise> getExercises(){
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();

        String sql = "SELECT "+COLUMN_ID+","+COLUMN_TITLE+","+COLUMN_REPETITIONS+","+COLUMN_CALORIESPERSECOND+","+COLUMN_LINK+" FROM "+TABLE_EXERCISE;
        connection = ConnectionFactory.getConnection();
        try {
            Statement pstm = connection.createStatement();
            ResultSet result = pstm.executeQuery(sql);

            while (result.next()){
                Exercise exercise = new Exercise();
                exercise.setId(result.getLong(COLUMN_ID));
                exercise.setTitle(result.getString(COLUMN_TITLE));
                exercise.setRepetition(result.getInt(COLUMN_REPETITIONS));
                exercise.setCaloriesPerSecond(result.getDouble(COLUMN_CALORIESPERSECOND));
                exercise.setLink(result.getString(COLUMN_LINK));

                exercises.add(exercise);
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
        return exercises;
    }

    //Update
    public boolean updateExercise(Exercise exercise){
        boolean ret = false;
        String sql = "UPDATE "+TABLE_EXERCISE+" SET "+COLUMN_TITLE+"=?, "+COLUMN_REPETITIONS+"=?, "+COLUMN_CALORIESPERSECOND+"=?, "+COLUMN_LINK+"=? WHERE "+COLUMN_ID+"=?";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, exercise.getTitle());
            pstm.setInt(2, exercise.getRepetition());
            pstm.setDouble(3,exercise.getCaloriesPerSecond());
            pstm.setString(4,exercise.getLink());

            pstm.setLong(5,exercise.getId());

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
    public boolean deleteExercise(long idExercise){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_EXERCISE+" WHERE "+COLUMN_ID+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idExercise);

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
