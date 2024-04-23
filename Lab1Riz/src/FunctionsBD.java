import entity.Clients;
import entity.Masters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FunctionsBD {
    private static final Logger log= LoggerFactory.getLogger(FunctionsBD.class);
    public static void ClientsOut(Clients clients){
        log.info(clients.getId() + " " + clients.getName() + " " + clients.getSurname());
    }
    public static void MastersOut(Masters masters){
        log.info(masters.getId() + " " + masters.getName() + " " + masters.getSurname());
    }
}
