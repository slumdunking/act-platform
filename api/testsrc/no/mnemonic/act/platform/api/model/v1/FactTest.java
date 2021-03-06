package no.mnemonic.act.platform.api.model.v1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FactTest {

  private static final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testEncodeFact() {
    Fact fact = createFact();
    JsonNode root = mapper.valueToTree(fact);
    assertEquals(fact.getId().toString(), root.get("id").textValue());
    assertTrue(root.get("type").isObject());
    assertEquals(fact.getValue(), root.get("value").textValue());
    assertTrue(root.get("inReferenceTo").isObject());
    assertTrue(root.get("organization").isObject());
    assertTrue(root.get("source").isObject());
    assertEquals(fact.getAccessMode().toString(), root.get("accessMode").textValue());
    assertEquals("2016-11-30T15:47:01Z", root.get("timestamp").textValue());
    assertEquals("2016-11-30T15:47:02Z", root.get("lastSeenTimestamp").textValue());
    assertTrue(root.get("sourceObject").isObject());
    assertTrue(root.get("destinationObject").isObject());
    assertTrue(root.get("bidirectionalBinding").booleanValue());
  }

  @Test
  public void testEncodeFactInfo() {
    Fact.Info fact = createFactInfo();
    JsonNode root = mapper.valueToTree(fact);
    assertEquals(fact.getId().toString(), root.get("id").textValue());
    assertTrue(root.get("type").isObject());
    assertEquals(fact.getValue(), root.get("value").textValue());
  }

  private Fact createFact() {
    return Fact.builder()
            .setId(UUID.randomUUID())
            .setType(createFactTypeInfo())
            .setValue("value")
            .setInReferenceTo(createFactInfo())
            .setOrganization(Organization.builder().setId(UUID.randomUUID()).setName("organization").build().toInfo())
            .setSource(Source.builder().setId(UUID.randomUUID()).setName("source").build().toInfo())
            .setAccessMode(AccessMode.Explicit)
            .setTimestamp(1480520821000L)
            .setLastSeenTimestamp(1480520822000L)
            .setSourceObject(createObjectInfo())
            .setDestinationObject(createObjectInfo())
            .setBidirectionalBinding(true)
            .build();
  }

  private Fact.Info createFactInfo() {
    return Fact.builder()
            .setId(UUID.randomUUID())
            .setType(createFactTypeInfo())
            .setValue("value")
            .build()
            .toInfo();
  }

  private FactType.Info createFactTypeInfo() {
    return FactType.builder()
            .setId(UUID.randomUUID())
            .setName("factType")
            .build()
            .toInfo();
  }

  private Object.Info createObjectInfo() {
    return Object.builder()
            .setId(UUID.randomUUID())
            .setType(ObjectType.builder().setId(UUID.randomUUID()).setName("objectType").build().toInfo())
            .setValue("value")
            .build()
            .toInfo();
  }

}
