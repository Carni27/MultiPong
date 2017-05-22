package riccardocarnicelli.gmail.com.multipong.Utilities;

/**
 * Created by Riccardo on 15/05/2017.
 */
public class Function {


    /**
     * Risolve la proporzione A:B=C:D restituendo D
     *
     * @param a Il parametro A (divisore)
     * @param b Il parametro B (moltiplicare)
     * @param c Il parametro C (moltiplicare)
     *
     * @return d (b*c)/a
     *
     */
    public static float resolveProportions(float a, float b, float c ){
        return (b*c)/c;
    }

}
