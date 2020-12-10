package facade;

import controller.ChallengeController;
import controller.ExerciseController;
import controller.TrainingController;
import controller.UserController;
import model.*;
import utils.Constants;

import java.util.ArrayList;


public class Facade extends Constants {


    //Cadastro
    public int createUser(User user){

        return (new UserController()).createUser(user);
/*
        if (result != OK) {
            if(result==ERROR_INTERNAL){
                response = Response.status(Returns.INTERNAL_SERVER_ERROR.getResponse()).entity(MESSAGE_INTERNAL_SERVER_ERROR).build();
            }else if(result == ERROR_EMAIL_CONFLICT || result == ERROR_USERNAME_CONFLICT) {
                response = Response.status(Returns.CONFLICT.getResponse()).entity(MESSAGE_CONFLICT).build();
            }else{
                response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_BAD_REQUEST).build();
            }

        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    
    
    public ArrayList<Training> getWorkouts(int idUser){

        return (new TrainingController()).getTrainings(idUser);

        /*Response response = null;

        if (result == null) {
            response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_BAD_REQUEST).build();
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    //Mostrar desafios
    public ArrayList<Challenge> getChallenges(){

        return (new ChallengeController()).getChallenges();
/*
        Response response = null;

        if (result == null) {
            response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_BAD_REQUEST).build();
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    //Mostrar exercícios
    public ArrayList<Exercise> getExercises(){

        return (new ExerciseController()).getExercises();

        /*Response response = null;

        if (result == null) {
            response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_BAD_REQUEST).build();
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    //Mostrar perfil
    public User getProfile(int idUser){

        return (new UserController()).getUser(idUser);

        /*Response response = null;

        if (result == null) {
            response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_BAD_REQUEST).build();
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    //Cadastrar treino
    public int newWorkout(int idUser, Training training){
    
        return (new TrainingController().newWorkout(idUser,training));

        /*Response response = null;

        if (result != OK) {
            if(result == ERROR_INTERNAL){
                response = Response.status(Returns.INTERNAL_SERVER_ERROR.getResponse()).entity(MESSAGE_INTERNAL_SERVER_ERROR).build();
            }else{
                response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_BAD_REQUEST).build();
            }
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    //Concluiu treino(add mais um dia de treino e quantidade de queima calórica)
    public int runWorkout(int idUser, long calories){

        return (new TrainingController()).runTraining(idUser,calories);

        /*Response response = null;

        if (result != OK) {
            if(result == ERROR_NOT_EXISTS){
                response = Response.status(Returns.UNAUTHORIZED.getResponse()).entity(MESSAGE_USER_UNAUTHORIZED).build();
            }else if(result == ERROR_INTERNAL){
                response = Response.status(Returns.INTERNAL_SERVER_ERROR.getResponse()).entity(MESSAGE_INTERNAL_SERVER_ERROR).build();
            }
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    //Participar de desafio
    public int intoChallenge(int idUser, int idChallenge){
    
        return (new ChallengeController()).intoInChallenge(idUser,idChallenge);

        /*Response response = null;

        if (result != OK) {
            if(result == ERROR_INTERNAL){
                response = Response.status(Returns.INTERNAL_SERVER_ERROR.getResponse()).entity(MESSAGE_INTERNAL_SERVER_ERROR).build();
            }else if(result == ERROR_CONFLICT){
                response = Response.status(Returns.CONFLICT.getResponse()).entity(MESSAGE_CONFLICT_CHALLENGE).build();
            }else{
                response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_CHALLENGE_NOT_EXISTS).build();
            }
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }

        return response;*/
    }

    //Sair de desafio
    public int outChallenge(int idUser, int idChallenge){
        
        return (new ChallengeController()).outChallenge(idUser,idChallenge);

        /*Response response = null;

        if (result != OK) {
            if(result == ERROR_INTERNAL){
                response = Response.status(Returns.INTERNAL_SERVER_ERROR.getResponse()).entity(MESSAGE_INTERNAL_SERVER_ERROR).build();
            }else{
                response = Response.status(Returns.UNAUTHORIZED.getResponse()).entity(MESSAGE_USER_NOT_EXISTS).build();
            }
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }
        return response;*/
    }

    //Cumprir desafio
    public int runChallenge(int idUser, int idChallenge){
        

        return (new ChallengeController()).runChallenge(idUser,idChallenge);

        /*Response response = null;

        if (result != OK) {
            if(result == ERROR_INTERNAL){
                response = Response.status(Returns.INTERNAL_SERVER_ERROR.getResponse()).entity(MESSAGE_INTERNAL_SERVER_ERROR).build();
            }else if(result == ERROR_CHALLENGE_ALREADY_DONE_TODAY){
                response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_ALREADY_DONE_CHALLENGE_TODAY).build();
            }else if(result == ERROR_OVER_CHALLENGE){
                response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_OVER_CHALLENGE).build();
            }else if(result == ERROR_USER_NOT_IS_IN_CHALLENGE){
                response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_USER_OUT_CHALLENGE).build();
            }else{
                response = Response.status(Returns.BAD_REQUEST.getResponse()).entity(MESSAGE_BAD_REQUEST).build();
            }
        } else {
            String retorno = jf.Formatar(JsonConverter.toJson(result).toString());
            response = Response.ok().entity(retorno).build();
        }
        return response;*/
    }

}
