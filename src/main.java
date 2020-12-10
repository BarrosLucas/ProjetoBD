import java.util.ArrayList;

import facade.Facade;
import model.Exercise;
import model.Modules;
import model.Training;
import model.User;
import utils.Constants;
import java.util.Scanner;

public class main extends Constants{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Facade facade = new Facade();
		Scanner scan = new Scanner(System.in);
		
		User user = new User();
		
		// Criando o Perfil de Usuario
		System.out.println("*** PROJETO BANCO DE DADOS ***\n");
		System.out.println("Digite seu nome de Perfil: ");
		user.setName(scan.nextLine());
		user.setCalories(0);
		user.setDaysOfTraining(0);
		user.setFullChallenges(0);
		user.setTrainings(new ArrayList<Training>());

		int ret = facade.createUser(user);
		if(ret==OK) {
			System.out.println("Usuário cadastrado");
		}else {
			System.out.println("Falha no cadastro");
		}
		
		// Criando Exercicios
		Exercise exercise = new Exercise();
		
		exercise.setTitle("Polichinelo");
		exercise.setRepetition(60);
		exercise.setCaloriesPerSecond(3);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Prancha");
		exercise.setRepetition(1);
		exercise.setCaloriesPerSecond(2);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Pular corda");
		exercise.setRepetition(50);
		exercise.setCaloriesPerSecond(4);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Correr");
		exercise.setRepetition(10);
		exercise.setCaloriesPerSecond(3);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Agachamento");
		exercise.setRepetition(10);
		exercise.setCaloriesPerSecond(2);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Abdominal");
		exercise.setRepetition(25);
		exercise.setCaloriesPerSecond(1);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Flexão");
		exercise.setRepetition(26);
		exercise.setCaloriesPerSecond(1);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Rosca");
		exercise.setRepetition(12);
		exercise.setCaloriesPerSecond(1);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Elevação lateral");
		exercise.setRepetition(10);
		exercise.setCaloriesPerSecond(2);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		exercise.setTitle("Desenvolvimento");
		exercise.setRepetition(10);
		exercise.setCaloriesPerSecond(2);
		exercise.setLink("https://youtu.be/wyhAkcqrHaA");
		if(facade.createExercise(exercise)==OK) {
			System.out.println("Exercício criado");
		}else {
			System.out.println("Falha ao criar");
		}
		
		// MENU
		System.out.println("\n********** MENU **********\n");
		System.out.println("> Digite 1 para editar o nome do perfil");
		System.out.println("> Digite 2 para montar o treino");
		System.out.println("> Digite 3 para mostrar os desafios");
		System.out.println("> Digite 4 para mostrar os exercicios");
		int selecao = scan.nextInt();

		switch (selecao) {
			case 1:
				System.out.println("Informe o novo nome de perfil: ");
				user.setName(scan.nextLine());
				break;
			case 2:
				System.out.println("Montar treino ...");
				break;
			case 3:
				System.out.println("Mostrando desafios ...");
				break;
			case 4:
				System.out.println("Mostrando exercicios ...");
				break;
			default:
				System.out.println("Entrada Invalida!");
		}
	}

}
