package call.callcenter.junit;

import com.callcenter.main.Call;
import com.callcenter.main.Dispatcher;
import com.callcenter.main.Employee;
import com.callcenter.main.SimultaneousCalls;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ExecutionException;


/**
 * Created by hitok on 25/06/2017.
 * Debe tener un test unitario donde lleguen 10 llamadas.
 */

public class CallTest extends TestCase {

    public Dispatcher dispatcher = new Dispatcher();

    /**
     * @case normal cuando hay llamadas y hay empleados
     * */
    @Test
    public void test10Call()throws InterruptedException, ExecutionException {
        this.makeEmployessCaseA();
        try {
            this.makeCall();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * @case cuando hay mas de 10 llamadas
     * */
    public void testMore10Call()throws InterruptedException, ExecutionException {
        this.makeEmployessCaseA();
        try {
            this.makeMore10Call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
    * @case no hay diponibilidad
     * Dar alguna solución sobre qué pasa con una llamada cuando no
     * hay ningún empleado libre.
    * */
    public void testNoAvailabilityCall()throws InterruptedException, ExecutionException {
        this.makeEmployessCaseB();
        try {
            this.makeCall();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @case Dar alguna solución sobre qué pasa con una llamada cuando
     entran más de 10 llamadas concurrentes.
     * */
    private void makeEmployessCaseB(){

        // operadores
        dispatcher.operadores.add( new Employee(1,  false));
        dispatcher.operadores.add( new Employee(2, false));

        // supervisores
        dispatcher.supervisores.add( new Employee(3, false));

        // empleados
        dispatcher.directores.add( new Employee(4, false));
    }

    private void makeEmployessCaseA(){

        // operadores
        dispatcher.operadores.add( new Employee(1,  false));
        dispatcher.operadores.add( new Employee(2, true));

        // supervisores
        dispatcher.supervisores.add( new Employee(3, true));

        // empleados
        dispatcher.directores.add( new Employee(4, true));
    }

    /**
    * Armando 10 llamadas
    * */
    private void makeCall() throws InterruptedException {

        for (int i = 0; i < 10; i++){
            SimultaneousCalls calling = new SimultaneousCalls();
            calling.setDispatcher(this.dispatcher);
            calling.setCall(new Call(i));
            calling.setPriority(10 - i);
            calling.start();
            System.out.println("Llamadas entrantes" + calling.num_thread);
        }
    }

    /**
     * Armando 10 llamadas
     * */
    private void makeMore10Call() throws InterruptedException {

        for (int i = 0; i < 11; i++){
            SimultaneousCalls calling = new SimultaneousCalls();
            calling.setDispatcher(this.dispatcher);
            calling.setCall(new Call(i));
            calling.setPriority(11 - i);
            calling.start();
            System.out.println("Llamadas entrantes" + calling.num_thread);
        }
    }
}
