package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.ServicePriceDto;

public class PricingControllerTest {

    private static EmbeddedServer server;
    private static PricingTestClient client;

    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PricingTestClient.class, server.getURL());
    }

    @Test
    public void testClient() {
        ServicePriceDto dto = client.getPriceForService("SERVICE_1");

        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getPrice());
    }

    @AfterClass
    public static void cleanup() {
        if (server != null)
            server.stop();
    }
}