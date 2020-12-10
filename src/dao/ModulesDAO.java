package dao;

import com.sun.org.apache.xpath.internal.operations.Mod;
import connection.ConnectionFactory;
import model.Exercise;
import model.ModuleExercise;
import model.Modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModulesDAO {
    private static final String TABLE_MODULES = "Module";

    private static final String COLUMN_MODULE_ID = "id";
    private static final String COLUMN_MODULE_IDTRAINING = "idTraining";
    private static final String COLUMN_MODULE_DESCRIPTION = "description";
    private static final String COLUMN_MODULE_TITLE = "title";

    //Singleton
    private static ModulesDAO instance;

    public static ModulesDAO getInstance() {
        if (instance == null) {
            instance = new ModulesDAO();
        }
        return instance;
    }

    //Connection object
    private Connection connection = null;

    //CRUD
    //Create
    public boolean createModule(Modules module, long idTraining) {
        boolean ret = false;

        String sql = "INSERT INTO " + TABLE_MODULES + "(" + COLUMN_MODULE_DESCRIPTION + "," + COLUMN_MODULE_TITLE + ","+COLUMN_MODULE_IDTRAINING+") VALUES (?, ?, ?)";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, module.getDescription());
            pstm.setString(2, module.getTitle());
            pstm.setLong(3, idTraining);

            long id = pstm.executeUpdate();

            connection.commit();

            ret = true;

            for(ModuleExercise moduleExercise:module.getExercises()){
                boolean ok = (new ModuleExerciseDAO()).createModuleExercise(moduleExercise,id);
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
    public Modules getModule(long idModule){
        Modules module = null;

        String sql = "SELECT "+COLUMN_MODULE_ID+","+COLUMN_MODULE_DESCRIPTION+","+COLUMN_MODULE_TITLE+" FROM "+TABLE_MODULES+" WHERE "+COLUMN_MODULE_ID+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idModule);
            ResultSet result = pstm.executeQuery();

            module = new Modules();
            while (result.next()){
                module.setId(result.getLong(COLUMN_MODULE_ID));
                module.setDescription(result.getString(COLUMN_MODULE_DESCRIPTION));
                module.setTitle(result.getString(COLUMN_MODULE_TITLE));
                module.setExercises((new ModuleExerciseDAO()).getModuleExercisesByModule(module.getId()));
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
        return module;
    }
    public ArrayList<Modules> getModulesFromTraining(long idTraining){
        ArrayList<Modules> modules = new ArrayList<Modules>();

        String sql = "SELECT "+COLUMN_MODULE_ID+","+COLUMN_MODULE_DESCRIPTION+","+COLUMN_MODULE_TITLE+" FROM "+TABLE_MODULES+" WHERE "+COLUMN_MODULE_IDTRAINING+"=?";
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,idTraining);
            ResultSet result = pstm.executeQuery();


            while (result.next()){
                Modules module = new Modules();
                module.setId(result.getLong(COLUMN_MODULE_ID));
                module.setDescription(result.getString(COLUMN_MODULE_DESCRIPTION));
                module.setTitle(result.getString(COLUMN_MODULE_TITLE));
                module.setExercises((new ModuleExerciseDAO()).getModuleExercisesByModule(module.getId()));

                modules.add(module);
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
        return modules;
    }

    //Update
    public boolean updateModule(Modules module){
        boolean ret = false;
        String sql = "UPDATE "+TABLE_MODULES+" SET "+COLUMN_MODULE_DESCRIPTION+"=?, "+COLUMN_MODULE_TITLE+"=? WHERE "+COLUMN_MODULE_ID+"=?";

        try {

            connection = ConnectionFactory.getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, module.getDescription());
            pstm.setString(2, module.getTitle());
            pstm.setLong(3,module.getId());
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
    public boolean deleteModule(long idModule){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_MODULES+" WHERE "+COLUMN_MODULE_ID+"=?";

        if((new ModuleExerciseDAO()).deleteModulesExercisesByModule(idModule)){
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
        }

        return ret;
    }
    public boolean deleteModulesByTraining(long idTraining){
        boolean ret = false;
        String sql = "DELETE FROM "+TABLE_MODULES+" WHERE "+COLUMN_MODULE_ID+"=?";

        ArrayList<Modules> modules = getModulesFromTraining(idTraining);


        for(Modules module: modules){
            if((new ModuleExerciseDAO()).deleteModulesExercisesByModule(module.getId())){
                try {
                    connection = ConnectionFactory.getConnection();

                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setLong(1, module.getId());

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
            }
        }

        return ret;
    }
}
