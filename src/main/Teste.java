package main;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class Teste {

	public static void main(String[] args) {
		Rengine r = new Rengine(new String[]{"--vanilla"}, false, null);
	    r.eval("library(Hmisc)");
	    r.eval("yy <- describe(rnorm(200))");
	    REXP exp = r.eval("zz <- yy$counts[5:11]");
	    REXP names = r.eval("names(zz)");
	    String[] strExp = exp.asStringArray();
	    System.out.println("result:" + exp);
	 
	    r.eval("histval <- hist(rnorm(100), plot=FALSE)");
	    REXP xvalExp = r.eval("histval$mids");
	    REXP yvalExp = r.eval("histval$counts");
	    System.out.println("histval$mids:" + xvalExp);
	    System.out.println("histval$counts:" + yvalExp);
	}

}
