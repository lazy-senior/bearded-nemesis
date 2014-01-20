package FX;

import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WoahGUI extends BorderPane{

	@FXML
	private GridPane woahGrid;
	@FXML
	private Label woahWin;
	//Sorcery
	private Label[][] woahLabel = new Label[3][3];
	private int setCount = 0;
	private int player = -1;
	
	private static String O = "O";
	private static String X = "X";
	private static String __ = "  ";
	
	public WoahGUI() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WoahGUI.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		loader.load();
		init();
	}
	private String calculateWinner(){
		for(int x = 0; x < 3; x++){
			if(woahLabel[x][0].getText().equals(woahLabel[x][1].getText()) &&
					woahLabel[x][0].getText().equals(woahLabel[x][2].getText()) && 
					!woahLabel[x][0].getText().equals("  "))
				return woahLabel[x][0].getText();
		}
		for(int y = 0; y < 3; y++){
			if(woahLabel[0][y].getText().equals(woahLabel[1][y].getText()) &&
					woahLabel[0][y].getText().equals(woahLabel[2][y].getText()) && 
					!woahLabel[0][y].getText().equals("  "))
				return woahLabel[0][y].getText();
		}
		if(woahLabel[0][0].getText().equals(woahLabel[1][1].getText()) &&
			woahLabel[0][0].getText().equals(woahLabel[2][2].getText()) && 
			!woahLabel[0][0].getText().equals("  "))
			return woahLabel[0][0].getText();
		if(woahLabel[0][2].getText().equals(woahLabel[1][1].getText()) &&
			woahLabel[0][2].getText().equals(woahLabel[2][0].getText()) && 
			!woahLabel[0][2].getText().equals("  "))
			return woahLabel[0][2].getText();
		return null;
	}
	private void KI(MouseEvent e){
		if(player > -1)
		{
			if(setCount >= 9){
				return;
			}
			
			if(player == 0){
				player = 1;
				((Label) e.getSource()).setText(X);
			}
			else{
				player = 0;
				((Label) e.getSource()).setText(O);
			}
			
			setCount++;
		}
		else{
			
			if(setCount >= 9){
				return;
			}
			
			System.out.println("KI" + setCount);
			S((Label) e.getSource(),X);
			setCount++;
			
			//PRO KI -------------------------
			
			if(setCount >= 9){
				return;
			}
			setCount++;
			//CHECK IF COMPUTER CAN WIN --------
			for(int i = 0; i < 3; i++){
				//VERTIKAL
				if(o(i,1) && o(i,2) && _(i,0)){
					s(i,0,O);
					return;
				}
				if(o(i,1) && o(i,0) && _(i,2)){
					s(i,2,O);
					return;
				}
				if(o(i,0) && o(i,2) && _(i,1)){
					s(i,1,O);
					return;
				}
				//Horizontal
				if(o(1,i) && o(2,i) && _(0,i)){
					s(0,i,O);
					return;
				}
				if(o(1,i) && o(0,i) && _(2,i)){
					s(2,i,O);
					return;
				}
				if(o(0,i) && o(2,i) && _(1,i)){
					s(1,i,O);
					return;
				}
			}
			
			//DIAGONAL \
			if(o(1,1) && o(2,2) && _(0,0))
				s(0,0,O);
			else if(o(1,1) && o(0,0) && _(2,2))
				s(2,2,O);
			else if(o(2,2) && o(0,0) && _(1,1))
				s(1,1,O);
			//DIAGONAL /
			else if(o(1,1) && o(0,2) && _(2,0))
				s(2,0,O);
			else if(o(1,1) && o(2,0) && _(0,2))
				s(0,2,O);
			else if(o(0,2) && o(2,0) && _(1,1))
				s(1,1,O);
			
			//END CHECK IF COMPUTER CAN WIN -------------------------------------
			//CHECK IF PLAYER   CAN WIN --------
			else{
				for(int i = 0; i < 3; i++){
					//VERTIKAL
					if(x(i,1) && x(i,2) && _(i,0)){
						s(i,0,O);
						return;
					}
					if(x(i,1) && x(i,0) && _(i,2)){
						s(i,2,O);
						return;
					}
					if(x(i,0) && x(i,2) && _(i,1)){
						s(i,1,O);
						return;
					}
					//Horizontal
					if(x(1,i) && x(2,i) && _(0,i)){
						s(0,i,O);
						return;
					}
					if(x(1,i) && x(0,i) && _(2,i)){
						s(2,i,O);
						return;
					}
					if(x(0,i) && x(2,i) && _(1,i)){
						s(1,i,O);
						return;
					}
				}
				//DIAGONAL \
				if(x(1,1) && x(2,2) && _(0,0))
					s(0,0,O);
				else if(x(1,1) && x(0,0) && _(2,2))
					s(2,2,O);
				else if(x(2,2) && x(0,0) && _(1,1))
					s(1,1,O);
				//DIAGONAL /
				else if(x(1,1) && x(0,2) && _(2,0))
					s(2,0,O);
				else if(x(1,1) && x(2,0) && _(0,2))
					s(0,2,O);
				else if(x(0,2) && x(2,0) && _(1,1))
					s(1,1,O);
				else{
					//RANDOM SO BAD
					Random r = new Random();
					while(true){
						int x = r.nextInt(3);
						int y = r.nextInt(3);
						if(_(x,y)){
							s(x,y,O);
							
							break;
						}
					}
				}
			}//endelse
		}
	}
	//Set a label in the x,y model to o
	private void s(int x, int y, String o) {
		S(l(x,y),o);
	}
	// Set a label to o;
	private void S(Label l, String o){
		l.setText(o);
	}
	// Get a label
	private Label l(int x, int y){
		return woahLabel[x][y];
	}
	// Assign x,y Model with a new Label
	private void L(int x, int y, Label l){
		woahLabel[x][y] = l;
	}
	//is empty
	private boolean _(int x, int y ){
		return is(__,l(x,y));
	}
	// is x
	private boolean x(int x, int y ) {
		return is(X,l(x,y));
	}
	// is o
	private boolean o(int x, int y ) {
		return is(O,l(x,y));
	}
	// is String l
	private boolean is(String p, Label l){
		return l.getText().equals(p);
	}
	//Add a label to the grid
	private void a(Label l , int x, int y){
		woahGrid.add(l,x,y);
	}
	@FXML
	private void onMenuExitAction(ActionEvent event){
		((Stage) this.getScene().getWindow()).close();
	}
	@FXML
	public void init(){
		woahWin.setText("");
		setCount = 0;		
		woahGrid.getChildren().clear();
		for(int x = 0; x < 3;x++){
			woahLabel[x] = new Label[3];
			for(int y = 0; y < 3; y++){

				Label l = new Label(__);
				l.setStyle("-fx-border-color:#5566EE;-fx-border-width:1px");
				l.setAlignment(Pos.CENTER);
				l.setPrefSize(31,31);
				L(x,y,l);
				
				l(x,y).setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public	void handle(MouseEvent e){
						if(!(is(X,(Label) e.getSource())) && !(is(O,(Label) e.getSource()))){
							KI(e);
							if(calculateWinner() != null || setCount >= 9){
								S(woahWin,"WoahWinner = " + calculateWinner());
								setCount = 9;
							}
							
						}
					}
				});
				a(l(x,y),x,y);
			}
		}
	}
	
}
