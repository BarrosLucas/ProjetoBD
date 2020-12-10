package utils;

public abstract class Constants {
    public static final String homeSite = "Site no ar!";

    public static final String CHALLENGE_IN = "in";
    public static final String CHALLENGE_OUT = "out";
    public static final String CHALLENGE_DONE = "done";

    public static final String MESSAGE_BAD_REQUEST = "Erro! Ocorreu um problema na requisição.";
    public static final String MESSAGE_CONFLICT = "Um objeto já foi criado com as mesmas informações.";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Ocorreu uma falha no servidor!";
    public static final String MESSAGE_USER_NOT_EXISTS = "Usuário não existe";
    public static final String MESSAGE_USER_UNAUTHORIZED = "Usuário ou senha incorretos";
    public static final String MESSAGE_CONFLICT_CHALLENGE = "Usuário já participa do desafio";
    public static final String MESSAGE_CHALLENGE_NOT_EXISTS = "Desafio inexistente";
    public static final String MESSAGE_ALREADY_DONE_CHALLENGE_TODAY = "Desafio já cumprido hoje";
    public static final String MESSAGE_OVER_CHALLENGE = "Perdeu o prazo de cumprimento do desafio";
    public static final String MESSAGE_USER_OUT_CHALLENGE = "Usuário fora do desafio";

    public static final int OK = 0;
    public static final int ERROR_INTERNAL = -1;

    public static final int ERROR_EMPTY = 1;
    public static final int ERROR_EMAIL = 2;
    public static final int ERROR_PASSWORD = 3;
    public static final int ERROR_USERNAME = 4;
    public static final int ERROR_NAME = 5;
    public static final int ERROR_NOT_EXISTS = 9;

    public static final int ERROR_USERNAME_CONFLICT = 6;
    public static final int ERROR_EMAIL_CONFLICT = 7;

    public static final int ERROR_CONFLICT_LOGIN = 8;

    public static final int ERROR_CONFLICT = 10;
    public static final int ERROR_CHALLENGE_ALREADY_DONE_TODAY = 11;
    public static final int ERROR_OVER_CHALLENGE = 12;
    public static final int ERROR_USER_NOT_IS_IN_CHALLENGE = 13;
    
    public static final int AMOUNT_DAYS_CHALLENGE = 10;

}
