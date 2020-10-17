package json;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import static com.jayway.jsonassert.JsonAssert.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JsonExamplesTest {

    @Test
    public void assertion(){

        with(JsonExamples.getJson2())
                .assertThat("@.age", is(13))
                .assertThat("$.address.street", is("Main Street"));

        JSONArray jsonArray = JsonPath.read(JsonExamples.getJson2(),"$.phoneNumber");
        assertThat(jsonArray.contains("11-111-1111"), is(true));

    }
}