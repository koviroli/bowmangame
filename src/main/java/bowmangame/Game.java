package bowmangame;
import action.ShotAction;
import controller.ArcherController;
import controller.ArrowController;
import controller.BowController;
import controller.TargetController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BowManGameObject;
import view.GameUI;

/**
 * Ebben az osztályban valósítódik meg a játék.
 * @author koviroli
 *
 */
public class Game extends BowManGameObject{
	
	private static Group root = new Group();
	private static Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	private Stage stage = new Stage();
	
	private ArcherController archController = new ArcherController();
	private BowController bowController = new BowController();
	private ArrowController arrowController = new ArrowController();
	private TargetController targetController = new TargetController();
	
	private GameUI gameUI = new GameUI();
	private ShotAction shot = new ShotAction(arrowController.getModel(), targetController.getModel());
	
	private User user;
	
	public Game(User user){
		this.user = user;
		gameUI.setPoints(user.getPoints());
		gameUI.setPlayerNameText(user.getUsername());
		stage.setScene(scene);
		addToRoot();
		eventHandler();
		stage.show();
		stage.setOnCloseRequest(e -> Platform.exit());
	}
	
	
	/**
	 * Az egér kezelésért felelős függvény
	 */
	private void eventHandler(){
		scene.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {            
                if (e.getClickCount() > 0) {         	
                	gameUI.setAimLineStartXY(e.getX(), e.getY());
                }  
            }
        });
		
		scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {            
                if (e.getClickCount() > 0) { 	
                	gameUI.setAimLineEndXY(e.getX(), e.getY());
                	gameUI.UpdateGameInfoTexts();
                	rotationHandler();
                	shot.setPower(gameUI.getPower());
                	shot.setAngle(gameUI.getAngle());
                }  
            }
        });
		
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {            
                if (e.getClickCount() > 0) { 
                	gameUI.clearAimLine();

                    if( shot.action() ){
                    	gameUI.incPoints();
                    	user.addPoint();
                    }
                    else{
                    	gameUI.decrPoints();
                    	user.losePoint();
                    }
                }  
            }
        });
	}
	
	//set the rotation of the models and also rotate them
	public void rotationHandler(){
		bowController.setRotation(gameUI.getAngle());
		arrowController.setRotation(gameUI.getAngle());
		bowController.rotate(archController.getModel().getCenterX(), archController.getModel().getCenterY());
		arrowController.rotate(archController.getModel().getCenterX(), archController.getModel().getCenterY());
	}
	
	//this function add elements to root
	private void addToRoot(){
		root.getChildren().add(archController.setView().getCanvas());
		root.getChildren().add(bowController.getView().getCanvas());
		root.getChildren().add(arrowController.getView().getCanvas());
		root.getChildren().add(targetController.getView().getCanvas());
		root.getChildren().add(gameUI.getAngleText());
		root.getChildren().add(gameUI.getPowerText());
		root.getChildren().add(gameUI.getAimLine());
		root.getChildren().add(gameUI.getPointsText());
		root.getChildren().add(gameUI.getPlayerNameText());
		root.getChildren().add(shot.getImw());
		root.getChildren().add(shot.getPath());
	}
	
	//gives back the scene we builded
	public static Scene scene(){ 
		return scene; 
	}	
}
