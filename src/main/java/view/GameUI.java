package view;

import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GameUI {

	//the maximum power of the shot
	private static final double MAX_POWER = 150.0f;
	
	private static final double POINTS_TEXT_X = 30.0f;
	
	private static final double POINTS_TEXT_Y = 30.0f;
	
	private static final double PLAYER_NAME_TEXT_X = 150.0f;
	
	private static final double PLAYER_NAME_TEXT_Y = 30.0f; 
	
	//aimLine graphically representing the angle and power of the shot
	private Line aimLine;
	
	//powerText a text shows the power of the shot
	private Text powerText;
	
	//angleText a text shows the angle of the shot
	private Text angleText;
	
	//the power of the shot
	private double power;
	
	//the angle of the shot
	private double angle;
	
	private int points;
	
	private Text pointsText;
	
	private Text playerNameText;
	
	public GameUI(){
		aimLine = new Line();
		powerText = new Text();
		angleText = new Text();
		pointsText = new Text();
		playerNameText = new Text();
		playerNameText.setX(PLAYER_NAME_TEXT_X);
		playerNameText.setY(PLAYER_NAME_TEXT_Y);
		setPointsText();

		this.aimLine.setStrokeWidth(0.2);
	}
	
	//we set the power of the shot
	//the shot's power can't be bigger the MAX_POWER
	public void setPower(){
		power = calculateDistance(aimLine.getStartX(), aimLine.getStartY(), aimLine.getEndX(), aimLine.getEndY());
		
		if (power > MAX_POWER){
			power = MAX_POWER;
		}
	}
	
	public double getPower(){
		setPower();
		return power;
	}

	public double getAngle() {
		setAngle();
		return angle;
	}

	public void setAngle() {
		angle = calculateAngle(aimLine.getEndX(), aimLine.getEndY(), aimLine.getStartX(), aimLine.getStartY() );
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void incPoints(){
		points++;
		setPointsText();
	}
	
	public void decrPoints(){
		points--;
		setPointsText();
	}

	public Text getPowerText() {
		return powerText;
	}
	
	//print power to a text with 2 decimal accurary
	public void setPowerText() {
		powerText.setText(String.format("%.2f", getPower()));
		powerText.setX(aimLine.getEndX());
		powerText.setY(aimLine.getEndY());
	}

	public Text getAngleText() {
		return angleText;
	}

	//print angle to a text with 2 decimal accurary
	public void setAngleText() {
		angleText.setText(String.format("%.2f°", getAngle()));
		angleText.setX(aimLine.getStartX());
		angleText.setY(aimLine.getStartY());
	}

	public Text getPointsText() {
		return pointsText;
	}

	public void setPointsText() {
		pointsText.setText(String.format("points: %.0f", getPoints()));
		pointsText.setX(POINTS_TEXT_X);
		pointsText.setY(POINTS_TEXT_Y);
	}

	public Text getPlayerNameText() {
		return playerNameText;
	}

	public void setPlayerNameText(String playerName) {
		this.playerNameText.setText("player: " + playerName);
	}

	public Line getAimLine() {
		return aimLine;
	}

	public void setAimLine(Line aimLine) {
		this.aimLine = aimLine;
	}
	
	public void setAimLineStartXY(double x, double y){
		this.aimLine.setStartX(x);
		this.aimLine.setStartY(y);
	}
	
	public void setAimLineEndXY(double x, double y){
		aimLine.setEndX(x);
		aimLine.setEndY(y);
	}
	
	public void clearAimLine(){
		setAimLineStartXY(0, 0);
		setAimLineEndXY(0, 0);
	}
	
	private double calculateDistance(double Ax, double Ay, double Bx, double By){
		return  Math.sqrt( Math.pow(Ax-Bx, 2) + Math.pow(Ay-By, 2)) ;
	}
	
	private double calculateAngle(double x1, double y1, double x2, double y2) {
	    double angle = (-1) *  Math.toDegrees(Math.atan2(y2 - y1 , x2 - x1   ));

	    return angle ;
	}
	
	public void UpdateGameInfoTexts(){
		setAngleText();
		setPowerText();
	}
	
}
