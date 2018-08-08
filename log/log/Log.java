/*@author FELIPE*/
package log;

//import dao.VendaEntregaDAO;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import teste.Teste;

public class Log {
    
    static Logger logger = Logger.getLogger(Log.class.getName());  
    Logger loggers = Logger.getLogger("WarnAppender");
    URL url = Log.class.getResource("log4j.properties");
    
    public void gerarLog(String teste) {  
        PropertyConfigurator.configure(url);
        //BasicConfigurator.configure();
                
        //logging in different levels
        //logger.all("This is a Trace");
        logger.config("This is a Config:" + teste + "\n");
        logger.fine("This is a Fine:" + teste + "\n");        
        logger.finer("This is an Finer:" + teste + "\n");
        logger.finest("This is a Finest:" + teste + "\n");
        logger.info("This is an Info:" + teste + "\n");        
        //logger.off("This is an Error" + teste + "\n");
        logger.severe("This is a Severe:" + teste + "\n");
        logger.warning("This is a Warning:" + teste + "\n");
        
        
        
        
    } 
}
