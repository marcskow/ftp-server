package pl.edu.agh.marcskow.ftpserver.clientHandler;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Timer extends Thread {
    private final FtpSession session;
    private final int length;
    private int timeout;

    public Timer(int length, FtpSession session){
        this.length = length;
        this.session = session;
    }

    public synchronized void reset(){
        timeout = 0;
    }

    public void run(){
        int sleepRate = 100;
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
                    break;
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
