package json;

import java.io.StringReader;
import java.io.StringWriter;
import javax.json.*;

public class JsonExamples {

    public static void main(String[] args) {


    }

    private void generateJson() {
        String personJSONData = "  {" +
                "   \"name\": \"Jack\", " +
                "   \"age\" : 13, " +
                "   \"isMarried\" : false, " +
                "   \"address\": { " +
                "       \"street\": \"#1234, Main Street\", " +
                "       \"zipCode\": \"123456\" " +
                "   }, " +
                "   \"phoneNumbers\": [\"011-111-1111\", \"011-111-1111\", \"012-111-1212\" ] " +
                " }";

        JsonReader reader = Json.createReader(new StringReader(personJSONData));
        JsonObject personObject = reader.readObject();
        reader.close();

        System.out.println("Name   : " + personObject.getString("name"));
        System.out.println("Age    : " + personObject.getInt("age"));
        System.out.println("Married: " + personObject.getBoolean("isMarried"));
        JsonObject addressObject = personObject.getJsonObject("address");
        System.out.println("Address: ");
        System.out.println(addressObject.getString("street"));
        System.out.println(addressObject.getString("zipCode"));
        System.out.println("Phone  : ");
        JsonArray phoneNumbersArray = personObject.getJsonArray("phoneNumbers");
        for (JsonValue jsonValue : phoneNumbersArray) {
            System.out.println(jsonValue.toString());
        }
    }

    public static String getJson2() {

        JsonObject personObject = Json.createObjectBuilder()
                .add("name", "John")
                .add("age", 13)
                .add("isMarried", false)
                .add("address",
                        Json.createObjectBuilder()
                                .add("street", "Main Street")
                                .add("city", "New York")
                                .add("zipCode", "11111")
                )
                .add("phoneNumber",
                        Json.createArrayBuilder()
                                .add("00-000-0000")
                                .add("11-111-1111")
                                .add("11-111-1112")
                ).build();

        return personObject.toString();
    }

    public String getJson3() {

        JsonObject personObject = Json.createObjectBuilder()
                .add("name", "Jack")
                .add("age", 13)
                .add("isMarried", false)
                .add("address",
                        Json.createObjectBuilder()
                                .add("street", "Main Street")
                                .add("city", "New York")
                                .add("zipCode", "11111")
                )
                .add("phoneNumber",
                        Json.createArrayBuilder()
                                .add("00-000-0000")
                                .add("11-111-1111")
                                .add("11-111-1112")
                ).build();

        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(personObject);
        writer.close();
        return stringWriter.getBuffer().toString();
    }

    public String getJson4(){

        JsonObject json = Json.createObjectBuilder()
            .add("store",
                Json.createObjectBuilder()
                    .add("book", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                            .add("category", "reference")
                            .add("author", "Nigel Rees")
                            .add("title", "Sayings of the Century")
                            .add("price", 8.95))
                        .add(Json.createObjectBuilder()
                            .add("category", "fiction")
                            .add("author", "Evelyn Waugh")
                            .add("title", "Sword of Honour")
                            .add("price", 12.99))
                        .add(Json.createObjectBuilder()
                            .add("category", "fiction")
                            .add("author", "Herman Melville")
                            .add("title", "Moby Dick")
                            .add("isbn", "0-553-21311-3")
                            .add("price", 8.99))
                        .add(Json.createObjectBuilder()
                            .add("category", "fiction")
                            .add("author", "J. R. R. Tolkien")
                            .add("title", "The Lord of the Rings")
                            .add("isbn", "0-395-19395-8")
                            .add("price", 22.99))
                    )
                    .add("bicycle", Json.createObjectBuilder()
                            .add("color", "red")
                            .add("price", 19.95))
            ).add("expensive", 10).build();

        return json.toString();
    }
}
