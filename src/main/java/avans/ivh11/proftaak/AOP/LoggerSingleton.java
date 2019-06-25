package avans.ivh11.proftaak.AOP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggerSingleton
{

    // Static member holds only one instance of the
    // LoggerSingleton class
    private static LoggerSingleton singletonInstance;

    private final Logger logger = LoggerFactory.getLogger(LoggerSingleton.class);
    // LoggerSingleton prevents any other class from instantiating
    private LoggerSingleton() {
    }
    // Providing Global point of access
    public static LoggerSingleton getSingletonInstance() {
        if (null == singletonInstance) {
            singletonInstance = new LoggerSingleton();
            System.out.println("Creating new instance");
        }
        return singletonInstance;
    }
    public void logSingleton(String message){
        logger.debug(message);
        System.out.println("Inside print Singleton");
    }
}

