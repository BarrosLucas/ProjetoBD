package controller;

import dao.ChallengeDAO;
import dao.ProfileChallengeDAO;
import model.Challenge;
import model.ProfileChallenge;
import utils.Compare;
import utils.Constants;
import utils.Format;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class ChallengeController extends Constants {
    public ArrayList<Challenge> getChallenges(){
        return ((new ChallengeDAO()).getChallenges());
    }

    public int intoInChallenge(long idUser, long idChallenge){
        ProfileChallengeDAO profileChallengeDAO = new ProfileChallengeDAO();

        ProfileChallenge profileChallenge = profileChallengeDAO.getProfileChallenge(idUser, idChallenge);
        if(profileChallenge == null){
            Challenge challenge = (new ChallengeDAO()).getChallenge(idChallenge);
            if(challenge != null){

                profileChallenge = new ProfileChallenge();
                profileChallenge.setStatus(CHALLENGE_IN);
                profileChallenge.setDateStart(Format.dateToString(Calendar.getInstance().getTime()));
                profileChallenge.setChallenge(challenge);

                if(profileChallengeDAO.createProfileChallengeDAO(profileChallenge,idUser)){
                    return OK;
                }else{
                    return ERROR_INTERNAL;
                }
            }else{
                return ERROR_NOT_EXISTS;
            }
        }else if(profileChallenge.getStatus().equalsIgnoreCase(CHALLENGE_OUT)){
            profileChallenge.setStatus(CHALLENGE_IN);
            profileChallenge.setDateStart(Format.dateToString(Calendar.getInstance().getTime()));
            if(profileChallengeDAO.updateProfileChallenge(profileChallenge)){
                return OK;
            }else{
                return ERROR_INTERNAL;
            }
        }else{
            return ERROR_CONFLICT;
        }
    }

    public int outChallenge(long idUser, long idChallenge){
        ProfileChallengeDAO profileChallengeDAO = new ProfileChallengeDAO();
        ProfileChallenge profileChallenge = profileChallengeDAO.getProfileChallenge(idUser, idChallenge);

        if(profileChallenge != null){
            if(profileChallenge.getStatus().equalsIgnoreCase(CHALLENGE_IN)){
                profileChallenge.setStatus(CHALLENGE_OUT);
                if(profileChallengeDAO.updateProfileChallenge(profileChallenge)){
                    return OK;
                }else{
                    return ERROR_INTERNAL;
                }
            }else{
                return ERROR_USER_NOT_IS_IN_CHALLENGE;
            }
        }else{
            return ERROR_NOT_EXISTS;
        }
    }

    public int runChallenge(long idUser, long idChallenge){
        ProfileChallengeDAO profileChallengeDAO = new ProfileChallengeDAO();
        ProfileChallenge profileChallenge = profileChallengeDAO.getProfileChallenge(idUser, idChallenge);
        if(profileChallenge != null){
            if(profileChallenge.getStatus().equalsIgnoreCase(Constants.CHALLENGE_IN)){
                Challenge challenge = (new ChallengeDAO().getChallenge(idChallenge));
                if(challenge != null){
                    try {
                        Calendar cStart = Format.stringToDate(profileChallenge.getDateStart());
                        Calendar cLast  = Format.stringToDate(profileChallenge.getDateLast());

                        Calendar now = Format.stringToDate(Format.dateToString(Calendar.getInstance().getTime()));

                        int daysSinceLastChallengeRun = Compare.differenceBetweenDate(now,cLast);
                        if(daysSinceLastChallengeRun == 1){ //OK
                            profileChallenge.setDateLast(Format.dateToString(now.getTime()));
                            if((Compare.differenceBetweenDate(cLast,cStart)+1)==AMOUNT_DAYS_CHALLENGE){
                                profileChallenge.setStatus(Constants.CHALLENGE_DONE);
                            }
                            if(profileChallengeDAO.updateProfileChallenge(profileChallenge)){
                                return OK;
                            }else{
                                return ERROR_INTERNAL;
                            }
                        }else if(daysSinceLastChallengeRun == 0){ //Cumpriu o desafio ainda hoje
                            return ERROR_CHALLENGE_ALREADY_DONE_TODAY;
                        }else if(daysSinceLastChallengeRun > 1){ //Perdeu o desafio, cumpriu há muito tempo
                            outChallenge(idUser,idChallenge);
                            return ERROR_OVER_CHALLENGE;
                        }else{ //Invalido, diferença de dias menor que 0
                            return ERROR_INTERNAL;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return ERROR_INTERNAL;
                    }
                }else{
                    return ERROR_INTERNAL;
                }
            }
            else{ //USUÁRIO NÃO ESTÁ NO DESAFIO
                return ERROR_USER_NOT_IS_IN_CHALLENGE;
            }
        }else{
            return ERROR_INTERNAL;
        }
    }
}
