package pl.edu.agh.marcskow.ftpserver.command;

import org.junit.Test;

import static org.junit.Assert.*;


public class CommandsTest {

    @Test
    public void testIsPassive() throws Exception {
        assertTrue("LIST should be passive ", Commands.isPassive("LIST"));
        assertTrue("APPE should be passive ", Commands.isPassive("APPE"));
        assertTrue("STOR should be passive ", Commands.isPassive("STOR"));
        assertTrue("RETR should be passive ", Commands.isPassive("RETR"));
    }

    @Test
    public void needAuthorization() throws Exception {
        assertTrue("PASV should needs authorization", Commands.needAuthorization("PASV"));
        assertFalse("USER should not needs authorization", Commands.needAuthorization("USER"));
    }
}