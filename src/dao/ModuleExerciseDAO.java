package dao;

import connection.ConnectionFactory;
import model.ModuleExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModuleExerciseDAO {
    private static final String TABLE_MODULE_EXERCISE = "ModuleExercise";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IDEXERCISE = "idExercise";
    private static final String COLUMN_IDMODULE = "idModule";

    //Singleton
    private static ModuleExerciseDAO instance;

    public static ModuleExerciseDAO getInstance() {
        if (instance == null) {
            instance = new ModuleExerciseDAO();
        }
        return instance;
    }

    //Connection object
    private Connection connection = null;

    //CRUD
    //Create
    public boolean createModuleExercise(ModuleExercise moduleExercise, long idModule){
        boolean ret = false;

        String sql = "INSERT INTO " + TABLE_MODULE_EXERCISE + "(" + COLUMN_IDEXERCISE + "," + COLUMN_IDMODULE + ") VALUES (?, ?)";


        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setLong(1, moduleExercise.getExercise().getId());
            pstm.setLong(2, idModule);

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
    public ModuleExercise getModuleExercise(long idModuleExercise){
        ModuleExercise moduleExercise = null;

        String sql = "SELECT "+COLUMN_IDEXERCISE+","+COLUMN_IDMODULE+" FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_ID+"=?";
        connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idModuleExercise);
            ResultSet result = pstm.executeQuery();

            if (result.next()){
                moduleExercise = new ModuleExercise();
                moduleExercise.setExercise((new ExerciseDAO()).getExercise(result.getLong(COLUMN_IDEXERCISE)));
                moduleExercise.setModule((new ModulesDAO()).getModule(result.getLong(COLUMN_IDMODULE)));
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

        return moduleExercise;
    }
    public ModuleExercise getModuleExercise(long idExercise, long idModule){
        ModuleExercise moduleExercise = null;

        String sql = "SELECT "+COLUMN_IDEXERCISE+","+COLUMN_IDMODULE+" FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_IDEXERCISE+"=? AND "+COLUMN_IDMODULE+"=?";
        connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idExercise);
            pstm.setLong(2,idModule);
            ResultSet result = pstm.executeQuery();

            if (result.next()){
                moduleExercise = new ModuleExercise();
                moduleExercise.setExercise((new ExerciseDAO()).getExercise(result.getLong(COLUMN_IDEXERCISE)));
                moduleExercise.setModule((new ModulesDAO()).getModule(result.getLong(COLUMN_IDMODULE)));
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

        return moduleExercise;
    }
    public ArrayList<ModuleExercise> getModuleExercisesByModule(long idModule){
        ArrayList<ModuleExercise> moduleExercises = new ArrayList<ModuleExercise>();

        String sql = "SELECT "+COLUMN_IDEXERCISE+","+COLUMN_IDMODULE+" FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_IDMODULE+"=?";
        connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idModule);
            ResultSet result = pstm.executeQuery();

            while (result.next()){
                ModuleExercise moduleExercise = new ModuleExercise();
                moduleExercise.setExercise((new ExerciseDAO()).getExercise(result.getLong(COLUMN_IDEXERCISE)));
                moduleExercise.setModule((new ModulesDAO()).getModule(result.getLong(COLUMN_IDMODULE)));

                moduleExercises.add(moduleExercise);
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

        return moduleExercises;
    }
    public ArrayList<ModuleExercise> getModuleExercisesByExercise(long idExercise){
        ArrayList<ModuleExercise> moduleExercises = new ArrayList<ModuleExercise>();

        String sql = "SELECT "+COLUMN_IDEXERCISE+","+COLUMN_IDMODULE+" FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_IDEXERCISE+"=?";
        connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idExercise);
            ResultSet result = pstm.executeQuery();

            while (result.next()){
                ModuleExercise moduleExercise = new ModuleExercise();
                moduleExercise.setExercise((new ExerciseDAO()).getExercise(result.getLong(COLUMN_IDEXERCISE)));
                moduleExercise.setModule((new ModulesDAO()).getModule(result.getLong(COLUMN_IDMODULE)));

                moduleExercises.add(moduleExercise);
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

        return moduleExercises;
    }

    //Delete
    public boolean deleteModuleExerciseByID(long idModuleExercise){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_ID+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idModuleExercise);

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
    public boolean deleteModuleExerciseByModuleAndExercise(long idExercise, long idModule){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_IDEXERCISE+"=? AND "+COLUMN_IDMODULE+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idExercise);
            statement.setLong(2, idModule);

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
    public boolean deleteModulesExercisesByModule(long idModule){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_IDMODULE+"=?";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, idModule);

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
    public boolean deleteModulesExercisesByExercise(long idExercise){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_MODULE_EXERCISE+" WHERE "+COLUMN_IDEXERCISE+"=?";

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
