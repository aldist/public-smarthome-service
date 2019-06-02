package de.aldist.smarthomeappserver.service;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.dto.FHEMResponse.GetResponse;
import de.aldist.smarthomeappserver.dto.ServiceResponse;
import de.aldist.smarthomeappserver.errorhandling.ServiceStarterNoSuchServiceException;
import de.aldist.smarthomeappserver.errorhandling.ServiceStarterProcessingServicesException;
import de.aldist.smarthomeappserver.service.regulation.FhemHeatingService;
import de.aldist.smarthomeappserver.service.regulation.FhemPowerSocketService;
import de.aldist.smarthomeappserver.service.regulation.RegulationService;
import de.aldist.smarthomeappserver.service.sensors.SensorsService;
import de.aldist.smarthomeappserver.service.sensors.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
/*
@ContextConfiguration(classes = {
        FhemHeatingService.class,
        FhemPowerSocketService.class,
        WeatherService.class,
})
*/
public class ServiceStarterImplTest {

    private ServiceStarterImpl serviceStarter;

    @Mock
    FhemHeatingService fhemHeatingService;

    @Mock
    FhemPowerSocketService fhemPowerSocketService;

    @Mock
    WeatherService weatherService;

    @Spy
    List<ComponentService> componentService = new ArrayList<>();

    @Before
    public void setUp() {
        this.componentService.add(this.fhemHeatingService);
        this.componentService.add(this.fhemPowerSocketService);
        this.componentService.add(this.weatherService);

        this.serviceStarter = new ServiceStarterImpl(this.componentService);
    }

    @Test
    public void returnsServiceResponseFromGetDataWhenAtLeastOneServiceIsPresent() {
        when(this.fhemHeatingService.getData(any(Optional.class), any(Optional.class))).thenReturn(CompletableFuture.completedFuture("validReturnValue1"));
        when(this.fhemPowerSocketService.getData(any(Optional.class), any(Optional.class))).thenReturn(CompletableFuture.completedFuture("validReturnValue2"));

        List<?> serviceValues = this.serviceStarter.getDataFromServices(RegulationService.class, Optional.empty(), Optional.empty());
        List<String> expectedValues = new ArrayList<>();
        expectedValues.add("validReturnValue1");
        expectedValues.add("validReturnValue2");

        assertThat(serviceValues).isEqualTo(expectedValues);
    }

    @Test
    public void returnsEmptyServiceResponseFromGetDataWhenNoServiceIsPresent() {
        List<?> serviceValues = this.serviceStarter.getDataFromServices(String.class, Optional.empty(), Optional.empty());
        List<String> expectedValues = new ArrayList<>();

        assertThat(serviceValues).isEqualTo(expectedValues);
    }

    @Test(expected = ServiceStarterProcessingServicesException.class)
    public void throwsServiceStarterProcessingServicesExceptionWhenCompletitionExceptionIsThrown() {
        doThrow(CompletionException.class).when(this.weatherService).getData(any(Optional.class), any(Optional.class));

        this.serviceStarter.getDataFromServices(SensorsService.class, Optional.empty(), Optional.empty());
    }

    @Test
    public void returnsServiceResponseFromSetValueWhenServiceExists() {
        ServiceResponse serviceResponse = new ServiceResponse("validServiceName", "status", false);

        when(this.fhemHeatingService.setValue(any(Command.class))).thenReturn(serviceResponse);

        ServiceResponse serviceStarterResponse = this.serviceStarter.executeCommandOnDevice(
                new Command(
                        this.fhemHeatingService.getClass().getSimpleName(),
                        "validDevice",
                        "validCommand",
                        "validValue"
                )
        );

        assertThat(serviceResponse).isEqualTo(serviceStarterResponse);
    }

    @Test(expected = ServiceStarterNoSuchServiceException.class)
    public void throwsServiceStarterNoSuchServiceExceptionWhenServiceDoesntExists() {
        //ServiceResponse serviceResponse = new ServiceResponse("validServiceName", "status", false);
        //when(this.fhemHeatingService.setValue(any(Command.class))).thenReturn(serviceResponse);

        this.serviceStarter.executeCommandOnDevice(new Command(
                "invalidServiceName",
                "validDevice",
                "validCommand",
                "validValue"
                )
        );
    }
}
