package action;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.GameObjectModel;
import model.TargetModel;

/**
 * ShotAction felelős az íj nyílból való kilövéséhez.
 * @author koviroli
 *
 */
public class ShotAction extends ShotActionModel{
	
	/**
	 * Az út amit bejár a nyíl a lövés következtében.
	 */
	private Path path = new Path();
	
	
	private PathTransition pTransition = new PathTransition();
	
	/**
	 * imw a kapott képet reprezentálja. Ez fog mozogni a kép helyett.
	 */
	private ImageView imw = new ImageView();
	
	/**
	 * Ezt a képet adjuk át az imw -nek.
	 */
	private Image image;
	

	public ShotAction() {
	
	}
	
	/**
	 * A ShotAction konstruktora kapja a model-t amit kilövünk és a target-et amit el kell találjunk.
	 * @param model az az objektum amivel lövünl.
	 * @param target az az objektum amit el kell találni.
	 */
	public ShotAction(GameObjectModel model, TargetModel target){
		image = model.getImage();
		this.sx = model.getPosX();
		sy = model.getPosY();
		angle = model.getRotation();
		imw.setTranslateX(model.getPosX());
		imw.setTranslateY(model.getPosY());
		this.tx1 = target.getX1();
		this.ty1 = target.getY1();
		this.tx2 = target.getX2();
		this.ty2 = target.getY2();
	}
	
	/**
	 * @return visszaadja azt az utat, amit a nyíl bejár a lövés során.
	 */
	public Path getPath() {
		return path;
	}
	
	/**
	 * @param path átadjuk a path osztályváltozónknak a path -ot, ami szintén egy utat kell tartalmazzon.
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	
	/**
	 * Visszaadja a pathTransition-t, az az, az animációt amivel a path-t bejárjuk.
	 * @return
	 */
	public PathTransition getpTransition() {
		return pTransition;
	}
	
	/**
	 * Beállítjuk miként járja be az utat a modellünk.
	 * @param pTransition az út bejárása során lejátszandó animáció
	 */
	public void setpTransition(PathTransition pTransition) {
		this.pTransition = pTransition;
	}
	
	public ImageView getImw() {
		return imw;
	}

	public void setImw(ImageView imw) {
		this.imw = imw;
	}
	
    /**
     * A lövés ívét szolgátató függvény
     * @param x egy double változó amit a shotArc függvénybe helyettesítve megkapjuk a hozzátartozó y értéket.
     * @return	a függvény x-hez tartozó y értéke.
     */
    public double shotArc(double x){
    	double y = (Math.tan(Math.toRadians(angle)) * x) 
    			- (G / (2.0f * Math.pow(power, 2) * Math.pow(Math.cos(Math.toRadians(angle)), 2))) * Math.pow(x, 2);
    	
    	return y;
    }
    
    /**
     * A matematikai definíciója ennek a függvénynek:(v0^2) * sin(2*alfa)/g
     * @return Adott erejű és szögű lövés esetén a maximum x értéket adja vissza. 
     */
    public double xmax(){
    	double max = (Math.pow(power, 2) * Math.sin(2 * Math.toRadians(angle))) / G;
    	return max;
    }
   
	/**
	 * Inicializáljuk a képet, az ut és az animációt.
	 * @return
	 */
	public boolean Initalize(){
		imw.setImage(image);
	    path.setOpacity(0.0);
	    pTransition.setDuration(Duration.seconds(1.0f));
	    pTransition.setDelay(Duration.seconds(0.0f));
	    pTransition.setPath(path);
	    pTransition.setNode(imw);
	    pTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

    	path.getElements().add(new MoveTo(this.sx, this.sy));
    	double arcx = 0.0f, arcy = 0.0f;
    	for(double i = this.sx; arcy < WINDOW_HEIGHT; i += 1.0f){
	    	arcx = i;
	    	arcy = -shotArc(i)+this.sy;
    		path.getElements().add(new LineTo(arcx, arcy ));
    		if((arcy > ty1) && (arcy < ty2) && (Math.abs(arcx - tx1) < 5.0f)){ 
    			return true;
    			//break;
    		}
	    }
    	return false;
    }
	
    /**
     * Maga a lövést reprezentáló függvény.
     * @return igazat ad vissza, ha talált, egyébként hamisat.
     */
    public boolean action(){	  
    	hit = Initalize();
	    pTransition.play();
	    path.getElements().clear();
	    return hit;
    }	
}
