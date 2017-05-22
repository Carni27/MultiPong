package riccardocarnicelli.gmail.com.multipong.Utilities;

/**
 * Created by Riccardo on 15/04/2017.
 */
public class PVector {

    private float x,y;

    public PVector(){
        this.x = this.y = 0;
    }

    public PVector(float x, float y){
        this.x = x;
        this.y = y;
    }


    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public PVector copy(){
        return new PVector(this.x, this.y);
    }

    public void add(PVector add){
        this.setX(this.getX() + add.getX());
        this.setY(this.getY() + add.getY());
    }

    public void limit(float limit){
        if (this.getX() > limit) { this.setX(limit); }
        if (this.getY() > limit) { this.setY(limit); }
    }

}
