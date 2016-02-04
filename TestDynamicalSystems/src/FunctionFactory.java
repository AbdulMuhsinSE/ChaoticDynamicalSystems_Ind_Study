/**
 * Created by Grumpy Arab on 2/3/2016.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import javax.swing.text.html.HTMLDocument;

public class FunctionFactory {
    private static Queue<Double> myQueue = new LinkedList<>();
    private static String currentExpression = new String();

    private FunctionFactory(){};
    public static void repeat(String expression, double seed, int iterationFactor){
        myQueue.add(seed);
        //System.out.println(iterationFactor);
        if(Double.isInfinite(seed)){
            System.out.println("The expression has grown to Infinity and the iteration shall stop.");
            return;
        } else if(expression.contains("x") && iterationFactor>0){
            DoubleEvaluator evaluator = new DoubleEvaluator();
            String toWorkWith = expression+"";
            CharSequence seedString = seed + "";
            //System.out.println(toWorkWith);
            toWorkWith = toWorkWith.replace("x",seedString);
            //System.out.println(toWorkWith);
            Double newseed = evaluator.evaluate(toWorkWith);
            //System.out.println(newseed);
            int minus = iterationFactor -1;
            repeat(expression,newseed,minus);
        } else if(iterationFactor == 0){
            currentExpression = expression;
        }
    }
    public static void printQueueToConsole(){
        for(Double x: myQueue){
            System.out.println(x);
        }
    }

    public static void printQueueToFile(String filepath){
        int one = 1;
        try {
            File file = new File(filepath);
            PrintWriter writer = new PrintWriter(file);
            writer.println("The dynamical system was built using the expression: " + currentExpression);
            writer.println("The seed of this dynamical system is: " + myQueue.peek());
            for(Double x: myQueue){
                writer.print("F" +one+"(x)= ");
                writer.println(x);
                one++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
