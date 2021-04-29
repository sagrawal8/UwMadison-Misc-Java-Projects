import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageManagerTest {

  protected PackageManager pack;
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
       pack = new PackageManager();
  }
  
  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
  }
  
  @Test
  void testPackageMnager_001_test_construct_graph() throws FileNotFoundException, IOException, ParseException {
    try {
      pack.constructGraph("valid.json");
      assert(pack.getAllPackages().size() == 5);
    }
    catch(Exception e) {
      e.printStackTrace();
      fail("not expected");
    }
  }
  
  @Test
  void testPackageMnager_002_test_construct_graph() throws FileNotFoundException, IOException, ParseException {
    try {
      pack.constructGraph("valid.json");
      List<String> list = pack.getInstallationOrder("A");
      for(int i = 0; i < list.size(); i++)
        System.out.println(list.get(i));
    }
    catch(Exception e) {
      e.printStackTrace();
      fail("not expected");
    }
  }

}
