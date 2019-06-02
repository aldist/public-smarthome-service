package de.aldist.smarthomeappserver.service.regulation;

import de.aldist.smarthomeappserver.dto.Command;
import de.aldist.smarthomeappserver.dto.ServiceResponse;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class FhemPowerSocketService implements RegulationService {

    public FhemPowerSocketService() {
    }

    @Override
    public CompletableFuture<?> getData(Optional<Date> from, Optional<Date> to) {
        return CompletableFuture.supplyAsync(() ->
                getPowerSocketData(
                        from.orElseGet(Date::new),
                        to.orElseGet(Date::new))
        );
    }

    @Override
    public ServiceResponse<?> setValue(Command command) {
        //TODO: set Command
        return new ServiceResponse<Object>(null,null,true);
    }

    private String getPowerSocketData(Date from, Date to) {
        return "Data from " + from.toGMTString() + " to " + to.toGMTString();
    }
}
