package com.serviceexchange.www.serviceexchangeandroid.loginmodule;

import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

public class CustomEvent {
    private CustomEventType eventType;
    private Object object;

    public CustomEvent(CustomEventType eventType, Object object) {
        this.eventType = eventType;
        this.object = object;
    }

    public CustomEventType getEventType() {
        return eventType;
    }

    public void setEventType(CustomEventType eventType) {
        this.eventType = eventType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
