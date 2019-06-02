package de.aldist.smarthomeappserver.dto;


public class ServiceResponse<T> {

    private final String serviceName;
    private final T data;
    private final boolean error;

    public ServiceResponse(String serviceName, T data, boolean error) {
        this.serviceName = serviceName;
        this.data = data;
        this.error = error;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public T getData() {
        return this.data;
    }

    public boolean isError() {
        return this.error;
    }
}
