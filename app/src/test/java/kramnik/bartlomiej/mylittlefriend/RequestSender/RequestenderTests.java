package kramnik.bartlomiej.mylittlefriend.RequestSender;

import android.accounts.NetworkErrorException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.ActionsSequence;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;

/**
 * Tests of request sender
 */
public class RequestenderTests {

    private RequestSender testObject;



    @Before
    public void prepare(){
        testObject = new RequestSender();
    }

    @Test
    public void nullTestSending1(){
        try {
            testObject.sendCommands(null, "127.0.0.1");
            Assert.fail();
        }
        catch (IOException e) {
            Assert.fail();
        }
        catch (NetworkErrorException e) {
            Assert.fail();
        }
        catch (NullPointerException e){
            //OK
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void nullTestSending2(){
        try {
            testObject.sendCommands(new ActionsSequence(),null);
            Assert.fail();
        }
        catch (IOException e) {
            Assert.fail();
        }
        catch (NetworkErrorException e) {
            Assert.fail();
        }
        catch (NullPointerException e){
            //OK
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void nullTestPinging(){
        try{
            testObject.checkStatus(null);
            Assert.fail();
        }
        catch (NullPointerException e){
            //OK
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void pingingTest1(){
        try{
            Assert.assertTrue(testObject.checkStatus("127.0.0.1")== Agent.UNREACHABLE);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void pingingTestWrongIP(){
        try{
            Assert.assertTrue(testObject.checkStatus("justReallyWrongIPAddress")== Agent.UNREACHABLE);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void sendingCommandsTestPositive(){
        try {
            testObject.sendCommands(new ActionsSequence(), "portquiz.net");
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void sendingCommandsTestNegative(){
        try {
            testObject.sendCommands(new ActionsSequence(), "192.168.0.55");
            Assert.fail();
        }
        catch (IOException e) {
            Assert.fail();
        }
        catch (NetworkErrorException e) {
           //OK
        }
        catch (Exception e){
            Assert.fail();
        }
    }

}
