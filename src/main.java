import java.util.ArrayList;

import facade.Facade;
import model.Exercise;
import model.Modules;
import model.Training;
import model.User;
import utils.Constants;

public class main extends Constants{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Facade facade = new Facade();
		
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
		

	}

}
