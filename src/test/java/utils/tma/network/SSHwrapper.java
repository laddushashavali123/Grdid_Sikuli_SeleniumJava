package utils.tma.network;

import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;

import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSHwrapper {
    private String username;
    private String hostname;
    private int port;
    private String password;
    private Session session;
    private Expect expect;
    private Channel channel;

    public static final Logger logger = LoggerFactory.getLogger("SSHwrapper");

    public SSHwrapper(String hostname, int port, String username, String password) {
        this.username = username;
        this.hostname = hostname;
        this.password = password;
        this.port = port;
    }

    public void connect() throws IOException, JSchException {
        
        JSch jsch = new JSch();
        session = jsch.getSession(username, hostname, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
        channel = session.openChannel("shell");
        channel.connect();
        expect = new ExpectBuilder()
                .withOutput(channel.getOutputStream())
                .withInputs(channel.getInputStream(), channel.getExtInputStream())
                .withEchoInput(System.out)
                .withEchoOutput(System.err)
                .withInputFilters(removeColors(), removeNonPrintable())
                .withExceptionOnFailure()
                .build();
   }

    public void disconnect() {
        try {
            expect.close();
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            logger.error("Can not access host!!! " + e);
        }

    }
    
    public void send(String str) throws IOException{
        expect.send(str);
    }
    
    public void sendLine(String str) throws IOException{
        expect.sendLine(str);
    }
       
    public Expect interaction(){
        return this.expect;
    }
    
    public ChannelSftp buildSFTP() throws JSchException{
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        return sftpChannel;
    }
}  
