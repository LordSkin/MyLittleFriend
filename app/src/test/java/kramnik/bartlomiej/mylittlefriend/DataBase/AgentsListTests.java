package kramnik.bartlomiej.mylittlefriend.DataBase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentDao;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsDataBase;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsList;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;

/**
 * Tests of AgentsList
 */

public class AgentsListTests {

    private AgentsList testObjects;

    @Mock
    private AgentsDataBase agentsDataBase;

    @Mock
    private AgentDao agentDao;

    @Before
    public void prepare() {
        agentsDataBase = Mockito.mock(AgentsDataBase.class);
        agentDao = Mockito.mock(AgentDao.class);
        Mockito.when(agentsDataBase.agentDao()).thenReturn(agentDao);
        Mockito.when(agentDao.getAll()).thenReturn(new ArrayList<Agent>());

        testObjects = new AgentsList(agentsDataBase);

    }

    @Test
    public void getEmptyList() {
        try {
            Assert.assertTrue(testObjects.getAgents().size() == 0);
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void getList() {
        try {
            testObjects.addAgent(new Agent("", ""));
            Assert.assertTrue(testObjects.getAgents().size() == 1);
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addNull() {
        try {
            testObjects.addAgent(null);
            Assert.fail();
        }
        catch (NullPointerException e) {
            //OK
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addGet() {
        try {
            Agent test1 = new Agent("", "");
            Agent test2 = new Agent("", "");
            Agent test3 = new Agent("", "");
            testObjects.addAgent(test1);
            testObjects.addAgent(test2);
            testObjects.addAgent(test3);
            Assert.assertEquals(test1, testObjects.getAgent(0));
            Assert.assertEquals(test2, testObjects.getAgent(1));
            Assert.assertEquals(test3, testObjects.getAgent(2));
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void getWrong() {
        try {
            Agent test1 = new Agent("", "");
            testObjects.addAgent(test1);
            Assert.assertEquals(test1, testObjects.getAgent(1));
        }
        catch (Exception e) {
            //OK
        }
    }

    @Test
    public void deleteTest() {
        try {
            Agent test1 = new Agent("", "");
            testObjects.addAgent(test1);
            testObjects.deleteAgent(0);
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteTest2() {
        try {
            Agent test1 = new Agent("", "");
            testObjects.addAgent(test1);
            testObjects.deleteAgent(test1);
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteWrongTest() {
        try {
            Agent test1 = new Agent("", "");
            testObjects.addAgent(test1);
            testObjects.deleteAgent(-1);
            Assert.fail();
        }
        catch (Exception e) {
            //OK
        }
    }

    @Test
    public void deleteWrongTest2() {
        try {
            Agent test1 = new Agent("", "");
            testObjects.addAgent(test1);
            testObjects.deleteAgent(new Agent("", ""));
            Assert.fail();
        }
        catch (Exception e) {
            //OK
        }
    }

}
