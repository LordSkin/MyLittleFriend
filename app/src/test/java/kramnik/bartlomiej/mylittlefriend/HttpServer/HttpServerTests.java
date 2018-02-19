package kramnik.bartlomiej.mylittlefriend.HttpServer;

import android.util.Log;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Observation;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseListener;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseServer;


/**
 * Http server tests
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class HttpServerTests {

    private ResponseServer testObject;

    @Mock
    private ResponseListener listener;

    @Mock
    private NanoHTTPD.IHTTPSession session;

    @Before
    public void prepare(){
        testObject = new ResponseServer();
        listener = Mockito.mock(ResponseListener.class);
        testObject.setListener(listener);
        PowerMockito.mockStatic(Log.class);


        session = Mockito.mock(NanoHTTPD.IHTTPSession.class);
        Map<String, String> map = new HashMap<String,String>();
        map.put("content-length", 0+"" );
        Mockito.when(session.getHeaders()).thenReturn(map);
        Mockito.when(session.getInputStream()).thenReturn(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });
    }

    @After
    public void cleanAfter(){
        testObject.stop();
    }

    @Test
    public void tets1(){

        try{
            testObject.serve(session);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void nullListenerTest(){
        testObject.setListener(null);
        try{
            testObject.serve(session);
        }
        catch (Exception e){
            Assert.fail();
        }
    }
}
