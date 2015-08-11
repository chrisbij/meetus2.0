package vue;

import controller.PasserelleDB;
import android.view.View;
import android.view.View.OnClickListener;
import model.Sortie;
import model.User;

public class CreateView {

	private User user;
	private Sortie sortie;
	PasserelleDB passerelle;
	
	
	public void openNewView(String option){
		
	}
	
	/*
	 * Vue permettant de cr&eacute;er un nouvel utilisateur
	 */
	
	public OnClickListener onClickCreatUser = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			/*
			 * methodes de la classe User
			 */
			passerelle.createUser(user);
		}
		
	};
	
	
	/*
	 * Vue permettant de cr&eacute;er une nouvelle sortie
	 */
	
	public OnClickListener onClickCreateSortie = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			/*
			 * Methodes de la classe Sortie
			 */
			passerelle.createSortie(sortie);
		}
	};
	
	
}
