import java.util.ArrayList;

import facade.Facade;
import model.Training;
import model.User;
import utils.Constants;

public class main extends Constants{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setName("Lucas Freitas de Barros");
		user.setCalories(0);
		user.setDaysOfTraining(0);
		user.setFullChallenges(0);
		user.setTrainings(new ArrayList<Training>());
		
		
		int ret = (new Facade()).createUser(user);
		if(ret==OK) {
			System.out.println("Usuário cadastrado");
		}else {
			System.out.println("Falha no cadastro");
		}

	}

}
