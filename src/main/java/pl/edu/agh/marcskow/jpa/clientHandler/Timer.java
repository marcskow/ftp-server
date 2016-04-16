package pl.edu.agh.marcskow.jpa.clientHandler;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Timer extends Thread {
    private FtpSession session;
    private int timeout;
    private int length;
    private int sleepRate = 100;

    public Timer(int length, FtpSession session){
        this.length = length;
        this.session = session;
    }

    public synchronized void reset(){
        timeout = 0;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(sleepRate);
            }
            catch (InterruptedException e){
                continue;
            }

            synchronized (this){
                timeout += sleepRate;
                if(timeout > length){
                    connectionLost();
                }
            }
        }
    }

    public void connectionLost(){
        session.setUp(false);
        try {
            session.closeConnection();
        } catch (IOException e){
            log.error("Error ", e);
        }
    }
}
