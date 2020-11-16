package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

public class OperationsServerStatus extends ServerStatusDecorator {

    public OperationsServerStatus(ServerStatusInterface newServerStatus) {
        super(newServerStatus);
    }




    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getContentHeader() {
        return super.getContentHeader();
    }


    @Override
    public String getStatusDesc() {
        return super.getStatusDesc() + ServerManager.getOperations();
    }

}

